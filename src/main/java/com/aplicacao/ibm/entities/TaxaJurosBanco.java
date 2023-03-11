package com.aplicacao.ibm.entities;

import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "juros")
@ExcludeFromJacocoGeneratedReport
public class TaxaJurosBanco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("Mes")
    public String mes;
    @JsonProperty("Modalidade")
    public String modalidade;
    @JsonProperty("Posicao")
    public Integer posicao;
    @JsonProperty("InstituicaoFinanceira")
    public String instituicaoFinanceira;
    @JsonProperty("TaxaJurosAoMes")
    public Double taxaJurosAoMes;
    @JsonProperty("TaxaJurosAoAno")
    public Double taxaJurosAoAno;
    public String cnpj8;
    public String anoMes;

}
