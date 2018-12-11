package com.barry.cloud.platform.poi.utils;

import com.barry.cloud.platform.poi.model.Record;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

@Slf4j
public class XlsxUtils {

    /**
     * demo.format("###,###.###", 111222.34567);
     *         demo.format("000,000.000", 11222.34567);
     *         demo.format("###,###.###$", 111222.34567);
     *         demo.format("000,000.000￥", 11222.34567);
     *         demo.format("##.###%", 0.345678);        // 使用百分数形式
     *         demo.format("00.###%", 0.0345678);        // 使用百分数形式
     *         demo.format("###.###\u2030", 0.345678);    // 使用千分数形式
     * */
    public static String numericFormat(String pattern, double value){
        DecimalFormat df=new DecimalFormat(pattern);
        return df.format(value);
    }


    /**
     * 根据Cell类型设置数据
     * @param cell
     * @return Object
     */
    public static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            /** 判断当前Cell的Type */
            switch (cell.getCellType()) {
                case NUMERIC:// 如果当前Cell的Type为NUMERIC
                    cellvalue = numericFormat("###", cell.getNumericCellValue());
                    break;
                case FORMULA: {
                    /** 判断当前的cell是否为Date */
                    if (DateUtil.isCellDateFormatted(cell)) {
                        /**
                         * 如果是Date类型则，转化为Data格式
                         * data格式是带时分秒的：2013-7-10 0:00:00
                         * cellvalue = cell.getDateCellValue().toLocaleString();
                         *  */
                        /** data格式是不带带时分秒的：2013-7-10 */
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {
                        /** 如果是纯数字,取得当前Cell的数值 */
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:
                    /** 取得当前的Cell字符串 */
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    /** 默认的Cell值 */
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    /**
     * 读取excel文件
     * */
    public static List<?> readXlsxFile(String filePath) {
        List<Record> list = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = spreadsheet.iterator();
            XSSFRow row;
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
//                if (row.getRowNum()==0){//判断第一行是否跳过
//                    continue;
//                }
                String count = (String) getCellFormatValue(row.getCell(0));
                String vin = (String) getCellFormatValue(row.getCell(1));
                log.info("=============>count="+count+",vin="+vin);
                Record record = new Record();
                record.setCount(count);
                record.setVin(vin);
                list.add(record);
            }
            return list;
        } catch (Exception e) {
            log.error("read excel error {} ", e);
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 写入excel文件
     * */
    public static void writeIntoExcel(){
        FileOutputStream out = null;
        try {
            //Create blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();

            //Create a blank sheet
            XSSFSheet spreadsheet = workbook.createSheet(" Employee Info ");

            //Create row object
            XSSFRow row;

            //This data needs to be written (Object[])
            Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
            empinfo.put( "1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
            empinfo.put( "2", new Object[] { "tp01", "Gopal", "Technical Manager" });
            empinfo.put( "3", new Object[] { "tp02", "Manisha", "Proof Reader" });
            empinfo.put( "4", new Object[] { "tp03", "Masthan", "Technical Writer" });
            empinfo.put( "5", new Object[] { "tp04", "Satish", "Technical Writer" });
            empinfo.put( "6", new Object[] { "tp05", "Krishna", "Technical Writer" });

            //Iterate over data and write to sheet
            Set<String> keyid = empinfo.keySet();
            int rowid = 0;

            for (String key : keyid) {
                row = spreadsheet.createRow(rowid++);
                Object [] objectArr = empinfo.get(key);
                int cellid = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue((String)obj);
                }
            }
            /** Write the workbook in file system */
            out = new FileOutputStream(new File("Writesheet.xlsx"));
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (out!=null){
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
