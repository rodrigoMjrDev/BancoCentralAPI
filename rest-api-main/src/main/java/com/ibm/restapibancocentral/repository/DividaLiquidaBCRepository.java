package com.ibm.restapibancocentral.repository;

import com.ibm.restapibancocentral.domain.DividaLiquidaBC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DividaLiquidaBCRepository extends JpaRepository<DividaLiquidaBC, Long> {


    List<DividaLiquidaBC> findByData(Date data);

    List<DividaLiquidaBC> findByDataBetween(Date startDate, Date endDate);

    @Query(
            value = "SELECT * FROM tb_divida WHERE data Like %?1%",
            nativeQuery = true)
    List<DividaLiquidaBC> findByYear(@Param ("data") String year);

    @Query(
            value = "SELECT * FROM tb_divida WHERE valor = :valor",
            nativeQuery = true)
    List<DividaLiquidaBC> findByValor2(Double valor);

    List<DividaLiquidaBC> findByValor(Double valor);

}


