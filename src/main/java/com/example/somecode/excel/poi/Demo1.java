package com.example.somecode.excel.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Demo1 {

    public static void main(String[] args) throws IOException {
        Demo1 demo1 = new Demo1();
//        demo1.read07v2();
        demo1.write03();
//        demo1.write07Plus();
    }

    /**
     * 读excel-03版本
     * @throws IOException
     */
    public void read03() throws IOException {
        String path = "D:\\file\\测试01.xls";

        try (
                // 获取文件流
                FileInputStream inputStream = new FileInputStream(path);
                // 1、创建一个工作簿。使用excel能操作的这边他都可以操作
                Workbook workbook = new HSSFWorkbook(inputStream);
        ) {
            // 2、得到表
            Sheet sheet = workbook.getSheetAt(0);
            // 3、得到行
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            for (int i = firstRowNum; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                // 4、得到列
                short firstCellNum = row.getFirstCellNum();
                short lastCellNum = row.getLastCellNum();
                for (int j = firstCellNum; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    // 获取字符串类型 ,这里留意要保证单元格中数据格式和处理格式对应，否则会报错
                    String value = cell.getStringCellValue();
                    System.out.println("03版本的值:" + value);
                }
            }
        }
    }

    /**
     * 读Excel-03版本v2
     * @throws IOException
     */
    public void read03v2() throws IOException {
        String path = "D:\\file\\测试01.xls";

        try (
                // 获取文件流
                FileInputStream inputStream = new FileInputStream(path);
                // 1、创建一个工作簿。使用excel能操作的这边他都可以操作
                Workbook workbook = new HSSFWorkbook(inputStream);
        ) {
            // 2、得到表
            Sheet sheet = workbook.getSheetAt(0);

            // 3、得到行
            // 3.1 处理第一行，表头
            Row head = sheet.getRow(0);
            System.out.print(head.getCell(0).getStringCellValue() + " ");
            System.out.print(head.getCell(1).getStringCellValue() + " ");
            System.out.print(head.getCell(2).getStringCellValue() + " ");
            System.out.println();

            // 3.2 处理其他行，数据行
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                System.out.print((int)row.getCell(0).getNumericCellValue() + " ");
                System.out.print(row.getCell(1).getStringCellValue() + " ");
                System.out.print(row.getCell(2).getNumericCellValue() + " ");
                System.out.println();
            }
        }
    }

    /**
     * 读Excel-07版本v2
     * @throws IOException
     */
    public void read07v2() throws IOException {
        String path = "D:\\file\\测试02.xlsx";

        try (
                // 获取文件流
                FileInputStream inputStream = new FileInputStream(path);
                // 1、创建一个工作簿。使用excel能操作的这边他都可以操作
                Workbook workbook = new XSSFWorkbook(inputStream);
        ) {
            // 2、得到表
            Sheet sheet = workbook.getSheetAt(0);

            // 3、得到行
            // 3.1 处理第一行，表头
            Row head = sheet.getRow(0);
            System.out.print(head.getCell(0).getStringCellValue() + " ");
            System.out.print(head.getCell(1).getStringCellValue() + " ");
            System.out.print(head.getCell(2).getStringCellValue() + " ");
            System.out.println();

            // 3.2 处理其他行，数据行
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                System.out.print((int)row.getCell(0).getNumericCellValue() + " ");
                System.out.print(row.getCell(1).getStringCellValue() + " ");
                System.out.print(row.getCell(2).getNumericCellValue() + " ");
                System.out.println();
            }
        }
    }

    /**
     * 写Excel-03版本
     * @throws IOException
     */
    public void write03() throws IOException {
        //创建测试数据，真实应该是从db或者ftp等数据源获取的
        Person person1 = new Person("1001", "张三", 18);
        Person person2 = new Person("1002", "李四", 19);
        Person person3 = new Person("1003", "王五", 20);
        List<Person> personList = Arrays.asList(person1, person2, person3);

        FileOutputStream fileOutputStream = null;
        Workbook workbook = null;

        try {
            // 生成一张表(IO流) 03版本就是使用xls结尾，07版本使用xlsx结尾
            long timeMillis = System.currentTimeMillis();
            String path = "D:\\file\\写测试" + timeMillis + ".xls";
            fileOutputStream = new FileOutputStream(path);

            // 1、创建一个工作簿
            workbook = new HSSFWorkbook();
            // 2、创建一个工作表
            Sheet sheet = workbook.createSheet("sheet01");

            //维护标题list
//        String[] titilArray = {"id", "name", "age"};
            List<String> titleList = Arrays.asList("id", "name", "age");
            Row head = sheet.createRow(0);
            for(int i=0;i<titleList.size();i++) {
                Cell cell = head.createCell(i, CellType.STRING);
                cell.setCellValue(titleList.get(i));
            }

            //正常导出文件的每列顺序是固定的，方便用户习惯，当然可以自定义，这里只讲怎么导出，具体导出根据业务细节自己处理
            for (int i = 0; i < personList.size(); i++) {
                // 3、创建一个行，如果有表头的话，这里就是第二行开始，索引为1
                Row row = sheet.createRow(i + 1);
                Person person = personList.get(i);
                //id
                Cell cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(person.getId());
                //name
                Cell nameCell = row.createCell(1, CellType.STRING);
                nameCell.setCellValue(person.getName());
                //age
                Cell authorCell = row.createCell(2, CellType.NUMERIC);
                authorCell.setCellValue(person.getAge());
            }
            workbook.write(fileOutputStream);
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                log.error("fileOutputStream close error...", e);
            }
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (Exception e) {
                log.error("workbook close error...", e);
            }
        }
        System.out.println("write end...");
    }

    /**
     * 写Excel-07版本-大数据量
     * @throws IOException
     */
    public void write07BigData() throws IOException {
        long begin = System.currentTimeMillis();
        long timeMillis = System.currentTimeMillis();
        String path = "D:\\file\\大数据量写测试" + timeMillis + ".xlsx";

        try (
                // 1、创建一个工作簿 07
                SXSSFWorkbook workbook = new SXSSFWorkbook();
                FileOutputStream fileOutputStream = new FileOutputStream(path)
        ) {
            // 2、创建一个工作表
            Sheet sheet = workbook.createSheet("sheet1");
            // 3、写入数据
            for (int rowNum = 0; rowNum < 105536; rowNum++) {
                Row row = sheet.createRow(rowNum);
                for (int cellNum = 0; cellNum < 10; cellNum++) {
                    Cell cell = row.createCell(cellNum);
                    cell.setCellValue(cellNum);
                }
            }

            workbook.write(fileOutputStream);
            // 关闭流
            fileOutputStream.close();
            // 清除临时文件
            workbook.dispose();
            long end = System.currentTimeMillis();
            System.out.println("测试大数据量表 生成完毕,时间消耗:" + (double) (end - begin));
        }
    }

    /**
     * 读Excel文件--从MultipartFile中读
     * @param file
     * @throws IOException
     */
    public void readFromMultiFile(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        // 判断文件类型，选择相对应的workbook ......
        Workbook workbook = new XSSFWorkbook(is);
        // .....
        workbook.close();
    }

    /**
     * 读Excel文件--从HttpServletRequest中读
     * @param request
     * @throws IOException
     */
    public void readFromRequest(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest mulReq=(MultipartHttpServletRequest)request;
        MultipartFile multipartFile = mulReq.getFile("multipartFile"); // multipartFile为自定义的参数key
        if(multipartFile ==null){
            return;
        }
        InputStream is = multipartFile.getInputStream();
        // 判断文件类型，选择相对应的workbook ......
        Workbook workbook = new XSSFWorkbook(is);
        // .....
        workbook.close();
    }

    /**
     * 写Excel到浏览器
     * @param response
     * @throws IOException
     */
    public void write03ToBrowser(HttpServletResponse response) throws IOException {
        Workbook workbook = new SXSSFWorkbook();
        // workbook设置操作....

        // 设置响应头信息
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=1.xlsx");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        servletOutputStream.flush(); // 刷新缓冲区
        servletOutputStream.close();
        workbook.close();
    }

    /**
     * 设置Excel中的行、列、单元格数据格式
     */
    public void setStyle() {
        Workbook wb = new XSSFWorkbook();

        // 设置单元格各种样式
        // 设置字体
        Font font = wb.createFont();
        //字体高度
        font.setFontHeightInPoints((short) 11);
        //字体颜色
        font.setColor(Font.COLOR_NORMAL);
        //字体
        font.setFontName("宋体");

        //设置单元格里的字体样式，使用上面设置对字体样式，以及设置单元格的格式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        //水平布局：居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //单元格垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //换行
        cellStyle.setWrapText(true);

        /**
         *设置CELL格式为文本格式
         */
        DataFormat format = wb.createDataFormat();
        //"@"是指文本的数据格式，主要是这段代码
        cellStyle.setDataFormat(format.getFormat("@"));

        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);

        // 设置行格式
        // row.setRowStyle(cellStyle);

        Cell cell = row.getCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("你的值");

        //CELL_TYPE_STRING是单元格格式，这里写不写没区别…… 原因如下解释
        cell.setCellType(CellType.STRING);
    }

    public void readExpr() throws IOException {
        String PATH = "D:\\test\\";
        // 获取文件流
        FileInputStream inputStream = new FileInputStream(PATH + "测试公式.xlsx");
        // 1、创建一个工作簿。使用excel能操作的这边他都可以操作
        Workbook workbook = new XSSFWorkbook(inputStream);
        // 2、得到表
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(5);
        Cell cell = row.getCell(0);
        //拿到计算公式 eval
        XSSFFormulaEvaluator xssfFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
        //输出单元格的内容
        String cellFormula = cell.getCellFormula();
        System.out.println(cellFormula); //SUM(A2,A3,A4)
        //计算
        CellValue evaluate = xssfFormulaEvaluator.evaluate(cell);
        String cellValue = evaluate.formatAsString();
        System.out.println(cellValue); // 291.11
    }

    /**
     * 设置富文本格式，分段字符格式
     * @throws IOException
     */
    public void setRichText() throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\test.xls"));
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("富文本");
        sheet.setColumnWidth(2, 150*256);
        Row row = sheet.createRow(5);
        Cell cell = row.createCell(2);

        CellStyle style = workbook.createCellStyle();
        cell.setCellStyle(style);
        HSSFRichTextString str = new HSSFRichTextString("富文本上标 字符串下标 水平删除线 下划线");

        /**********************************第一个字体Font:富文本0-3******************************************************/
        // 在workbook中创建一个字体
        Font font1 = workbook.createFont();
        // 设置字体为粗体
        font1.setBold(true);
        // 设置字体的字符集 - 默认字体集
        font1.setCharSet(Font.DEFAULT_CHARSET);
        // 设置字体的高度 - 以1pt的1/20位单位
        font1.setFontHeightInPoints((short)40);
        // 设置字体的名字
        font1.setFontName("宋体");
        // 设置文字为斜体
        font1.setItalic(false);
        // 使用水平删除线
        font1.setStrikeout(true);
        // 设置字体颜色为默认黑色
        font1.setColor(Font.COLOR_RED);
        style.setFont(font1);

        /**********************************第二个字体Font: 上标3-5******************************************************/
        Font font2 = workbook.createFont();
        // 设置上标字体高度为30pt
        font2.setFontHeightInPoints((short)20);
        // 设置为上标
        font2.setTypeOffset(Font.SS_SUPER);
        str.applyFont(3, 5, font2);

        /**********************************第三个字体Font: 字符串6-9******************************************************/
        Font font3 = workbook.createFont();
        // 设置字体高度 - 以1pt为单位, 设置字体为60pt
        font3.setFontHeightInPoints((short)40);
        font3.setBold(true);
        // 设置字体的字符集 - ANSI字符集
        font3.setCharSet(Font.ANSI_CHARSET);
        // 设置字体颜色为深红色
        font3.setColor(Font.COLOR_RED);
        str.applyFont(6, 9, font3);

        /**********************************第四个字体Font: 下标9-11******************************************************/
        Font font4 = workbook.createFont();
        // 设置为下标
        font4.setTypeOffset(Font.SS_SUB);
        // 设置下 标字体高度为30pt
        font4.setFontHeightInPoints((short)20);
        str.applyFont(9, 11, font4);

        /**********************************第五个字体Font: 水平删除线12-17******************************************************/
        Font font5 = workbook.createFont();
        // 设置下 标字体高度为30pt
        font5.setFontHeightInPoints((short)30);
        font5.setColor(Font.COLOR_RED);
        font5.setStrikeout(true);
        str.applyFont(12, 17, font5);

        /**********************************第六个字体Font: 下划线18-21******************************************************/
        Font font6 = workbook.createFont();
        // 设置下 标字体高度为30pt
        font6.setFontHeightInPoints((short)30);
        font6.setColor(Font.COLOR_RED);
        font6.setItalic(true);
        // 设置字体双下划线
        font6.setUnderline(Font.U_DOUBLE);
        str.applyFont(18, 21, font6);

        cell.setCellValue(str);
        workbook.write(out);
    }
}
