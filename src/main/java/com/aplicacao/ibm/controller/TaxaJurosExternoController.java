package com.aplicacao.ibm.controller;


import com.aplicacao.ibm.client.TaxaFeignClient;
import com.aplicacao.ibm.dto.ListExternoDto;
import com.aplicacao.ibm.dto.TaxaJurosExternoDto;
import com.aplicacao.ibm.entities.Conversor;
import com.aplicacao.ibm.entities.TaxaJurosBanco;
import com.aplicacao.ibm.service.TaxaJurosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/externa/v1")
@Tag(name = "API Externa", description = "endpoints")
public class TaxaJurosExternoController {

    @Autowired
    private TaxaFeignClient taxaFeignClient;

    @Autowired
    private TaxaJurosService service;


    @GetMapping("/listar")
    @Operation(summary = "Lista todos os dados da API externa")
    public ResponseEntity<ListExternoDto> get() {
        ListExternoDto obj = taxaFeignClient.getAllData();
        return obj != null ?
                ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
    }

    @PostMapping("/salvar")
    @Operation(summary = "Salva no banco todos os dados da API externa")
    public ResponseEntity<ListExternoDto> postAll() {
        ListExternoDto obj = taxaFeignClient.getAllData();

        for (TaxaJurosExternoDto x : obj.getValue()) {
            TaxaJurosBanco taxaJurosBanco = Conversor.getEntity(x);
            service.save(taxaJurosBanco);
        }

        return ResponseEntity.ok(obj);
    }
}
