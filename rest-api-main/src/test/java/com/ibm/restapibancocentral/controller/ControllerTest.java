package com.ibm.restapibancocentral.controller;

import com.ibm.restapibancocentral.contoller.ControllerBC;
import com.ibm.restapibancocentral.entities.URLDiv;
import com.ibm.restapibancocentral.entities.DadosDividaLiquida;
import com.ibm.restapibancocentral.service.ServiceBC;
import com.ibm.restapibancocentral.util.DivLiqURLCreator;
import com.ibm.restapibancocentral.util.DividaLiquidaBCCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ExtendWith(SpringExtension.class)
public class ControllerTest {

    @InjectMocks
    private ControllerBC controllerBC;
    @Mock
    private ServiceBC dividaLiquidaBCMock;

    @BeforeEach
    void setUp(){

        BDDMockito.when(dividaLiquidaBCMock.callApi())
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaLiquidaBCMock.saveApi(ArgumentMatchers.any(URLDiv.class)))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        PageImpl<DadosDividaLiquida> dividaPage = new PageImpl<>(List.of(DividaLiquidaBCCreator.createValidDivida()));
        BDDMockito.when(dividaLiquidaBCMock.listPageable(ArgumentMatchers.any()))
                .thenReturn(dividaPage);

        BDDMockito.when(dividaLiquidaBCMock.listAll())
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaLiquidaBCMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(DividaLiquidaBCCreator.createValidDivida());

        BDDMockito.when(dividaLiquidaBCMock.findByValor(ArgumentMatchers.anyDouble()))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaLiquidaBCMock.findByValor2(ArgumentMatchers.anyDouble()))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaLiquidaBCMock.findByData(ArgumentMatchers.any(Date.class)))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaLiquidaBCMock.findByDataBetween(ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Date.class)))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaLiquidaBCMock.findByYear(ArgumentMatchers.anyString()))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaLiquidaBCMock.getSum(ArgumentMatchers.anyString()))
                .thenReturn(0.30);

        BDDMockito.when(dividaLiquidaBCMock.save(ArgumentMatchers.any(DadosDividaLiquida.class)))
                .thenReturn(DividaLiquidaBCCreator.createValidDivida());

        BDDMockito.doNothing().when(dividaLiquidaBCMock).replace(ArgumentMatchers.any(DadosDividaLiquida.class));

        BDDMockito.doNothing().when(dividaLiquidaBCMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("list returns list of divida inside page object when successful")
    void list_ReturnsListOfDividaInsidePageObject_WhenSuccessful(){

        Double expectedValor = DividaLiquidaBCCreator.createValidDivida().getValor();
        Date expectedData = DividaLiquidaBCCreator.createValidDivida().getData();


        Page<DadosDividaLiquida> dividaPage = controllerBC.listPageable(null).getBody();

        Assertions.assertThat(dividaPage).isNotNull();

        Assertions.assertThat(dividaPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(dividaPage.toList().get(0).getValor()).isEqualTo(expectedValor);
        Assertions.assertThat(dividaPage.toList().get(0).getData()).isEqualTo(expectedData);
    }

    @Test
    @DisplayName("listAll returns list of divida when successful")
    void listAll_ReturnsListOfDivida_WhenSuccessful(){
        Double expectedValue = DividaLiquidaBCCreator.createValidDivida().getValor();

        List<DadosDividaLiquida> animes = controllerBC.listAll().getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getValor()).isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("findById returns divida when successful")
    void findById_ReturnsDivida_WhenSuccessful(){
        Long expectedId = DividaLiquidaBCCreator.createValidDivida().getId();

        DadosDividaLiquida divida = controllerBC.findById(1).getBody();

        Assertions.assertThat(divida).isNotNull();

        Assertions.assertThat(divida.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByValor returns a list of divida when successful")
    void findByValor_ReturnsListOfDivida_WhenSuccessful(){
        Double expectedName = DividaLiquidaBCCreator.createValidDivida().getValor();

        List<DadosDividaLiquida> divida = controllerBC.findByValor(0.12).getBody();

        Assertions.assertThat(divida)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(divida.get(0).getValor()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByValor2 returns a list of divida when successful")
    void findByValor2_ReturnsListOfDivida_WhenSuccessful(){
        Double expectedName = DividaLiquidaBCCreator.createValidDivida().getValor();

        List<DadosDividaLiquida> divida = controllerBC.findByValor2(0.12).getBody();

        Assertions.assertThat(divida)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(divida.get(0).getValor()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByValor returns an empty list of divida when divida is not found")
    void findByValor_ReturnsEmptyListOfDivida_WhenDividaIsNotFound(){
        BDDMockito.when(dividaLiquidaBCMock.findByValor(ArgumentMatchers.anyDouble()))
                .thenReturn(Collections.emptyList());

        List<DadosDividaLiquida> divida = controllerBC.findByValor(0.15).getBody();

        Assertions.assertThat(divida)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("findByValor2 returns an empty list of divida when divida is not found")
    void findByValor2_ReturnsEmptyListOfDivida_WhenDividaIsNotFound(){
        BDDMockito.when(dividaLiquidaBCMock.findByValor2(ArgumentMatchers.anyDouble()))
                .thenReturn(Collections.emptyList());

        List<DadosDividaLiquida> divida = controllerBC.findByValor2(0.15).getBody();

        Assertions.assertThat(divida)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("findByData returns a list of divida when successful")
    void findByData_ReturnsListOfDivida_WhenSuccessful() throws ParseException {
        Date expectedDate = DividaLiquidaBCCreator.createValidDivida().getData();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        List<DadosDividaLiquida> divida = controllerBC.findByData(new java.sql.Date(df.parse("02/04/2015").getTime())).getBody();

        Assertions.assertThat(divida)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(divida.get(0).getData()).isEqualTo(expectedDate);
    }

    @Test
    @DisplayName("findByDataBetween returns a list of divida when successful")
    void findByDataBetween_ReturnsListOfDivida_WhenSuccessful() throws ParseException {
        Date expectedDate = DividaLiquidaBCCreator.createValidDivida().getData();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        List<DadosDividaLiquida> divida = controllerBC.findByDataBetween(
                new java.sql.Date(df.parse("01/07/2015").getTime()),
                new java.sql.Date(df.parse("01/07/2016").getTime())).getBody();

        Assertions.assertThat(divida)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(divida.get(0).getData()).isEqualTo(expectedDate);
    }

    @Test
    @DisplayName("findByYear returns a list of divida when successful")
    void findByYear_ReturnsListOfDivida_WhenSuccessful(){

        List<DadosDividaLiquida> divida = controllerBC.findByYear("2008").getBody();

        Assertions.assertThat(divida).isNotNull().isEqualTo(List.of(DividaLiquidaBCCreator.createValidDivida()
        ));
    }

    @Test
    @DisplayName("getSum returns sum when successful")
    void getSum_ReturnsSum_WhenSuccessful(){

        Double divida = controllerBC.getSum("2008").getBody();

        Assertions.assertThat(divida).isNotNull().isEqualTo(
                DividaLiquidaBCCreator.createValidDivida().getValor() +
                        DividaLiquidaBCCreator.createValidDivida().getValor());
    }

    @Test
    @DisplayName("callApi returns divida when successful")
    void callApi_ReturnsDivida_WhenSuccessful(){

        List<DadosDividaLiquida> divida = controllerBC.callApi().getBody();

        Assertions.assertThat(divida).isNotNull().isEqualTo(List.of(DividaLiquidaBCCreator.createValidDivida()));

    }

    @Test
    @DisplayName("saveAPi returns divida when successful")
    void saveApi_ReturnsDivida_WhenSuccessful(){

        List<DadosDividaLiquida> divida = controllerBC.saveApi(DivLiqURLCreator.createDivLiqURLToBeSaved()).getBody();

        Assertions.assertThat(divida).isNotNull().isEqualTo(List.of(DividaLiquidaBCCreator.createValidDivida()));

    }

    @Test
    @DisplayName("save returns divida when successful")
    void save_ReturnsDivida_WhenSuccessful(){

        DadosDividaLiquida divida = controllerBC.save(DividaLiquidaBCCreator.createDividaToBeSaved()).getBody();

        Assertions.assertThat(divida).isNotNull().isEqualTo(DividaLiquidaBCCreator.createValidDivida());

    }

    @Test
    @DisplayName("replace updates divida when successful")
    void replace_UpdatesDivida_WhenSuccessful(){

        Assertions.assertThatCode(() -> controllerBC.replace(DividaLiquidaBCCreator.createValidUpdatedDivida()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = controllerBC.replace(DividaLiquidaBCCreator.createValidUpdatedDivida());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes divida when successful")
    void delete_RemovesDivida_WhenSuccessful(){

        Assertions.assertThatCode(() -> controllerBC.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = controllerBC.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
