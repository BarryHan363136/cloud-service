package com.barry.cloud.platform.quartz.controller;

import com.barry.cloud.platform.quartz.entity.TaskInfo;
import com.barry.cloud.platform.quartz.service.JobAndTriggerService;
import com.barry.cloud.platform.quartz.utils.ResultGenerator;
import com.barry.cloud.platform.quartz.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/10 10:56
 */

@Slf4j
@RestController
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    /**
     * @Title: addJob
     * @Description: 添加Job
     * @param
     *
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addJob(@RequestBody TaskInfo taskInfo){
        try {
            jobAndTriggerService.addJob(taskInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("JobController addJob error {} ", e);
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    @RequestMapping(value = "/getAllJob", method = RequestMethod.GET)
    public List<TaskInfo> getAllJob(HttpServletRequest request){
        try {
            List<TaskInfo> list = jobAndTriggerService.getAllJob();
            return list;
        } catch (Exception e) {
            log.error("JobController getAllJob error {} ", e);
            return null;
        }
    }

    /**
     * 所有正在运行的job
     * @return
     */
    @RequestMapping(value = "/getRunningJob", method = RequestMethod.GET)
    public List<TaskInfo> getRunningJob(HttpServletRequest request){
        try {
            List<TaskInfo> list = jobAndTriggerService.getRunningJob();
            return list;
        } catch (Exception e) {
            log.error("JobController getRunningJob error {} ", e);
            return null;
        }
    }

    /**
     * @Title: pauseJob
     * @Description: 暂停Job
     */
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    public Result pauseJob(@RequestBody TaskInfo taskInfo) {
        try {
            jobAndTriggerService.pauseJob(taskInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("JobController pauseJob error {} ", e);
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    /**
     * @Title: resumeJob
     * @Description: 恢复Job
     */
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    public Result resumeJob(@RequestBody TaskInfo taskInfo) {
        try {
            jobAndTriggerService.resumejob(taskInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("JobController resumeJob error {} ", e);
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    /**
     * @Title: rescheduleJob
     * @Description: 重新设置Job
     */
    @RequestMapping(value = "/reschedule", method = RequestMethod.POST)
    public Result rescheduleJob(@RequestBody TaskInfo taskInfo) {
        try {
            jobAndTriggerService.updateJob(taskInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("JobController rescheduleJob error {} ", e);
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    /**
     * @Title: deleteJob
     * @Description: 删除Job
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result deleteJob(@RequestBody TaskInfo taskInfo) {
        try {
            jobAndTriggerService.deleteJob(taskInfo);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            log.error("JobController deleteJob error {} ", e);
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }


}
