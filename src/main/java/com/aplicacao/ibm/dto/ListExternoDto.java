package com.aplicacao.ibm.dto;

import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@ExcludeFromJacocoGeneratedReport
@lombok.Generated
public class ListExternoDto {
    private List<TaxaJurosExternoDto> value;
}
