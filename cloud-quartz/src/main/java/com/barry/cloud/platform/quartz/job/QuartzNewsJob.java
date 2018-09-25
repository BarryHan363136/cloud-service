package com.barry.cloud.platform.quartz.job;

import com.barry.cloud.platform.common.utils.DateUtils;
import com.barry.cloud.platform.quartz.entity.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import java.io.Serializable;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/7 16:05
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzNewsJob implements Job, Serializable {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        TaskInfo taskInfo = (TaskInfo) jobExecutionContext.getJobDetail().getJobDataMap().get("jobDataMap");
        log.info("<============QuartzNewsJob=================>"+DateUtils.getCurrentDateTimeAsString());
    }

}
