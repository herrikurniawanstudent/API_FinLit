package com.skripsi.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaterialProgressDto {
    private Long subModuleId;
    private Integer lastCompletedOrder;
}
