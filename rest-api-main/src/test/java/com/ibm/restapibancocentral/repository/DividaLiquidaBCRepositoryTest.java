package com.ibm.restapibancocentral.repository;

import com.ibm.restapibancocentral.domain.DividaLiquidaBC;
import com.ibm.restapibancocentral.util.DividaLiquidaBCCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

@DataJpaTest
@DisplayName("Tests for divida Repository")
@Log4j2
public class DividaLiquidaBCRepositoryTest {

    @Autowired
    private DividaLiquidaBCRepository dividaLiquidaBCRepository;

    @Test
    @DisplayName("Save persists divida when Successful")
    void save_PersistDivida_WhenSuccessful(){
        DividaLiquidaBC dividaToBeSaved = DividaLiquidaBCCreator.createDividaToBeSaved();

        DividaLiquidaBC dividaSaved = this.dividaLiquidaBCRepository.save(dividaToBeSaved);

        Assertions.assertThat(dividaSaved).isNotNull();

        Assertions.assertThat(dividaSaved.getId()).isNotNull();

        Assertions.assertThat(dividaSaved.getValor()).isEqualTo(dividaToBeSaved.getValor());
        Assertions.assertThat(dividaSaved.getData()).isEqualTo(dividaToBeSaved.getData());
    }

    @Test
    @DisplayName("Save updates divida when Successful")
    void save_UpdatesDivida_WhenSuccessful(){
        DividaLiquidaBC dividaToBeSaved = DividaLiquidaBCCreator.createDividaToBeSaved();

        DividaLiquidaBC dividaSaved = this.dividaLiquidaBCRepository.save(dividaToBeSaved);

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        dividaSaved.setValor(1.20);
        dividaSaved.setData(date);

        DividaLiquidaBC dividaUpdated = this.dividaLiquidaBCRepository.save(dividaSaved);

        Assertions.assertThat(dividaUpdated).isNotNull();

        Assertions.assertThat(dividaUpdated.getId()).isNotNull();

        Assertions.assertThat(dividaUpdated.getValor()).isEqualTo(dividaSaved.getValor());
        Assertions.assertThat(dividaUpdated.getData()).isEqualTo(dividaSaved.getData());
    }

    @Test
    @DisplayName("Delete removes divida when Successful")
    void delete_RemovesDivida_WhenSuccessful(){
        DividaLiquidaBC dividaToBeSaved = DividaLiquidaBCCreator.createDividaToBeSaved();

        DividaLiquidaBC dividaSaved = this.dividaLiquidaBCRepository.save(dividaToBeSaved);

        this.dividaLiquidaBCRepository.delete(dividaSaved);

        Optional<DividaLiquidaBC> dividaOptional = this.dividaLiquidaBCRepository.findById(dividaSaved.getId());

        Assertions.assertThat(dividaOptional).isEmpty();

    }

    @Test
    @DisplayName("Find By Valor returns list of divida when Successful")
    void findByValor_ReturnsListOfDivida_WhenSuccessful(){
        DividaLiquidaBC dividaToBeSaved = DividaLiquidaBCCreator.createDividaToBeSaved();

        DividaLiquidaBC dividaSaved = this.dividaLiquidaBCRepository.save(dividaToBeSaved);

        Double valor = dividaSaved.getValor();

        List<DividaLiquidaBC> divida = this.dividaLiquidaBCRepository.findByValor(valor);

        Assertions.assertThat(divida)
                .isNotEmpty()
                .contains(dividaSaved);

    }

    @Test
    @DisplayName("Find By Valor2 returns list of divida when Successful")
    void findByValor2_ReturnsListOfDivida_WhenSuccessful(){
        DividaLiquidaBC dividaToBeSaved = DividaLiquidaBCCreator.createDividaToBeSaved();

        DividaLiquidaBC dividaSaved = this.dividaLiquidaBCRepository.save(dividaToBeSaved);

        Double valor = dividaSaved.getValor();

        List<DividaLiquidaBC> divida = this.dividaLiquidaBCRepository.findByValor2(valor);

        Assertions.assertThat(divida)
                .isNotEmpty()
                .contains(dividaSaved);

    }

    @Test
    @DisplayName("Find By Data returns list of divida when Successful")
    void findByData_ReturnsListOfDivida_WhenSuccessful(){
        DividaLiquidaBC dividaToBeSaved = DividaLiquidaBCCreator.createDividaToBeSaved();

        DividaLiquidaBC dividaSaved = this.dividaLiquidaBCRepository.save(dividaToBeSaved);

        Date data = dividaSaved.getData();

        List<DividaLiquidaBC> divida = this.dividaLiquidaBCRepository.findByData(data);

        Assertions.assertThat(divida)
                .isNotEmpty()
                .contains(dividaSaved);

    }

    @Test
    @DisplayName("Find By Data Between returns list of divida when Successful")
    void findByDataBetween_ReturnsListOfDivida_WhenSuccessful(){
        DividaLiquidaBC dividaToBeSaved = DividaLiquidaBCCreator.createDividaToBeSaved();

        DividaLiquidaBC dividaSaved = this.dividaLiquidaBCRepository.save(dividaToBeSaved);

        Date data = dividaSaved.getData();

        List<DividaLiquidaBC> divida = this.dividaLiquidaBCRepository.findByDataBetween(data, data);

        Assertions.assertThat(divida)
                .isNotEmpty()
                .contains(dividaSaved);

    }

    @Test
    @DisplayName("Find By Valor returns empty list when no divida is found")
    void findByValor_ReturnsEmptyList_WhenDividaIsNotFound(){
        List<DividaLiquidaBC> divida = this.dividaLiquidaBCRepository.findByValor(100.0);

        Assertions.assertThat(divida).isEmpty();
    }

}
