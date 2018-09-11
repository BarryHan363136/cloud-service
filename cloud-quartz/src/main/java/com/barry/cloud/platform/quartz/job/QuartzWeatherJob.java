package com.barry.cloud.platform.quartz.job;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.quartz.entity.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.Serializable;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/7 16:05
 */
@Slf4j
@DisallowConcurrentExecution
public class QuartzWeatherJob implements Job, Serializable {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("<============QuartzWeatherJob=================>");
        TaskInfo taskInfo = (TaskInfo) jobExecutionContext.getJobDetail().getJobDataMap().get("weatherDataMap");
        log.info("<============QuartzWeatherJob=================>"+JSONMapper.writeObjectAsString(taskInfo));
    }

}
