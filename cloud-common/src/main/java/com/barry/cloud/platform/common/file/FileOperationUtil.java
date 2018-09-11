package com.barry.cloud.platform.common.file;

import java.io.File;

/**
 * java操作文件或文件夹
 * 
 * @author hts
 * @date 2016-04-13
 */
public class FileOperationUtil {

  /**
   * 删除单个文件
   * 
   * @param sPath 被删除文件的文件名
   * @return 单个文件删除成功返回true，否则返回false
   */
  public static boolean deleteFile(String sPath) {
    boolean flag = false;
    File file = new File(sPath);
    // 路径为文件且不为空则进行删除
    if (file.isFile() && file.exists()) {
      file.delete();
      flag = true;
    }
    return flag;
  }

  /**
   * 删除目录（文件夹）以及目录下的文件
   * 
   * @param sPath 被删除目录的文件路径
   * @return 目录删除成功返回true，否则返回false
   */
  public static boolean deleteDirectory(String sPath) {
    // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
    if (!sPath.endsWith(File.separator)) {
      sPath = sPath + File.separator;
    }
    File dirFile = new File(sPath);
    // 如果dir对应的文件不存在，或者不是一个目录，则退出
    if (!dirFile.exists() || !dirFile.isDirectory()) {
      return false;
    }
    boolean flag = true;
    // 删除文件夹下的所有文件(包括子目录)
    File[] files = dirFile.listFiles();
    for (int i = 0; i < files.length; i++) {
      // 删除子文件
      if (files[i].isFile()) {
        flag = deleteFile(files[i].getAbsolutePath());
        if (!flag)
          break;
      } // 删除子目录
      else {
        flag = deleteDirectory(files[i].getAbsolutePath());
        if (!flag)
          break;
      }
    }
    if (!flag)
      return false;
    // 删除当前目录
    if (dirFile.delete()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 生产文件 如果文件所在路径不存在则生成路径
   * 
   * @param fileName 文件名 带路径
   * @param isDirectory 是否为路径
   * @return
   * @author yayagepei
   * @date 2008-8-27
   */
  public static File buildFile(String fileName, boolean isDirectory) {
    File target = new File(fileName);
    if (isDirectory) {
      target.mkdirs();
    } else {
      if (!target.getParentFile().exists()) {
        target.getParentFile().mkdirs();
        target = new File(target.getAbsolutePath());
      }
    }
    return target;
  }

}
