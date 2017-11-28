package com.cocosh.framework.excel;

import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
	public static String ConvertCell(Cell cell) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String content=cell.getStringCellValue().toString();
			return content.equals("")?null:content;
		}
		return null;
	}
}
