package com.kingbase.bookSearch.core.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * poi 解析 xls 格式的excel
 * @author ganliang
 */
public class XLSExcelParser extends ExcelParser {

	@Override
	public List<List<Object>> parser(InputStream stream) throws IOException {
		if (stream == null) {
			throw new IllegalArgumentException();
		}
		// 创建一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(stream);

		List<List<Object>> data = parseSheet(workbook);

		// 关闭资源
		workbook.close();
		stream.close();
		return data;
	}


	@Override
	public List<List<Object>> parser(String filePath) throws IOException {
		if(filePath==null||"".equals(filePath)){
			throw new IllegalArgumentException("file not be null");
		}
		File file=new File(filePath);
		if(!file.exists()){
			throw new IllegalArgumentException("file not exists!");
		}
		if(!file.isFile()){
			throw new IllegalArgumentException("file is not file!");
		}
		if(!file.getName().endsWith("xls")){
			throw new IllegalArgumentException("not support file type!");
		}
		FileInputStream stream = new FileInputStream(file);
		
		return parser(stream);
	}

	@Override
	public Workbook create(String sheetName, List<String> headers, List<List<Object>> data) {
		if(headers==null||headers.size()==0||data==null||data.size()==0){
			throw new IllegalArgumentException("保存excel文件不能为空！");
		}
		Workbook wb = new HSSFWorkbook();
		
		CreationHelper creationHelper = wb.getCreationHelper();
		//设置excel的样式
		CellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd h:mm:ss"));
	    cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
	    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    //设置字体的样式
	    Font font = wb.createFont();
	    font.setFontHeightInPoints((short)12);
	    font.setFontName("Courier New");
	    font.setItalic(true);
	    font.setStrikeout(true);
	    //cellStyle.setFont(font);
	    
	    //分页表格
	    int pageSize=1;
	    int begin=0;
	    int end=100;
	    if(data.size()%100==0){
	    	pageSize=data.size()/100;
	    }else{
	    	pageSize=data.size()/100+1;
	    }
	    for (int index = 0; index < pageSize; index++) {
	    	begin=index*100;
	    	//最后一个sheet
	    	if(pageSize-index==1){
	    		end=data.size();
	    	}else{
	    		end=(index+1)*100;
	    	}
	    	Sheet sheet = wb.createSheet(sheetName+index);
		    //设置标题头的样式
		    Header header = sheet.getHeader();
		    header.setCenter("Center Header");
		    header.setLeft("Left Header");
		    header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
		                    HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");
		    //添加表头
		    Row headerRow = sheet.createRow(0);
		    for (int i = 0; i < headers.size(); i++) {
		    	Cell createCell = headerRow.createCell(i);
		    	createCell.setCellValue(headers.get(i));
		    	createCell.setCellStyle(cellStyle);
			}
	        //添加表格数据
		    int rowIndex=0;
	        for (int i = begin; i < end; i++) {
	        	//创建一行
	        	Row row = sheet.createRow(++rowIndex);
	        	List<Object> list=data.get(i);
	        	for (int j = 0; j < list.size(); j++) {
	        		//创建一列
	        		Cell cell = row.createCell(j);
	        		//cell.setCellStyle(cellStyle);
	        		Object obj = list.get(j);
	        		if(obj==null){
	        			cell.setCellValue("");
	        		}else{
	        			if(obj instanceof Boolean){
		        			cell.setCellValue((Boolean)obj);
		        		}else if(obj instanceof Date){
		        			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        			String dateStr = sdf.format((Date)obj);
		        			cell.setCellValue(dateStr);
		        		}else if(obj instanceof Double){
		        			cell.setCellValue((Double)obj);
		        		}else{
		        			cell.setCellValue(obj.toString());
		        		}
	        		}
				}
			}
	        sheet.setDefaultColumnWidth(25);
		}
	    
		return wb;
	}
}
