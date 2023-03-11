package com.aplicacao.ibm.service;

import com.aplicacao.ibm.dto.TaxaJurosBancoDto;
import com.aplicacao.ibm.entities.TaxaJurosBanco;
import com.aplicacao.ibm.repository.TaxaJurosRepository;
import com.aplicacao.ibm.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaxaJurosServiceTest {

    public static final Long ID = 1L;
    public static final String MES = "Dez-2022";
    public static final String MODALIDADE = "FINANCIAMENTO IMOBILIÁRIO COM TAXAS REGULADAS - PRÉ-FIXADO";
    public static final int POSICAO = 1;
    public static final String INSTITUICAO_FINANCEIRA = "TEST 10 S.A.";
    public static final double TAXA_JUROS_AO_MES = 0.62;
    public static final double TAXA_JUROS_AO_ANO = 7.72;
    public static final String CNPJ_8 = "28127693";
    public static final String ANO_MES = "2022-12";
    public static final String ID_NAO_ENCONTRADO = "ID não encontrado!";
    public static final int INDEX = 0;
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

    @InjectMocks
    private TaxaJurosService service;

    @Mock
    private TaxaJurosRepository repository;

    @Mock
    private ModelMapper mapper;

    private TaxaJurosBanco banco;
    private TaxaJurosBancoDto bancoDto;
    private Optional<TaxaJurosBanco> optionalBanco;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startBanco();

        PageImpl<TaxaJurosBanco> bancoPage = new PageImpl<>(List.of());
    }

    @Test
    void deveRetornarSucessoNoSave() {
        when(repository.save(any())).thenReturn(banco);

        TaxaJurosBanco response = service.save(banco);

        assertNotNull(response);
        assertEquals(TaxaJurosBanco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(MES, response.getMes());
        assertEquals(INSTITUICAO_FINANCEIRA, response.getInstituicaoFinanceira());
        assertEquals(TAXA_JUROS_AO_MES, response.getTaxaJurosAoMes());
        assertEquals(TAXA_JUROS_AO_ANO, response.getTaxaJurosAoAno());
        assertEquals(CNPJ_8, response.getCnpj8());
        assertEquals(ANO_MES, response.getAnoMes());

        verify(repository, times(1)).save(any());
    }

    @Test
    void deveRetornarUmaInstanciaDeTaxaJurosBancoDoFindById() {
        when(repository.findById(anyLong())).thenReturn(optionalBanco);

        TaxaJurosBanco response = service.findById(ID);

        assertNotNull(response);
        assertEquals(TaxaJurosBanco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(MES, response.getMes());
        assertEquals(INSTITUICAO_FINANCEIRA, response.getInstituicaoFinanceira());
        assertEquals(TAXA_JUROS_AO_MES, response.getTaxaJurosAoMes());
        assertEquals(TAXA_JUROS_AO_ANO, response.getTaxaJurosAoAno());
        assertEquals(CNPJ_8, response.getCnpj8());
        assertEquals(ANO_MES, response.getAnoMes());

        verify(repository, times(1)).findById((Long) any());
    }
    @Test
    void deveRetornarObjectNotFoundExceptionDoFinById(){
        when(repository.findById(anyLong())).thenThrow(
                new ObjectNotFoundException(ID_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(ID_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void deveRetornarErroExceptionDoFinById(){
        when(repository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));

        verify(repository, times(1)).findById((Long) any());
    }

    @Test
    void deveRetornarUmaListaDeUsuariosDeFindAll() {
        when(repository.findAll()).thenReturn(List.of(banco));

        List<TaxaJurosBanco> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(TaxaJurosBanco.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(MES, response.get(INDEX).getMes());
        assertEquals(INSTITUICAO_FINANCEIRA, response.get(INDEX).getInstituicaoFinanceira());
        assertEquals(TAXA_JUROS_AO_MES, response.get(INDEX).getTaxaJurosAoMes());
        assertEquals(TAXA_JUROS_AO_ANO, response.get(INDEX).getTaxaJurosAoAno());
        assertEquals(CNPJ_8, response.get(INDEX).getCnpj8());
        assertEquals(ANO_MES, response.get(INDEX).getAnoMes());

        verify(repository, times(1)).findAll();
    }

    @Test
    void deveRetornarUmaListaPaginadaDeFindAlll() {
        PageImpl<TaxaJurosBanco> pageImpl = new PageImpl<>(new ArrayList<>());
        when(repository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<TaxaJurosBanco> listAlll = service.findAlll(null);
        assertSame(pageImpl, listAlll);
        assertTrue(listAlll.toList().isEmpty());

        verify(repository, times(1)).findAll((Pageable) any());


    }

    @Test
    void deveRetornarUmaListaPaginadaDefindByAnoMes() {
        PageImpl<TaxaJurosBanco> pageImpl = new PageImpl<>(new ArrayList<>());
        when(repository.findByAnoMes((String) any(), (Pageable) any())).thenReturn(pageImpl);
        Page<TaxaJurosBanco> listAnoMes = service.getAnoMess(ANO_MES, null);
        assertSame(pageImpl, listAnoMes);
        assertTrue(listAnoMes.toList().isEmpty());

        verify(repository, times(1)).findByAnoMes((String) any(), (Pageable) any());


    }

    @Test
    void deveRetornarUmaListaDefindByAnoMes() {
        when(repository.listAnoMes((String) any())).thenReturn(new ArrayList<>());
        List<TaxaJurosBanco> listAnoMes = service.getAnoMes(ANO_MES);

        verify(repository, times(1)).listAnoMes((String) any());


    }

    @Test
    void deveRetornaSucessoDoDTO(){
        when(mapper.map((Object) any(), (Class<TaxaJurosBancoDto>) any())).thenReturn(bancoDto);

        assertSame(bancoDto, service.toFilterDto(banco));

        verify(mapper).map((Object) any(), (Class<TaxaJurosBancoDto>)any());

    }

    @Test
    void deveRetornarSucessoDeCreate() {
        when(repository.save(any())).thenReturn(banco);

        TaxaJurosBanco response = service.create(bancoDto);

        assertNotNull(response);
        assertEquals(TaxaJurosBanco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(MES, response.getMes());
        assertEquals(INSTITUICAO_FINANCEIRA, response.getInstituicaoFinanceira());
        assertEquals(TAXA_JUROS_AO_MES, response.getTaxaJurosAoMes());
        assertEquals(TAXA_JUROS_AO_ANO, response.getTaxaJurosAoAno());
        assertEquals(CNPJ_8, response.getCnpj8());
        assertEquals(ANO_MES, response.getAnoMes());

        verify(repository, times(1)).save(any());
    }

    @Test
    void deveRetornarSucessoDeUpdate() {
        when(repository.save(any())).thenReturn(banco);
        when(repository.findById(anyLong())).thenReturn(optionalBanco);

        TaxaJurosBanco response = service.update(bancoDto);

        assertNotNull(response);
        assertEquals(TaxaJurosBanco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(MES, response.getMes());
        assertEquals(INSTITUICAO_FINANCEIRA, response.getInstituicaoFinanceira());
        assertEquals(TAXA_JUROS_AO_MES, response.getTaxaJurosAoMes());
        assertEquals(TAXA_JUROS_AO_ANO, response.getTaxaJurosAoAno());
        assertEquals(CNPJ_8, response.getCnpj8());
        assertEquals(ANO_MES, response.getAnoMes());

    }


    @Test
    void deveRetornarObjectNotFoundExceptionDeUpdate() {
        when(repository.findById(anyLong())).thenReturn(optionalBanco);

        try{
            optionalBanco.get().setId(2L);
            service.create(bancoDto);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(ID_NAO_ENCONTRADO, ex.getMessage());
        }
    }


    @Test
    void deveRetornarSucessoNoDelete() {
        when(repository.findById(anyLong())).thenReturn(optionalBanco);
        doNothing().when(repository).deleteById(anyLong());
        service.delete(ID);

        verify(repository, times(1)).deleteById(anyLong());
    }


    @Test
    void deveRetornarObjectNotFoundExceptionDeDelete() {
        when(repository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
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