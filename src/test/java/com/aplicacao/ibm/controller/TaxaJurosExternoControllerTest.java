package com.aplicacao.ibm.controller;

import com.aplicacao.ibm.client.TaxaFeignClient;
import com.aplicacao.ibm.dto.ListExternoDto;
import com.aplicacao.ibm.dto.TaxaJurosBancoDto;
import com.aplicacao.ibm.entities.TaxaJurosBanco;
import com.aplicacao.ibm.repository.TaxaJurosRepository;
import com.aplicacao.ibm.service.TaxaJurosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TaxaJurosExternoControllerTest {

    @InjectMocks
    private TaxaJurosExternoController controller;

    @Mock
    private TaxaJurosService service;

    @Mock
    private TaxaJurosRepository repository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private TaxaFeignClient feignClient;

    private TaxaJurosBanco banco;
    private TaxaJurosBancoDto bancoDto;
    private Optional<TaxaJurosBanco> optionalBanco;

    public static final Long ID = 1L;
    public static final String MES = "Dez-2022";
    public static final String MODALIDADE = "FINANCIAMENTO IMOBILIÁRIO COM TAXAS REGULADAS - PRÉ-FIXADO";
    public static final int POSICAO = 1;
    public static final String INSTITUICAO_FINANCEIRA = "TEST 10 S.A.";
    public static final double TAXA_JUROS_AO_MES = 0.62;
    public static final double TAXA_JUROS_AO_ANO = 7.72;
    public static final String CNPJ_8 = "28127693";
    public static final String ANO_MES = "2022-12";

    public static final int INDEX = 0;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startBanco();
    }

    @Test
    void get() {
        ListExternoDto listDto = new ListExternoDto();
        listDto.setValue(new ArrayList<>());

        when(feignClient.getAllData()).thenReturn(listDto);

        ResponseEntity<ListExternoDto> response = controller.get();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ListExternoDto.class, response.getBody().getClass());
    }

    @Test
    void postAll() {
        ListExternoDto listDto = new ListExternoDto();
        listDto.setValue(new ArrayList<>());

        when(feignClient.getAllData()).thenReturn(listDto);

        ResponseEntity<ListExternoDto> response = controller.postAll();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ListExternoDto.class, response.getBody().getClass());

        assertSame(listDto, feignClient.getAllData());
    }


    private void startBanco() {
        banco = new TaxaJurosBanco(ID, MES, MODALIDADE, POSICAO, INSTITUICAO_FINANCEIRA, TAXA_JUROS_AO_MES,
                TAXA_JUROS_AO_ANO, CNPJ_8, ANO_MES);
        bancoDto = new TaxaJurosBancoDto(ID, MES, INSTITUICAO_FINANCEIRA, TAXA_JUROS_AO_MES, TAXA_JUROS_AO_ANO,
                CNPJ_8, ANO_MES);
        optionalBanco = Optional.of(new TaxaJurosBanco(ID, MES, MODALIDADE, POSICAO, INSTITUICAO_FINANCEIRA,
                TAXA_JUROS_AO_MES, TAXA_JUROS_AO_ANO, CNPJ_8, ANO_MES));


    }
}