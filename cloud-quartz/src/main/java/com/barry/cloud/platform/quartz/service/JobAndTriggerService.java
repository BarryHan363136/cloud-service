package com.barry.cloud.platform.quartz.service;

import com.barry.cloud.platform.quartz.entity.TaskInfo;

import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/10 14:16
 */
public interface JobAndTriggerService {

    public void addJob(TaskInfo taskInfo);

    public void updateJob(TaskInfo taskInfo);

    public void deleteJob(TaskInfo taskInfo);

    public void pauseJob(TaskInfo taskInfo);

    public void resumejob(TaskInfo taskInfo);

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    public List<TaskInfo> getAllJob();

    /**
     * 所有正在运行的job
     * @return
     */
    public List<TaskInfo> getRunningJob();

    /**
     * 暂停调度中所有的job任务
     *
     */
    public  void pauseAll();

    /**
     * 恢复调度中所有的job的任务
     *
     */
    public  void resumeAll();

}
