package com.kingbase.bookSearch.core.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class ExcelParser {

	/**
	 * 解析 excel
	 * @param stream
	 * @return 返回解析的数据
	 * @throws IOException 
	 */
	public abstract List<List<Object>> parser(InputStream stream) throws IOException;
	
	/**
	 * 从文件中解析excel
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public abstract List<List<Object>> parser(String filePath) throws IOException;
	
	/**
	 * 解析表格
	 * @param workbook
	 * @return
	 */
	protected List<List<Object>> parseSheet(Workbook workbook) {
		List<List<Object>> data = new ArrayList<List<Object>>();
		Iterator<Sheet> sheetIterator = workbook.iterator();
		
		int sheetCount=0;
		// 遍历excel多个sheet
		while (sheetIterator.hasNext()) {
			
			Sheet sheet = sheetIterator.next();
			Iterator<Row> rowIterator = sheet.iterator();
			int rowNumber=0;
			// 遍历行
			while (rowIterator.hasNext()) {
				//多个表单头行 一样
				if(sheetCount>1&&rowNumber==0){
					continue;
				}
				Row row = rowIterator.next();
				List<Object> rowData = parseRow(row);
				data.add(rowData);
				
				rowNumber++;
			}
			
			sheetCount++;
		}
		return data;
	}

	/**
	 * 解析行
	 * @param row
	 * @return
	 */
	protected List<Object> parseRow(Row row) {
		List<Object> rowData = new ArrayList<Object>();

		for (int i = 0; i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			Object cellObj=null;
			if(cell!=null){
				cellObj = parseCell(cell);
			}
			rowData.add(cellObj);
		}
		/*// 迭代 一行的各个单元格
		Iterator<Cell> cellIterator = row.iterator();
		// 遍历一行多列
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			Object cellObj = parseCell(cell);
			rowData.add(cellObj);
		}*/
		return rowData;
	}

	/**
	 * 解析单元格
	 * @param cell
	 * @return
	 */
	protected Object parseCell(Cell cell) {
		Object obj = null;
		int cellType = cell.getCellType();
		switch (cellType) {
		case Cell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// 处理日期格式、时间格式
            if (HSSFDateUtil.isCellDateFormatted(cell)) {  
            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                Date date = cell.getDateCellValue();  
                obj = sdf.format(date);  
            } else if (cell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                double value = cell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil  
                        .getJavaDate(value);  
                obj = sdf.format(date);  
            } else {  
                double value = cell.getNumericCellValue();  
                CellStyle style = cell.getCellStyle();  
                DecimalFormat format = new DecimalFormat();  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("#");  
                }  
                obj = format.format(value);  
            }  
			break;
		default:
			obj = cell.getStringCellValue();
			break;
		}
		return obj;
	}
	
	/**
	 * 生成excel
	 * @param sheetName 表单的名称
	 * @param headers 表单的表头数据
	 * @param data excel的数据
	 */
	public abstract Workbook create(String sheetName,List<String> headers,List<List<Object>> data);
	
	/**
	 * 获取列的样式
	 * @param wb
	 * @return
	 */
	protected CellStyle getCellStyle(Workbook wb) {
		CreationHelper creationHelper = wb.getCreationHelper();
		//设置excel的样式
		CellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd h:mm:ss"));
	    cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
	    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    return cellStyle;
	}
	
	/**
	 * 创建表格
	 * @param wb
	 * @param sheetName excel的名称
	 * @param headers excel的头
	 * @param data excel数据
	 */
	protected void createSheet(Workbook wb,String sheetName,List<String> headers,List<List<Object>> data){
		CellStyle cellStyle = getCellStyle(wb);
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
	        		Object obj = list.get(j);
	        		if(obj instanceof Boolean){
	        			cell.setCellValue((Boolean)obj);
	        		}else if(obj instanceof Date){
	        			cell.setCellValue((Date)obj);
	        		}else if(obj instanceof Double){
	        			cell.setCellValue((Double)obj);
	        		}else{
	        			cell.setCellValue(obj.toString());
	        		}
				}
			}
	        sheet.setDefaultColumnWidth(25);
		}
	}
}
