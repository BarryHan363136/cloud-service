package com.barry.cloud.platform.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.barry.cloud.platform.easyexcel.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.io.*;
import java.util.*;

/**
 * 参考URL: https://blog.csdn.net/tanhongwei1994/article/details/89084125
 *          https://blog.csdn.net/qq_41066066/article/details/88393053
 * */

@Slf4j
public class FileExcelUtils {

    @Test
    public void testWriteExcel(){
        List<User> userList = new ArrayList<>();
//        for (int i=0;i<100;i++){
//            User user = User.builder().name("张三"+ i).password("1234").age(i).build();
//            userList.add(user);
//        }
        //文件输出位置
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("/Users/hts/Desktop/date_test/excel/write001.xlsx");
            ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
            Sheet sheet1 = new Sheet(1, 0, User.class);
            sheet1.setSheetName("第一个sheet");
            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            excelWriter.write(userList, sheet1);

            // 将上下文中的最终 outputStream 写入到指定文件中
            excelWriter.finish();
        }catch (Exception e){
            log.info("testWriteExcel error {} ", e);
        }
    }

    @Test
    public void testReadExcel(){
        File file = new File("/Users/hts/Desktop/date_test/excel/write001.xlsx");
        try(InputStream inputStream = new FileInputStream(file)){
            List<Object> list = EasyExcelFactory.read(inputStream, new Sheet(1, 1));
            for (Object obj : list){
                if (obj instanceof User){
                    User user = (User) obj;
                    log.info("===============>"+ JSON.toJSONString(user));
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read() throws FileNotFoundException {
        File file = new File("/Users/hts/Desktop/date_test/excel/write001.xlsx");
        InputStream inputStream = new FileInputStream(file);
        AnalysisEventListener<User> listener = new AnalysisEventListener<User>() {

            @Override
            public void invoke(User user, AnalysisContext context) {
                log.info("===============>"+JSON.toJSONString(user));
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                log.info("doAfterAllAnalysed...");
            }
        };
        ExcelReader excelReader = new ExcelReader(inputStream, null, listener);
        // ExcelReader excelReader = EasyExcelFactory.getReader(inputStream, listener);
        // 第二个参数为表头行数，按照实际设置
        excelReader.read(new Sheet(1, 1, User.class));
        // 解析每行结果在listener中处理
    }


}
