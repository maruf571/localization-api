package com.maruf.i18n.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDto {

    private Long id;

    private Long languageId;

    private String langKey;

    private String value;
}
