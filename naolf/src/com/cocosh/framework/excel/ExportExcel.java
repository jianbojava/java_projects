package com.cocosh.framework.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cocosh.framework.util.StringUtil;

public class ExportExcel {

	private Workbook workbook;

	private Sheet sheet;

	private int rownum;// 当前行号

	private List<Integer> colNumList = new ArrayList<Integer>();// 记录需要统计的列号集合

	List<Object[]> annotationList;

	Class<?> cls;

	/**
	 * 构造一个EXCEL
	 * 
	 * @param cls
	 */
	public ExportExcel(Class<?> cls) {
		this.cls = cls;
		this.annotationList = new ArrayList<Object[]>();
		// 获得注解
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(ExcelField.class)) {
				ExcelField ef = method.getAnnotation(ExcelField.class);
				annotationList.add(new Object[] { ef, method.getName() });
			}
		}
		Collections.sort(annotationList, new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				return new Integer(((ExcelField) o1[0]).sort())
						.compareTo(new Integer(((ExcelField) o2[0]).sort()));
			};
		});
		this.init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		this.rownum = 0;
		this.workbook = new HSSFWorkbook();
		this.sheet = workbook.createSheet("列表");
		// 增加标题行
		Row headerRow = sheet.createRow(rownum++);
		for (int i = 0; i < annotationList.size(); i++) {
			ExcelField excelField = (ExcelField) annotationList.get(i)[0];
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(excelField.title());
			// 设置列宽
			sheet.setColumnWidth(i, excelField.width() * 256);
			// 记录需要统计的列号数
			if (((ExcelField) annotationList.get(i)[0]).isSum()) {
				colNumList.add(i);
			}
		}
	}

	/**
	 * 增加一行
	 * 
	 * @return
	 */
	public Row addRow() {
		return sheet.createRow(rownum++);
	}

	/**
	 * 添加一个单元格
	 * 
	 * @param row
	 * @param colunm
	 * @param val
	 * @return
	 */
	public Cell addCell(Row row, int colunm, Object val) {
		Cell cell = row.createCell(colunm);
		try {
			if (val == null) {
				cell.setCellValue("");
			} else if (val instanceof String) {
				cell.setCellValue((String) val);
				// 从NC过来的字段类型均为字符串，导出时需要对其转换成数字类型，统计时也需要
				String numType = ((ExcelField) annotationList.get(colunm)[0])
						.numType();
				if (numType.equals("Integer")) {
					cell.setCellValue(Integer.valueOf(val.toString()));
				} else if (numType.equals("Double")) {
					cell.setCellValue(Double.valueOf(val.toString()));
				}
			} else if (val instanceof Integer) {
				cell.setCellValue((Integer) val);
			} else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			} else if (val instanceof Character) {
				cell.setCellValue((String) val);
			} else {
				cell.setCellValue(val + "");
			}
		} catch (Exception ex) {
			cell.setCellValue(val.toString());
		}
		return cell;
	}

	/**
	 * 添加数据
	 * 
	 * @param <E>
	 * @param list
	 * @return
	 */
	public <E> ExportExcel setData(List<E> list) {
		if(list!=null){
			Object val = null;
			Map<Object, Integer> groupMap = new HashMap<Object, Integer>();
			int i = 0;
			int count = 0;
			int sumCount = 0;
			String sumTitle = "";//注解中的分组统计标题，默认为小计
			String preValue="";//上一组的分组字段值
			ExcelField excelField = (ExcelField) annotationList.get(0)[0];
			if (excelField.isGroupSum()) {
				// 根据第一列的值进行分组统计
				sumTitle = excelField.groupSumTitle();
			}
			//有小计的情况下，合计需重新计算
			List<Integer> colList=new ArrayList<Integer>();
			for (E e : list) {
				int column = 0;
				// 获取第一列数据的值，根据此值来进行分组统计
				String title ="";
				Object value = null;
				if (excelField.isGroupSum()) {
					value = getVal(annotationList.get(0), value, e);
					if(i==0){
					   preValue=value.toString();
					}
					title = StringUtil.format(sumTitle, preValue);
					// 分组进行统计数据
					if (groupMap.containsKey(value)) {
						groupMap.put(value, groupMap.get(value) + 1);
						count = groupMap.get(value);
						preValue=value.toString();
					} else {
						groupMap.put(value, 1);
						if (i != 0) {
							// 添加小计行统计
							addSumRowCell(title, i - count + 2 + sumCount, i + 1 + sumCount);
							sumCount++;
							colList.add(i + 1 + sumCount);
						}
					}
				}
				// 数据列
				Row row = this.addRow();
				for (Object[] obj : annotationList) {
					val = getVal(obj, val, e);
					this.addCell(row, column++, val);
				}
				// 最后一组添加小计进行统计
				if (i == list.size() - 1 && sumCount != 0) {
					addSumRowCell(title, i - count + 3 + sumCount, i + 2 + sumCount);
					colList.add(i + 3 + sumCount);
				}
				i++;
			}
			// 添加合计行
			if (colNumList.size() > 0 && colList.size()==0) {
				addSumRowCell("合计", 2, list.size() + 1);
			}
			//小计行的最后合计需特殊处理
			if(colList.size() > 0){
				Row totalRow = this.addRow();
				Cell talCell = totalRow.createCell(0);
				talCell.setCellValue("合计");
				Integer num = colNumList.get(0);
				Cell totalCell = totalRow.createCell(num);
				totalCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
				String colNum = getColumnNum(num + 1);
				String expression = "";
				for (Integer rn : colList) {
					expression += colNum + rn + "+";
				}
				if (!StringUtil.isEmpty(expression)) {
					expression = expression.substring(0, expression.length() - 1);
				}
				totalCell.setCellFormula(expression);
			}
		}
		return this;
	}

	/**
	 * 获取某单元格的值
	 * 
	 * @param obj
	 * @param val
	 * @param e
	 * @return
	 */
	public Object getVal(Object[] obj, Object val, Object e) {
		try {
			Method method = cls.getDeclaredMethod((String) obj[1],
					new Class[] {});
			val = method.invoke(e, new Object[] {});
		} catch (Exception ex) {
			ex.printStackTrace();
			val = "";
		}
		return val;
	}

	/**
	 * 添加统计行
	 * 
	 * @param cellTitle
	 * @param startIndex
	 * @param endIndex
	 */
	public void addSumRowCell(String cellTitle, int startIndex, int endIndex) {
		Row totalRow = this.addRow();
		Cell talCell = totalRow.createCell(0);
		talCell.setCellValue(cellTitle);
		for (Integer num : colNumList) {
			Cell totalCell = totalRow.createCell(num);
			// 公式
			totalCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			String colNum = getColumnNum(num + 1);
			String expression = "SUM(" + colNum + startIndex + ":" + colNum
					+ endIndex + ")";
			totalCell.setCellFormula(expression);
		}
	}

	/**
	 * 输出到客户端
	 * 
	 * @param response
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public ExportExcel write(HttpServletResponse response, String fileName)
			throws IOException {
		response.reset();
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fileName.getBytes("gb2312"), "iso8859-1"));
		write(response.getOutputStream());
		return this;
	}

	/**
	 * 输出到文件
	 * 
	 * @param name
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ExportExcel writeFile(String name) throws FileNotFoundException,
			IOException {
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}

	/**
	 * 输出数据流
	 * 
	 * @param os
	 * @return
	 * @throws IOException
	 */
	public ExportExcel write(OutputStream os) throws IOException {
		workbook.write(os);
		return this;
	}

	/**
	 * 数字转换成相应的字母列号，如A、B、C等
	 * 
	 * @param i
	 * @return name
	 */
	public static String getColumnNum(int i) {
		String strResult = "";
		int intRound = i / 26;
		int intMod = i % 26;
		if (intRound != 0) {
			strResult = String.valueOf(((char) (intRound + 64)));
		}
		strResult += String.valueOf(((char) (intMod + 64)));
		return strResult;
	}
}
