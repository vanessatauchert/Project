package com.aplicacao.ibm.service;

import com.aplicacao.ibm.dto.TaxaJurosBancoDto;
import com.aplicacao.ibm.entities.Conversor;
import com.aplicacao.ibm.entities.TaxaJurosBanco;
import com.aplicacao.ibm.repository.TaxaJurosRepository;
import com.aplicacao.ibm.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TaxaJurosService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TaxaJurosRepository repository;

    public TaxaJurosBanco save(TaxaJurosBanco taxaJurosBanco){
        return repository.save(taxaJurosBanco);
    }


    public TaxaJurosBanco findById(Long id) {
        Optional<TaxaJurosBanco> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado!"));
    }

    public Page<TaxaJurosBanco> findAlll(Pageable pageable) {

        return repository.findAll(pageable);

    }

    public List<TaxaJurosBanco> findAll() {

        return repository.findAll();

    }
    public TaxaJurosBanco create(TaxaJurosBancoDto objDto) {
        objDto.setId(null);
        return repository.save(mapper.map(objDto, TaxaJurosBanco.class));
    }

    public TaxaJurosBanco update(TaxaJurosBancoDto objDTO) {
//        findById(objDTO.getId()); caso queira retornar uma exception
        return repository.save(mapper.map(objDTO, TaxaJurosBanco.class));

    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

//    Retorna lista anoMes sem a Query

    public Page<TaxaJurosBanco> getAnoMess(String anoMes, Pageable pageable) {
        return repository.findByAnoMes(anoMes, pageable);
    }

    public List<TaxaJurosBanco> getAnoMes(String anoMes) {
        return repository.listAnoMes(anoMes);
    }

    public TaxaJurosBancoDto toFilterDto(TaxaJurosBanco taxaJurosBanco) {
        return mapper.map(taxaJurosBanco, TaxaJurosBancoDto.class);
    }




//    public TaxaJurosBanco create(TaxaJurosBancoDto objDto) {
//        objDto.setId(null);
//        TaxaJurosBanco newObj = new TaxaJurosBanco(objDto);
//        return repository.save(newObj);
//    }



//    public TaxaJurosBanco update(Long id, TaxaJurosBancoDto objDTO) {
//        objDTO.setId(id);
//        TaxaJurosBanco oldObj = findById(id);
//        oldObj = new TaxaJurosBanco(objDTO);
//        return repository.save(oldObj);
//
//    }




}
