package com.maruf.localization.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface ImportExportService {

    ModelAndView exportLocalization(String languageId);

    void importLocalization(String languageId, MultipartFile file);
}
