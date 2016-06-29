package com.kingbase.bookSearch.core.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * excel工具类
 * @author ganliang
 *
 */
public class ExcelUtil {

	public static final String EXCEL_XLS="application/vnd.ms-excel";
	public static final String EXCEL_XLSX="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	private static final Logger log=Logger.getLogger(ExcelUtil.class);
	/**
	 * @param inputStream
	 * @param excelType
	 * @throws IOException 
	 */
	public static List<List<Object>> parse(InputStream inputStream,String excelType) throws IOException{
		ExcelParser excelParser=null;
		if(EXCEL_XLS.equals(excelType)){
			excelParser=new XLSExcelParser();
		}else if(EXCEL_XLSX.equals(excelType)){
			excelParser=new XLSXExcelParser();
		}else{
			throw new IllegalArgumentException("EXCEL类型【"+excelType+"】不匹配");
		}
		return excelParser.parser(inputStream);
	}
	
	/**
	 * 创建表单
	 * @param sheetName excel的名称
	 * @param headers excel的头名称
	 * @param data excel数据
	 * @param excelType excel的类型 xls xlsx
	 * @throws IOException 
	 */
	public static void create(String sheetName,List<String> headers,List<List<Object>> data,String excelType,String outPath) throws IOException{
		ExcelParser excelParser=null;
		if(EXCEL_XLS.equals(excelType)){
			excelParser=new XLSExcelParser();
		}else if(EXCEL_XLSX.equals(excelType)){
			excelParser=new XLSXExcelParser();
		}else{
			throw new IllegalArgumentException("EXCEL类型【"+excelType+"】不匹配");
		}
		
		Workbook workbook=null;
		FileOutputStream outputStream=null;
		try {
			workbook = excelParser.create(sheetName, headers, data);
			outputStream = new FileOutputStream(new File(outPath));
			workbook.write(outputStream);
		} catch (IOException e) {
			log.error("生成excel出错");
			throw new IOException();
		}finally{
			workbook.close();
			outputStream.close();
		}
	}
	/**
	 * 创建表单
	 * @param sheetName excel的名称
	 * @param headers excel的头名称
	 * @param data excel数据
	 * @param excelType excel的类型 xls xlsx
	 * @return 
	 * @throws IOException 
	 */
	public static Workbook createWorkbook(String sheetName,List<String> headers,List<List<Object>> data,String excelType) throws IOException{
		ExcelParser excelParser=null;
		if(EXCEL_XLS.equals(excelType)){
			excelParser=new XLSExcelParser();
		}else if(EXCEL_XLSX.equals(excelType)){
			excelParser=new XLSXExcelParser();
		}else{
			throw new IllegalArgumentException("EXCEL类型【"+excelType+"】不匹配");
		}
		return excelParser.create(sheetName, headers, data);
	}
}
