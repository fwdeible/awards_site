package com.project.awardParser.excel;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.awardParser.model.Award;

public class ExcelGenerator {

	public static XSSFWorkbook generateWorkbookFromListOfAwards(List<Award> list) {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet();

		for (int i = 0; i < list.size(); i++) {
			XSSFRow row = sheet.createRow((short)i);
			Award award = list.get(i);
			row.createCell(0).setCellValue(award.getId());
			row.createCell(1).setCellValue(award.getAwardName());
		}
		
		return workbook;

	}
}
