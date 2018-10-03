package com.maruf.i18n.localization.excelbuilder;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public class LocalizationExcelBuilder extends AbstractXlsxView {


    @Override
    @SuppressWarnings(value="unchecked")
    protected void buildExcelDocument(Map<String, Object> map,
                                      Workbook workbook,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse){

        //get data back from controller
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        List<Map<String, Object>> localizationList = (List<Map<String, Object>>) data.get("localizationList");

        //setup excel xls sheet
        Sheet sheet = workbook.createSheet("localization_file");
        sheet.setDefaultColumnWidth(30);
        sheet.createFreezePane(0, 1);

        CellStyle locaCellStyle = workbook.createCellStyle();
        locaCellStyle.setLocked(false);



        // excel header row
        int cellNumber = 0;
        Row header = sheet.createRow(0);
        header.createCell(cellNumber++).setCellValue("Language Key");
        header.createCell(cellNumber).setCellValue("Language Value");



        //write language key and value to excel row
        int rowCount = 1;
        for(Map<String, Object> localizationMap : localizationList){
            Row localizationRow =  sheet.createRow(rowCount++);
            localizationRow.createCell(0).setCellValue((String) localizationMap.get("langKey"));
            localizationRow.createCell(1).setCellValue((String) localizationMap.get("value"));
        }

    }
}
