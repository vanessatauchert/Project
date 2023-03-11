package com.aplicacao.ibm.entities;

import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;
import com.aplicacao.ibm.dto.TaxaJurosExternoDto;
@ExcludeFromJacocoGeneratedReport
public class Conversor {

    public static TaxaJurosBanco getEntity(TaxaJurosExternoDto dto){
        return TaxaJurosBanco.builder()
                .mes(dto.getMes())
                .modalidade(dto.getModalidade())
                .posicao(dto.getPosicao())
                .instituicaoFinanceira(dto.getInstituicaoFinanceira())
                .taxaJurosAoMes(dto.getTaxaJurosAoMes())
                .taxaJurosAoAno(dto.getTaxaJurosAoAno())
                .cnpj8(dto.getCnpj8())
                .anoMes(dto.getAnoMes()).build();
    }
}
