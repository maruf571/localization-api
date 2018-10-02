package com.maruf.i18n.localization;

import com.maruf.i18n.localization.dto.LocalizationDto;
import com.maruf.i18n.localization.excelbuilder.LocalizationExcelBuilder;
import com.maruf.i18n.localization.service.LocalizationService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/api/protected/localizations")
public class LocalizationApi {

    private final LocalizationService localizationService;
    public LocalizationApi(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @GetMapping("/project/{projectId}/language/{languageId}")
    public ResponseEntity<List<Map<String, Object>>> findAll(@PathVariable Long projectId, @PathVariable Long languageId){
        return ResponseEntity.ok()
                .body(localizationService.findAll(projectId, languageId));
    }

    @GetMapping("/project/{projectId}/language/{languageId}/localization/{localizationId}")
    public ResponseEntity findAll(@PathVariable Long projectId,
                                                   @PathVariable Long languageId,
                                                   @PathVariable Long localizationId){
        return ResponseEntity.ok()
                .body(localizationService.findOne(projectId, languageId, localizationId));
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
    public void delete(@PathVariable Long localizationId){
        localizationService.delete(localizationId);
    }


    @GetMapping("/project/{projectId}/language/{languageId}/export")
    public ModelAndView exportLocalization(@PathVariable Long projectId, @PathVariable Long languageId){

        List<Map<String, Object>> localizationList = localizationService.findAll(projectId, languageId);
        Map<String, Object> data = new HashMap<>();
        data.put("localizationList", localizationList);
        return new ModelAndView(new LocalizationExcelBuilder(), "data", data);
    }


    @PostMapping("/project/{projectId}/language/{languageId}/import")
    public ResponseEntity importLocalization(@PathVariable Long projectId, @PathVariable Long languageId, MultipartFile file){

        try {
            InputStream in = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(in);
            Sheet dataSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dataSheet.iterator();

            Row headerRow = iterator.next(); //header row
            Iterator<Cell> headerCellIterator = headerRow.iterator();
            Cell languageKeyHeader =  headerCellIterator.next();
            Cell languageKeyValueValue =  headerCellIterator.next();

            if(!languageKeyHeader.getStringCellValue().equals("Language Key") || !languageKeyValueValue.getStringCellValue().equals("Language Value")){
                throw new Exception("Header is not matched");
            }

            List<LocalizationDto> localizationDtoList = new ArrayList<>();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                localizationDtoList.add(
                        LocalizationDto.builder()
                                .langKey(currentRow.getCell(0).getStringCellValue())
                                .value(currentRow.getCell(1).getStringCellValue())
                                .languageId(languageId)
                                .projectId(projectId)
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
