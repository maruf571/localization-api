package com.maruf.localization.service;

import com.maruf.localization.api.dto.LocalizationDto;
import com.maruf.localization.excelbuilder.LocalizationExcelBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImportExportServiceImpl implements ImportExportService{

    private final LocalizationService localizationService;

    @Override
    public ModelAndView exportLocalization(String languageId) {
        List<Map<String, Object>> localizationList = localizationService.findAll(languageId);
        Map<String, Object> data = new HashMap<>();
        data.put("localizationList", localizationList);
        return new ModelAndView(new LocalizationExcelBuilder(), "data", data);
    }

    @Override
    public void importLocalization(String languageId, MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(in);
            Sheet dataSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dataSheet.iterator();

            Row headerRow = iterator.next(); //header row
            Iterator<Cell> headerCellIterator = headerRow.iterator();
            Cell languageKeyHeader =  headerCellIterator.next();
            Cell languageKeyValueValue =  headerCellIterator.next();

            if(!languageKeyHeader.getStringCellValue().equals(LocalizationExcelBuilder.KEY_COLUMN_NAME) ||
                    !languageKeyValueValue.getStringCellValue().equals(LocalizationExcelBuilder.VALUE_COLUMN_NAME)){
                throw new Exception("Header name is not matched");
            }

            List<LocalizationDto> localizationDtoList = new ArrayList<>();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                localizationDtoList.add(
                        LocalizationDto.builder()
                                .langKey(currentRow.getCell(0).getStringCellValue())
                                .value(currentRow.getCell(1) == null ? "" : currentRow.getCell(1).getStringCellValue())
                                .languageId(languageId)
                                .build()
                );
            }

            localizationService.importToLocalization(localizationDtoList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
