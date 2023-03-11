package com.aplicacao.ibm.dto;

import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@JsonRootName(value = "value")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ExcludeFromJacocoGeneratedReport
public class TaxaJurosExternoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("Mes")
    public String mes;
    @JsonProperty("Modalidade")
    public String modalidade;
    @JsonProperty("Posicao")
    public int posicao;
    @JsonProperty("InstituicaoFinanceira")
    public String instituicaoFinanceira;
    @JsonProperty("TaxaJurosAoMes")
    public double taxaJurosAoMes;
    @JsonProperty("TaxaJurosAoAno")
    public double taxaJurosAoAno;
    public String cnpj8;
    public String anoMes;


}
