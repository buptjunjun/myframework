package junjun.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class POIUtil 
{
	public static void creareExcel(String path) throws Exception
	{
		 // 创建Excel的工作书册 Workbook,对应到一个excel文档  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	  
	    // 创建Excel的工作sheet,对应到一个excel文档的tab  
	    HSSFSheet sheet = wb.createSheet("sheet1");  
	  
	    // 设置excel每列宽度  
	    sheet.setColumnWidth(0, 8000);  
	    sheet.setColumnWidth(1, 8000);  
	  
	    // 创建字体样式  
	    HSSFFont font = wb.createFont();  
	    font.setFontName("Verdana");  
	    font.setBoldweight((short) 100);  
	    font.setFontHeight((short) 300);  
	    font.setColor(HSSFColor.BLUE.index);  
	  
//	    // 创建单元格样式  
//	    HSSFCellStyle style = wb.createCellStyle();  
//	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
//	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
//	    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);  
//	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
//	  
//	    // 设置边框  
//	    style.setBottomBorderColor(HSSFColor.RED.index);  
//	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
//	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
//	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
//	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
//	  
//	    style.setFont(font);// 设置字体  
//	  
//	    // 创建Excel的sheet的一行  
//	    HSSFRow row = sheet.createRow(0);  
//	    row.setHeight((short) 500);// 设定行的高度  
//	    // 创建一个Excel的单元格  
//	    HSSFCell cell = row.createCell(0);  
//	  
//	    // 合并单元格(startRow，endRow，startColumn，endColumn)  
//	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));  
//	  
//	    // 给Excel的单元格设置样式和赋值  
//	    cell.setCellStyle(style);  
//	    cell.setCellValue("hello world");  
//	  
//	    // 设置单元格内容格式  
//	    HSSFCellStyle style1 = wb.createCellStyle();  
//	    style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("h:mm:ss"));  
//	  
//	    style1.setWrapText(true);// 自动换行  
//	  
//	    row = sheet.createRow(1);  
//	  
//	    // 设置单元格的样式格式  
//	  
//	    cell = row.createCell(0);  
//	    cell.setCellStyle(style1);  
//	    //cell.setCellValue(new Date());  
//	  
	    // 创建超链接  
//	    HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);  
//	    link.setAddress("http://www.baidu.com");  
//	    cell = row.createCell(1);  
//	    cell.setCellValue("百度");  
//	    cell.setHyperlink(link);// 设定单元格的链接  
	  
	    FileOutputStream os = new FileOutputStream(path);  
	    wb.write(os);  
	    os.close();  
	}
	
	
	public static void writeExcel(String path,String title, List<List<String>> datas) throws Exception
	{
		 
            InputStream in = new FileInputStream(path);  
            Workbook work = new HSSFWorkbook(in);  
            // 得到excel的第0张表  
            Sheet sheet = work.getSheetAt(0);  
//            // 得到第1行的第一个单元格的样式  
//            Row rowCellStyle = sheet.getRow(1);  
//            CellStyle columnOne = rowCellStyle.getCell(0).getCellStyle();  
            // 这里面的行和列的数法与计算机里的一样，从0开始是第一  
            // 填充title数据  
            Row row = sheet.createRow(0);// 得到行
            row.createCell(0);
            row.createCell(1);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));  
            row = sheet.getRow(0);  
            Cell cell = row.getCell(0);  
            cell.setCellValue(title);  
            int i = 1;//计数器  
             
            // 得到行，并填充数据和表格样式  
            for (List<String> data : datas) 
            {  
                row = sheet.createRow(i);// 得到行  
                int number = 0; 
                for(String d:data)
                { 
                	cell = row.createCell(number);// 得到第0个单元格  
                	cell.setCellValue(d);// 填充值  
                	//cell.setCellStyle(columnOne);// 填充样式  
                	number++;
                }
                
                i++;
            }  
            
            in.close();
            
            FileOutputStream os = new FileOutputStream(path);  
            work.write(os);  
            os.close();
    }  
	
	
	public static void main(String [] args) throws Exception
	{
		String path = "test.xls";
		creareExcel(path);
		List<List<String>> data  =  new ArrayList<List<String>>();
		List<String> data1 = Arrays.asList("addddddddddddddddddddddddddddddddddddddddddddddddd,b,c,d".split(","));
		List<String> data2 = Arrays.asList("aa,ba,ca,11".split(","));
		data.add(data1);
		data.add(data2);
		
		writeExcel(path,"哈喽",data);
		
	}
	
}
