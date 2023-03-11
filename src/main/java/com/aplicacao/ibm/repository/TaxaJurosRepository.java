package com.aplicacao.ibm.repository;

import com.aplicacao.ibm.entities.TaxaJurosBanco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxaJurosRepository extends JpaRepository<TaxaJurosBanco, Long> {

    //Retorna uma query anoMes a partir do DTO
    @Query(value = "SELECT * from apijuros.juros u where u.anoMes=:anoMes", nativeQuery = true)
    public List<TaxaJurosBanco> listAnoMes(@Param("anoMes") String anoMes);

    //Retorna um anoMes a partir do service e do DTO
    Page<TaxaJurosBanco> findByAnoMes(String anoMes, Pageable pageable);






//    @Query(value = "SELECT * from apijuros.juros u where u.anoMes=:anoMes", nativeQuery = true)
//    public List<TaxaJurosBanco> listAnoMess(@Param("anoMes") String anoMes, Pageable pageable);
    // ---------------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------

    //Retorna mockado a query anoMes
//    @Query(value = "SELECT * from apijuros.juros where anoMes like  '2022-11%'", nativeQuery = true)
//    public List<TaxaJurosBanco> tx();

    // ---------------------------------------------------------------------------------------------------

    //Retorna uma query anoMes a partir da entity TaxaJurosBanco

//    @Query(value = "SELECT * from apijuros.juros u where u.anoMes=:anoMes", nativeQuery = true)
//    public List<TaxaJurosBanco> txx(@Param("anoMes") String anoMes);
    // ---------------------------------------------------------------------------------------------------
//



}
