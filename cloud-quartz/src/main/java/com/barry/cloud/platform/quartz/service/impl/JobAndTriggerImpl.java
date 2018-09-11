package com.barry.cloud.platform.quartz.service.impl;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.quartz.entity.TaskInfo;
import com.barry.cloud.platform.quartz.job.QuartzWeatherJob;
import com.barry.cloud.platform.quartz.service.JobAndTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/10 14:26
 */
@Slf4j
@Service
public class JobAndTriggerImpl implements JobAndTriggerService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(TaskInfo taskInfo) {
        try {
            if (checkExists(taskInfo.getJobName(), taskInfo.getJobGroup())) {
                log.info("===> AddJob fail, job already exist, jobGroup:{} "+taskInfo.getJobName()+" jobName:{} "+taskInfo.getJobGroup());
                throw new RuntimeException(String.format("Job已经存在, jobName:{%s},jobGroup:{%s}", taskInfo.getJobName(), taskInfo.getJobGroup()));
            }
            // 启动调度器
            scheduler.start();

            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(QuartzWeatherJob.class)
                    .withIdentity(taskInfo.getJobName(), taskInfo.getJobGroup()).build();
            jobDetail.getJobDataMap().put("weatherDataMap", taskInfo);
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskInfo.getCronExpression());

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(taskInfo.getJobName(), taskInfo.getJobGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("===========>创建定时任务成功");
        } catch (Exception e) {
            log.error("addJob error {} ", e);
        }

    }

    @Override
    public void updateJob(TaskInfo taskInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(taskInfo.getJobName(), taskInfo.getJobGroup());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskInfo.getCronExpression());

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("updateJob error {} ", e);
        }
    }

    @Override
    public void deleteJob(TaskInfo taskInfo) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(taskInfo.getJobName(), taskInfo.getJobGroup()));
            scheduler.unscheduleJob(TriggerKey.triggerKey(taskInfo.getJobName(), taskInfo.getJobGroup()));
            scheduler.deleteJob(JobKey.jobKey(taskInfo.getJobName(), taskInfo.getJobGroup()));
        } catch (SchedulerException e) {
            log.error("deleteJob error {} ", e);
        }
    }

    @Override
    public void pauseJob(TaskInfo taskInfo) {
        try {
            scheduler.pauseJob(JobKey.jobKey(taskInfo.getJobName(), taskInfo.getJobGroup()));
        } catch (SchedulerException e) {
            log.error("pauseJob error {} ", e);
        }
    }

    @Override
    public void resumejob(TaskInfo taskInfo) {
        try {
            scheduler.resumeJob(JobKey.jobKey(taskInfo.getJobName(), taskInfo.getJobGroup()));
        } catch (SchedulerException e) {
            log.error("resumejob error {} ", e);
        }
    }

    /**
     * 验证是否存在
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     * 2016年10月8日下午5:30:43
     */
    private boolean checkExists(String jobName, String jobGroup) throws SchedulerException{
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    @Override
    public List<TaskInfo> getAllJob() {
        List<TaskInfo> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<TaskInfo>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    TaskInfo job = new TaskInfo();
                    job.setJobName(jobKey.getName());
                    job.setJobGroup(jobKey.getGroup());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                    Integer jobState = null;
//                    if (StringUtils.isNotEmpty(triggerState.name())){
//                        jobState = Integer.parseInt(triggerState.name());
//                    }
//                    job.setJobStatus(jobState);
                    job.setRemark("触发器:" + trigger.getKey()+", job状态:"+getJobState(triggerState.name()));

                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    jobList.add(job);
                }
            }
        } catch (SchedulerException e) {
            log.error("getAllJob error {} ", e);
        }
        return jobList;
    }

    /**
     * state的值代表该任务触发器的状态：
     * STATE_BLOCKED 	4   阻塞
     * STATE_COMPLETE 	2   完成
     * STATE_ERROR 	3   错误
     * STATE_NONE 	-1   不存在
     * STATE_NORMAL 	0  正常
     * STATE_PAUSED 	1  暂停
     * */
    private String getJobState(String stateName){
        String status = "";
        switch(stateName){
            case "NONE":
                status = "不存在";
                break;
            case "NORMAL":
                status = "正常";
                break;
            case "PAUSED":
                status = "暂停";
                break;
            case "COMPLETE":
                status = "完成";
                break;
            case "ERROR":
                status = "错误";
                break;
            case "BLOCKED":
                status = "阻塞";
                break;
            default:
                status = "";
                break;
        }
        return status;
    }

    /**
     * 所有正在运行的job
     * TriggerState job状态定义如下:
     * NONE:0;NORMAL:1;PAUSED:2;COMPLETE:3;ERROR:4;BLOCKED:5
     * @return
     * @throws SchedulerException
     */
    @Override
    public List<TaskInfo> getRunningJob() {
        List<TaskInfo> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                TaskInfo job = new TaskInfo();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setRemark("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                Integer jobState = null;
                if (StringUtils.isNotEmpty(triggerState.name())){
                    jobState = Integer.parseInt(triggerState.name());
                }
                job.setJobStatus(jobState);
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        } catch (SchedulerException e) {
            log.error("getRunningJob error {} ", e);
        }
        return jobList;
    }

//    @Override
//    public List<TaskInfo> getRunningJob() {
//        List<TaskInfo> jobList = new ArrayList<>();
//        try {
//            for (String groupName : scheduler.getJobGroupNames()) {
//                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
//                    TaskInfo taskInfo = new TaskInfo();
//                    taskInfo.setJobName(jobKey.getName());
//                    taskInfo.setJobGroup(jobKey.getGroup());
//                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
//                    for (Trigger trigger : triggers){
//                        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                        taskInfo.setRemark("触发器:" + trigger.getKey()+", job状态:"+getJobState(triggerState.name()));
//                    }
//                    jobList.add(taskInfo);
//                }
//            }
//        } catch (Exception e) {
//            jobList = null;
//            log.error("getRunningJob error {} ", e);
//        }
//        return jobList;
//    }

    /**
     * 暂停调度中所有的job任务
     */
    public  void pauseAll() {
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            log.error("pauseAll error {} ", e);
        }
    }

    /**
     * 恢复调度中所有的job的任务
     */
    public  void resumeAll() {
        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            log.error("resumeAll error {} ", e);
        }
    }


}