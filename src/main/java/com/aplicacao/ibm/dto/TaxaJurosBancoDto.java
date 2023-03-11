package com.aplicacao.ibm.dto;

import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;
import com.aplicacao.ibm.entities.TaxaJurosBanco;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@JsonRootName(value = "value")
@NoArgsConstructor
@Getter
@Setter
@ExcludeFromJacocoGeneratedReport
public class TaxaJurosBancoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull(message = "O campo MES é obrigatório")
    public String mes;
    @NotNull(message = "O campo INSTITUICAO FINANCEIRA é obrigatório")
    @JsonProperty("InstituicaoFinanceira")
    public String instituicaoFinanceira;
    @NotNull(message = "O campo TAXA JUROS AO MES é obrigatório")
    @JsonProperty("TaxaJurosAoMes")
    public Double taxaJurosAoMes;
    @NotNull(message = "O campo TAXA JUROS AO ANO é obrigatório")
    @JsonProperty("TaxaJurosAoAno")
    public Double taxaJurosAoAno;
    @NotNull(message = "O campo CNPJ8 é obrigatório")
    @Size(min = 8, max = 8)
    public String cnpj8;
    @NotNull(message = "O campo ANO MES é obrigatório")
    public String anoMes;

    public TaxaJurosBancoDto(TaxaJurosBanco taxaJurosBanco){
        this.id = taxaJurosBanco.getId();
        this.mes = taxaJurosBanco.getMes();
        this.instituicaoFinanceira = taxaJurosBanco.getInstituicaoFinanceira();
        this.taxaJurosAoMes = taxaJurosBanco.getTaxaJurosAoMes();
        this.taxaJurosAoAno = taxaJurosBanco.getTaxaJurosAoAno();
        this.cnpj8 = taxaJurosBanco.getCnpj8();
        this.anoMes = taxaJurosBanco.getAnoMes();
    }

    public TaxaJurosBancoDto(Long id, String mes, String instituicaoFinanceira, Double taxaJurosAoMes,
                             Double taxaJurosAoAno, String cnpj8, String anoMes) {
        this.id = id;
        this.mes = mes;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.taxaJurosAoMes = taxaJurosAoMes;
        this.taxaJurosAoAno = taxaJurosAoAno;
        this.cnpj8 = cnpj8;
        this.anoMes = anoMes;
    }
}
