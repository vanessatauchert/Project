package com.aplicacao.ibm.controller;


import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;
import com.aplicacao.ibm.client.TaxaFeignClient;

import com.aplicacao.ibm.dto.TaxaJurosBancoDto;
import com.aplicacao.ibm.repository.TaxaJurosRepository;
import com.aplicacao.ibm.service.TaxaJurosService;
import com.aplicacao.ibm.service.exception.ListNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/banco/v1")
@Tag(name = "API MySQL", description = "endpoints")
public class TaxaJurosBancoController {

    public static final String ID = "/{id}";
    public static final String ANO_MES = "{anoMes}";

    @Autowired
    private TaxaFeignClient taxaFeignClient;

    @Autowired
    private TaxaJurosService service;

    @Autowired
    private TaxaJurosRepository repository;

    @Autowired
    private ModelMapper mapper;


    @GetMapping("/id" + ID)
    @Operation(summary = "Busca registro por Id")
    public ResponseEntity<TaxaJurosBancoDto> findByIds(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), TaxaJurosBancoDto.class));
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os registros do banco")
    public ResponseEntity<List<TaxaJurosBancoDto>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll()
                        .stream().map(obj ->  mapper.map(obj,TaxaJurosBancoDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/listarPg")
    @Operation(summary = "Lista todos os registros do banco paginado")
    public ResponseEntity<List<TaxaJurosBancoDto>>
    findAlll(@RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "size", defaultValue = "10")Integer size) {
        return ResponseEntity.ok().body(service.findAlll(PageRequest.of(page, size))
                .stream().map(obj ->  mapper.map(obj,TaxaJurosBancoDto.class)).collect(Collectors.toList()));
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo registro")
    public ResponseEntity<TaxaJurosBancoDto> create(@Valid @RequestBody TaxaJurosBancoDto objDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("id" + ID)
                .buildAndExpand(service.create(objDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/update" + ID)
    @Operation(summary = "Atualiza um registro pelo id")
    public ResponseEntity<TaxaJurosBancoDto> update(@PathVariable Long id,
                                                    @Valid @RequestBody TaxaJurosBancoDto objDTO){
        objDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(objDTO), TaxaJurosBancoDto.class));
    }

    @DeleteMapping("/delete" + ID)
    @Operation(summary = "Deleta um registro pelo id")
    public ResponseEntity<TaxaJurosBancoDto> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExcludeFromJacocoGeneratedReport
    @GetMapping("/anoMes/" + ANO_MES)
    @Operation(summary = "Filtra com base no anoMes (yyyy-dd)")
    public ResponseEntity<List<TaxaJurosBancoDto>> anoMes(@PathVariable String anoMes) {
        List<TaxaJurosBancoDto> listDto = service.getAnoMes(anoMes).stream()
                .map( obj -> mapper.map(obj, TaxaJurosBancoDto.class)).collect(Collectors.toList());

        if( listDto.isEmpty()){
            throw new ListNotFoundException("AnoMes deve ser (yyyy-mm) entre: 2022-11 a 2023-01 - " +
                    "Digitado: "+ anoMes);
        }

        return ResponseEntity.ok().body(listDto);

    }

    @GetMapping("/anoMesPg/" + ANO_MES)
    @Operation(summary = "Filtra com base no anoMes (yyyy-dd)")
    public ResponseEntity<List<TaxaJurosBancoDto>> anoMesss(
            @PathVariable String anoMes,
            @RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "size", defaultValue = "10")Integer size) {

        return ResponseEntity.ok().body(service.getAnoMess(anoMes, PageRequest.of(page, size)).stream()
                .map(obj -> mapper.map(obj, TaxaJurosBancoDto.class)).collect(Collectors.toList()));

    }






    //    @GetMapping("/id/{id}")
//    @Operation(summary = "Busca registro por Id")
//    public ResponseEntity<TaxaJurosBancoDto> findById(@PathVariable("id") Long id) {
//        TaxaJurosBanco obj = service.findById(id);
//        return ResponseEntity.ok().body(new TaxaJurosBancoDto(obj));
//    }

//    @GetMapping("/anoMes/{anoMes}")
//    @Operation(summary = "Filtra com base no anoMes (yyyy-dd) usando Query")
//    public ResponseEntity<List<TaxaJurosBancoDto>> anoMes(@PathVariable String anoMes){
//        List<TaxaJurosBancoDto> listDTO = repository.listAnoMes(anoMes).stream().map(TaxaJurosBancoDto::new)
//                .collect(Collectors.toList());
//
//        if( listDTO.isEmpty()){
//            throw new ListNotFoundException("AnoMes deve ser (yyyy-mm) entre: 2022-11 a 2023-01 - " +
//                    "Digitado: "+ anoMes);
//        }
//        return ResponseEntity.ok().body(listDTO);
//
//    }

    //Retorna anoMes pelo Service



    // ---------------------------------------------------------------------------------------------------


//    @GetMapping("/anoMess/{anoMes}")
//    @Operation(summary = "Filtra com base no anoMes (yyyy-dd) Paginado")
//    public ResponseEntity<List<TaxaJurosBancoDto>> anoMess(
//            @PathVariable String anoMes, @RequestParam(value = "page", defaultValue = "0")Integer page,
//            @RequestParam(value = "size", defaultValue = "10")Integer size){
//
//        List<TaxaJurosBancoDto> listDTO = repository.listAnoMes(anoMes).stream().map(TaxaJurosBancoDto::new)
//                .collect(Collectors.toList());
//
//        if( listDTO.isEmpty()){
//            throw new ListNotFoundException("AnoMes deve ser (yyyy-mm) entre: 2022-11 a 2023-01 - " +
//                    "Digitado: "+ anoMes);
//        }
//        return ResponseEntity.ok().body(listDTO);
//    }


    // ---------------------------------------------------------------------------------------------------



    //Retorna mockado a query anoMes

    //    @GetMapping("/anoMes")
//    public List<TaxaJurosBanco> anoMes() {
//        return repository.tx();
//    }

    // ---------------------------------------------------------------------------------------------------

//    @GetMapping("/anoMes/{anoMes}")
//    public ResponseEntity<List<TaxaJurosBanco>> anoMess(@PathVariable String anoMes) {
//        List<TaxaJurosBanco> obj = repository.txx(anoMes);
//        return obj != null ? ResponseEntity.ok().body(obj) : ResponseEntity.notFound().build();
//    }

    // ---------------------------------------------------------------------------------------------------





}
