package com.maruf.i18n.localization.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDto {

    private String id;

    @NotNull
    private String langKey;

    private String value;

    @NotNull
    private String languageId;

    private String projectId;
}
