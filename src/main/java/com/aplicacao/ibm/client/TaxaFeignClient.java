package com.aplicacao.ibm.client;

import com.aplicacao.ibm.dto.ListExternoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "post",
        url = "https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/odata/TaxasJurosMensalPorMes?$top=100&$format=json")
public interface TaxaFeignClient {
    @GetMapping(value = "")
    ListExternoDto getAllData();
}
