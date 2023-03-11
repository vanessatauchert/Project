package com.aplicacao.ibm.controller;

import com.aplicacao.ibm.dto.TaxaJurosBancoDto;
import com.aplicacao.ibm.entities.Conversor;
import com.aplicacao.ibm.entities.TaxaJurosBanco;
import com.aplicacao.ibm.repository.TaxaJurosRepository;
import com.aplicacao.ibm.service.TaxaJurosService;
import com.aplicacao.ibm.service.exception.ListNotFoundException;
import com.aplicacao.ibm.service.exception.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaxaJurosBancoControllerTest {

    @InjectMocks
    private TaxaJurosBancoController controller;

    @Mock
    private TaxaJurosService service;

    @Mock
    private TaxaJurosRepository repository;

    @Mock
    private ModelMapper mapper;

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

        PageImpl<TaxaJurosBanco> bancoPage = new PageImpl<>(List.of());
    }

    @Test
    void deveRetornarSucessoDeFindByIds() {
        when(service.findById(anyLong())).thenReturn(banco);
        when(mapper.map(any(),any())).thenReturn(bancoDto);

        ResponseEntity<TaxaJurosBancoDto> response = controller.findByIds(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(TaxaJurosBancoDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(MES, response.getBody().getMes());
        assertEquals(INSTITUICAO_FINANCEIRA, response.getBody().getInstituicaoFinanceira());
        assertEquals(TAXA_JUROS_AO_MES, response.getBody().getTaxaJurosAoMes());
        assertEquals(TAXA_JUROS_AO_ANO, response.getBody().getTaxaJurosAoAno());
        assertEquals(CNPJ_8, response.getBody().getCnpj8());
        assertEquals(ANO_MES, response.getBody().getAnoMes());
    }

    @Test
    void deveRetornarUmaListaDefindAll() {
        when(service.findAll()).thenReturn(List.of(banco));
        when(mapper.map(any(),any())).thenReturn(bancoDto);

        ResponseEntity<List<TaxaJurosBancoDto>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(TaxaJurosBancoDto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(MES, response.getBody().get(INDEX).getMes());
        assertEquals(INSTITUICAO_FINANCEIRA, response.getBody().get(INDEX).getInstituicaoFinanceira());
        assertEquals(TAXA_JUROS_AO_MES, response.getBody().get(INDEX).getTaxaJurosAoMes());
        assertEquals(TAXA_JUROS_AO_ANO, response.getBody().get(INDEX).getTaxaJurosAoAno());
        assertEquals(CNPJ_8, response.getBody().get(INDEX).getCnpj8());
        assertEquals(ANO_MES, response.getBody().get(INDEX).getAnoMes());


    }

    @Test
    void deveRetornarListaPaginaDeFindAlll() {
        PageImpl<TaxaJurosBanco> pageImpl = new PageImpl<>(new ArrayList<>());
        when(service.findAlll((Pageable) any())).thenReturn(pageImpl);
        when(mapper.map(any(),any())).thenReturn(bancoDto);

        ResponseEntity<List<TaxaJurosBancoDto>> response = controller.findAlll(0,5);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        verify(service, times(1)).findAlll((Pageable) any());


    }

    @Test
    void deveRetornarSucessoDeCreate() {
        when(service.create(any())).thenReturn(banco);

        ResponseEntity<TaxaJurosBancoDto> response = controller.create(bancoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
        assertEquals(ResponseEntity.class, response.getClass());

    }

    @Test
    void deveRetornarSucessoDeUpdate() {
        when(service.update(bancoDto)).thenReturn(banco);
        when(mapper.map(any(),any())).thenReturn(bancoDto);

        ResponseEntity<TaxaJurosBancoDto> response = controller.update(ID, bancoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(TaxaJurosBancoDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(MES, response.getBody().getMes());
        assertEquals(INSTITUICAO_FINANCEIRA, response.getBody().getInstituicaoFinanceira());
        assertEquals(TAXA_JUROS_AO_MES, response.getBody().getTaxaJurosAoMes());
        assertEquals(TAXA_JUROS_AO_ANO, response.getBody().getTaxaJurosAoAno());
        assertEquals(CNPJ_8, response.getBody().getCnpj8());
        assertEquals(ANO_MES, response.getBody().getAnoMes());
    }

    @Test
    void deveRetornarSucessoDeDelete() {
        doNothing().when(service).delete(anyLong());

        ResponseEntity<TaxaJurosBancoDto> response = controller.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).delete(anyLong());
    }


    @Test
    void deveRetornarExceptionDeAnoMes() {
        when(service.getAnoMes(anyString())).thenThrow(
                new ListNotFoundException("AnoMes deve ser (yyyy-mm) entre: 2022-11 a 2023-01 - \" +\n" +
                "                    \"Digitado: "));
        when(mapper.map(any(),any())).thenReturn(repository);

        try {
            service.getAnoMes(ANO_MES);
        }catch (Exception ex) {
            assertEquals(ListNotFoundException.class, ex.getClass());
            assertEquals("AnoMes deve ser (yyyy-mm) entre: 2022-11 a 2023-01 - \" +\n" +
                    "                    \"Digitado: ", ex.getMessage());
        }

    }

    @Test
    void deveRetornarListaPaginadaDeAnoMess() {
        when(service.getAnoMess((String) any(), (Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(mapper.map(any(),any())).thenReturn(repository);
        ResponseEntity<List<TaxaJurosBancoDto>> anoMes = controller.anoMesss(ANO_MES, 0, 5);

        assertNotNull(anoMes);
        assertEquals(ResponseEntity.class, anoMes.getClass());
        assertEquals(HttpStatus.OK, anoMes.getStatusCode());
        assertTrue(anoMes.getHeaders().isEmpty());

        verify(service, times(1)).getAnoMess((String) any(), (Pageable) any());
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