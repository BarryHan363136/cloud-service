package com.barry.cloud.platform.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 相关参数获取实现类
 * 
 * @author hantongshan
 * @modify time 2013-12-26 16:49 PM
 */
@Slf4j
public class PropertieUtil {

  public static final String PARAS_CONFIG_FILENAME = "application.properties";

  protected static final Properties properties = new Properties();

  protected static void init(String propertyFileName) {
    InputStream in = null;
    try {
      in = PropertieUtil.class.getClassLoader().getResourceAsStream(propertyFileName);
      if (in != null)
        properties.load(in);
      log.info("PropertieUtil init sucessfull.............");
    } catch (IOException e) {
      log.error("PropertieUtil init error:", e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          log.error("PropertieUtil in finally error:", e);
        }
      }
    }
  }

  protected static String getProperty(String key, String defaultValue) {
    String value = properties.getProperty(key, defaultValue);
    log.info("PropertieUtil getProperty key:" + key + ",value:" + value + ",defaultValue:"
        + defaultValue);
    return value;
  }

  protected static String getProperty(String key) {
    String value = properties.getProperty(key);
    log.info("PropertieUtil getProperty key:" + key + ",value:" + value);
    return value;
  }

  static {
    init(PARAS_CONFIG_FILENAME);
  }

  /** 消息指令超时时间设置 */
  public static Integer COMMAND_TIMEOUT_SECONDS = new Integer(getProperty("opts.content.command.timeout.seconds"));
  /** Command历史数据存储路径 */
  public static String EXCEL_DATA_PATH = new String(getProperty("opts.content.excel.data.storge.path"));
  public static Integer COMMAND_TIMEOUT_DAYS = new Integer(getProperty("opts.content.command.timeout.days"));

  public static void main(String args[]) {
    log.info("**********为:" + PropertieUtil.COMMAND_TIMEOUT_DAYS);
  }
}
