package com.maruf.i18n.localization;

import com.maruf.i18n.localization.dto.LocalizationDto;
import com.maruf.i18n.localization.excelbuilder.LocalizationExcelBuilder;
import com.maruf.i18n.localization.service.LocalizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/protected/localizations")
public class LocalizationApi {

    private final LocalizationService localizationService;
    public LocalizationApi(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @GetMapping("/language/{languageId}")
    public ResponseEntity<List<Map<String, Object>>> findAll(@PathVariable String languageId){
        return ResponseEntity.ok()
                .body(localizationService.findAll(languageId));
    }


    @GetMapping("/{localizationId}/language/{languageId}")
    public ResponseEntity findOne(@PathVariable String languageId, @PathVariable String localizationId){
        return ResponseEntity.ok()
                .body(localizationService.findOne(localizationId, languageId));
    }

    @PostMapping
    public ResponseEntity<LocalizationDto> create(@RequestBody LocalizationDto localizationDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(localizationService.create(localizationDto));
    }

    @PutMapping
    public ResponseEntity<LocalizationDto> update(@RequestBody LocalizationDto localizationDto){
        return ResponseEntity.ok()
                .body(localizationService.update(localizationDto));
    }

    @DeleteMapping("/{localizationId}")
    public void delete(@PathVariable String localizationId){
        localizationService.delete(localizationId);
    }


    @GetMapping("/language/{languageId}/export")
    public ModelAndView exportLocalization(@PathVariable String languageId){
        log.debug("languageId {}", languageId);

        List<Map<String, Object>> localizationList = localizationService.findAll(languageId);
        Map<String, Object> data = new HashMap<>();
        data.put("localizationList", localizationList);
        return new ModelAndView(new LocalizationExcelBuilder(), "data", data);
    }


    @PostMapping("/language/{languageId}/import")
    public ResponseEntity importLocalization(@PathVariable String languageId, @RequestParam("file") MultipartFile file){

        System.out.println(file.getName());
        try {
            InputStream in = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(in);
            Sheet dataSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dataSheet.iterator();

            Row headerRow = iterator.next(); //header row
            Iterator<Cell> headerCellIterator = headerRow.iterator();
            Cell languageKeyHeader =  headerCellIterator.next();
            Cell languageKeyValueValue =  headerCellIterator.next();

            if(!languageKeyHeader.getStringCellValue().equals(LocalizationExcelBuilder.KEY_COLUMN_NAME) || !languageKeyValueValue.getStringCellValue().equals(LocalizationExcelBuilder.VALUE_COLUMN_NAME)){
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

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
