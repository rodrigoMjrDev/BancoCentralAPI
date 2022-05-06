package com.ibm.restapibancocentral.repository;

import com.ibm.restapibancocentral.entities.DadosDividaLiquida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DividaLiquidaBCRepository extends JpaRepository<DadosDividaLiquida, Long> {


    List<DadosDividaLiquida> findByData(Date data);

    List<DadosDividaLiquida> findByDataBetween(Date startDate, Date endDate);

    @Query(
            value = "SELECT * FROM tb_divida WHERE data Like %?1%",
            nativeQuery = true)
    List<DadosDividaLiquida> findByYear(@Param ("data") String year);

    @Query(
            value = "SELECT * FROM tb_divida WHERE valor = :valor",
            nativeQuery = true)
    List<DadosDividaLiquida> findByValor2(Double valor);

    List<DadosDividaLiquida> findByValor(Double valor);

}


