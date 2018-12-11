package com.barry.cloud.platform.poi.utils;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * Description: Excel操作
 */
@Slf4j
public class ExcelUtil {

    public static void main(String[] args) {
        /** 读取文件 */
//        String filePath = "C:\\Users\\qxv0963\\Desktop\\TempFiles\\BonApp\\AccessLog\\1208access-data-test.xlsx";
//        List<?> list = XlsxUtils.readXlsxFile(filePath);
//        log.info("============>"+JSONMapper.writeObjectAsString(list));
        /** 写文件 */
        String filePath = "C:\\Users\\qxv0963\\Desktop\\TempFiles\\BonApp\\AccessLog\\poi-write-test.xlsx";
        XlsxUtils.writeIntoExcel("sheet0", filePath);
    }

}