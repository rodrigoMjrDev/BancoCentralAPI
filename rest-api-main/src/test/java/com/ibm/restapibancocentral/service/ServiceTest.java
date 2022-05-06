package com.ibm.restapibancocentral.service;

import com.ibm.restapibancocentral.entities.DadosDividaLiquida;
import com.ibm.restapibancocentral.exeception.BadRequestException;
import com.ibm.restapibancocentral.repository.DividaLiquidaBCRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ServiceTest {

    @InjectMocks
    private ServiceBC serviceBC;
    @Mock
    private DividaLiquidaBCRepository dividaRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<DadosDividaLiquida> dividaPage = new PageImpl<>(List.of(DividaLiquidaBCCreator.createValidDivida()));
        BDDMockito.when(dividaRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(dividaPage);

        BDDMockito.when(dividaRepositoryMock.findAll())
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(DividaLiquidaBCCreator.createValidDivida()));

        BDDMockito.when(dividaRepositoryMock.findByValor(ArgumentMatchers.anyDouble()))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));



        BDDMockito.when(dividaRepositoryMock.findByValor2(ArgumentMatchers.anyDouble()))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));
//
        BDDMockito.when(dividaRepositoryMock.findByData(ArgumentMatchers.any(Date.class)))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));
//
        BDDMockito.when(dividaRepositoryMock.findByDataBetween(ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Date.class)))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));
//
        BDDMockito.when(dividaRepositoryMock.findByYear(ArgumentMatchers.anyString()))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));
//
//
        BDDMockito.when(dividaRepositoryMock.saveAll(ArgumentMatchers.anyList()))
                .thenReturn(List.of(DividaLiquidaBCCreator.createValidDivida()));




        BDDMockito.when(dividaRepositoryMock.save(ArgumentMatchers.any(DadosDividaLiquida.class)))
                .thenReturn(DividaLiquidaBCCreator.createValidDivida());

        BDDMockito.doNothing().when(dividaRepositoryMock).delete(ArgumentMatchers.any(DadosDividaLiquida.class));
    }
    @Test
    @DisplayName("listAll returns list of divida inside page object when successful")
    void listAll_ReturnsListOfDvidaInsidePageObject_WhenSuccessful(){
        Double expectedValue = DividaLiquidaBCCreator.createValidDivida().getValor();

        Page<DadosDividaLiquida> dividaPage = serviceBC.listPageable(PageRequest.of(1,1));

        Assertions.assertThat(dividaPage).isNotNull();

        Assertions.assertThat(dividaPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(dividaPage.toList().get(0).getValor()).isEqualTo(expectedValue);

    }

    @Test
    @DisplayName("listAllNonPageable returns list of divida when successful")
    void listAllNonPageable_ReturnsListOfDivida_WhenSuccessful(){
        Double expectedValue = DividaLiquidaBCCreator.createValidDivida().getValor();

        List<DadosDividaLiquida> divida = serviceBC.listAll();

        Assertions.assertThat(divida)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(divida.get(0).getValor()).isEqualTo(expectedValue);
    }

//    @Test
//    @DisplayName("SaveAll returns list of divida when successful")
//    void saveAll_ReturnsListOfDivida_WhenSuccessful(){
//        DivLiqURL divLiqURL = DivLiqURLCreator.createDivLiqURLToBeSaved();
//
//        List<DividaLiquidaBC> divida = dividaLiquidaBCService.saveApi(divLiqURL);
//
//        Assertions.assertThat(divida)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1);
//
//        Assertions.assertThat(divida).isNotNull().isEqualTo(List.of(DivLiqURLCreator.createDivLiqURLToBeSaved()));
//    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns divida when successful")
    void findByIdOrThrowBadRequestException_ReturnsDivida_WhenSuccessful(){
        Long expectedId = DividaLiquidaBCCreator.createValidDivida().getId();

        DadosDividaLiquida divida = serviceBC.findByIdOrThrowBadRequestException(1);

        Assertions.assertThat(divida).isNotNull();

        Assertions.assertThat(divida.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when divida is not found")
    void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenDividaIsNotFound(){
        BDDMockito.when(dividaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> serviceBC.findByIdOrThrowBadRequestException(1));
    }

    @Test
    @DisplayName("findByValor returns a list of divida when successful")
    void findByValor_ReturnsListOfADivida_WhenSuccessful(){
        Double expectedValue = DividaLiquidaBCCreator.createValidDivida().getValor();

        List<DadosDividaLiquida> divida = serviceBC.findByValor(2.22);

        Assertions.assertThat(divida)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(divida.get(0).getValor()).isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("findByValor returns an empty list of divida when divida is not found")
    void findByValor_ReturnsEmptyListOfDivida_WhenAnimeIsNotFound(){
        BDDMockito.when(dividaRepositoryMock.findByValor(ArgumentMatchers.anyDouble()))
                .thenReturn(Collections.emptyList());

        List<DadosDividaLiquida> divida = serviceBC.findByValor(2.22);

        Assertions.assertThat(divida)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("findByValor2 returns an empty list of divida when divida is not found")
    void findByValor2_ReturnsEmptyListOfDivida_WhenAnimeIsNotFound(){
        BDDMockito.when(dividaRepositoryMock.findByValor2(ArgumentMatchers.anyDouble()))
                .thenReturn(Collections.emptyList());

        List<DadosDividaLiquida> divida = serviceBC.findByValor2(2.22);

        Assertions.assertThat(divida)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("findByData returns a list of divida when successful")
    void findByData_ReturnsListOfDivida_WhenSuccessful() throws ParseException {
        Date expectedDate = DividaLiquidaBCCreator.createValidDivida().getData();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        List<DadosDividaLiquida> divida = serviceBC.findByData(new java.sql.Date(df.parse("02/04/2015").getTime()));

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

        List<DadosDividaLiquida> divida = serviceBC.findByDataBetween(
                new java.sql.Date(df.parse("01/07/2015").getTime()),
                new java.sql.Date(df.parse("01/07/2016").getTime()));

        Assertions.assertThat(divida)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(divida.get(0).getData()).isEqualTo(expectedDate);
    }







    @Test
    @DisplayName("findByYear returns a list of divida when successful")
    void findByYear_ReturnsListOfDivida_WhenSuccessful(){


        List<DadosDividaLiquida> divida = serviceBC.findByYear("2008");

        Assertions.assertThat(divida).isNotNull().isEqualTo(List.of(DividaLiquidaBCCreator.createValidDivida()
        ));
    }



    @Test
    @DisplayName("save returns divida when successful")
    void save_ReturnsDivida_WhenSuccessful(){

        DadosDividaLiquida divida = serviceBC.save(DividaLiquidaBCCreator.createDividaToBeSaved());

        Assertions.assertThat(divida).isNotNull().isEqualTo(DividaLiquidaBCCreator.createValidDivida());

    }

    @Test
    @DisplayName("replace updates divida when successful")
    void replace_UpdatesDivida_WhenSuccessful(){

        Assertions.assertThatCode(() -> serviceBC.replace(DividaLiquidaBCCreator.createValidUpdatedDivida()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("delete removes divida when successful")
    void delete_RemovesAnime_WhenSuccessful(){

        Assertions.assertThatCode(() -> serviceBC.delete(1))
                .doesNotThrowAnyException();

    }
}
