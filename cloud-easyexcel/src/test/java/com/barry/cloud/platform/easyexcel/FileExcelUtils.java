package com.barry.cloud.platform.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.barry.cloud.platform.easyexcel.entity.User;
import com.barry.cloud.platform.easyexcel.entity.UserReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        for (int i=0;i<100;i++){
            User user = new User();
            user.setName("张三"+i);
            user.setPassword("hantongshan_"+i);
            user.setAge(i);
            user.setBirthTime(new Date());
            userList.add(user);
        }
        //文件输出位置
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("/Users/hts/Desktop/date_test/excel/write001.xlsx");
            ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
            Sheet sheet1 = new Sheet(1, 0, User.class);
            sheet1.setSheetName("sheet1");
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
        List<UserReader> list = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        File file = new File("/Users/hts/Desktop/date_test/excel/write001.xlsx");
        InputStream inputStream = new FileInputStream(file);
        AnalysisEventListener<List<String>> listener = new AnalysisEventListener<List<String>>() {

            @Override
            public void invoke(List<String> users, AnalysisContext context) {
                log.info("========111===========>name:"+ users.get(0)+",password:"+users.get(1)+",age:"+users.get(2)+",birthTime:"+users.get(3));
                UserReader user = new UserReader();
                user.setName(users.get(0));
                user.setPassword(users.get(1));
                user.setAge(users.get(2)!=null ? Integer.parseInt(users.get(2)) : null);
                try {
                    user.setBirthTime(format.parse(users.get(3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list.add(user);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                log.info("doAfterAllAnalysed...");
            }
        };
        ExcelReader excelReader = new ExcelReader(inputStream, null, listener);
        // ExcelReader excelReader = EasyExcelFactory.getReader(inputStream, listener);
        // 第二个参数为表头行数，按照实际设置
        excelReader.read(new Sheet(1, 1));
        // 解析每行结果在listener中处理
        log.info("=====111==============>"+JSON.toJSONString(list));
    }




}
