package com.ibm.restapibancocentral.contoller;

import com.ibm.restapibancocentral.domain.DivLiqURL;
import com.ibm.restapibancocentral.domain.DividaLiquidaBC;
import com.ibm.restapibancocentral.service.DividaLiquidaBCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("dividaliquidabc/")
@RequiredArgsConstructor
public class DividaLiquidaBCController {

    private final DividaLiquidaBCService dividaService;

    Logger log = LoggerFactory.getLogger(DividaLiquidaBCService.class);

    //Chamando API - A URL está declarada na(o) package/classe Service/DividaLiquidaBCService
    @GetMapping(path = "/api")
    public ResponseEntity<List<DividaLiquidaBC>> callApi() {
        List<DividaLiquidaBC> listaDivida = dividaService.callApi();
        log.info(String.valueOf(listaDivida));
        return ResponseEntity.ok(listaDivida);
    }

    //save api - URL será passado no body
    @PostMapping(path = "/saveapi")
    public ResponseEntity<List<DividaLiquidaBC>> saveApi(@RequestBody @Valid DivLiqURL div) {
        return ResponseEntity.ok(dividaService.saveApi(div));
    }

    @Operation(summary = "List all divida paginated", description = "The default size is 20, use the parameter size to change the default value",
            tags = {"divida-paginated"})
    @GetMapping(path = "/pageable")
    public ResponseEntity<Page<DividaLiquidaBC>> listPageable(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(dividaService.listPageable(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<DividaLiquidaBC>> listAll() {
        return ResponseEntity.ok(dividaService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DividaLiquidaBC> findById(@PathVariable long id) {
        return ResponseEntity.ok(dividaService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/findvalor/{valor}")
    public ResponseEntity<List<DividaLiquidaBC>> findByValor(@PathVariable double valor) {
        return ResponseEntity.ok(dividaService.findByValor(valor));
    }

    //busca por valor utilizando native query
    @GetMapping(path = "/findvalor2/{valor2}")
    public ResponseEntity<List<DividaLiquidaBC>> findByValor2(@PathVariable double valor2) {
        return ResponseEntity.ok(dividaService.findByValor2(valor2));
    }

    @GetMapping(path = "/date")
    public ResponseEntity<List<DividaLiquidaBC>> findByData(
            @RequestParam Date data) throws ParseException {
        return ResponseEntity.ok(dividaService. findByData(data));
    }

    @GetMapping("/intevaldate")
    public ResponseEntity<List<DividaLiquidaBC>> findByDataBetween (
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        return ResponseEntity.ok(dividaService.findByDataBetween(startDate, endDate));
    }


    @GetMapping(path = "/findbyyear")
    public ResponseEntity<List<DividaLiquidaBC>> findByYear(@RequestParam String year) {
        return ResponseEntity.ok(dividaService.findByYear(year));
    }

    @GetMapping(path = "/getsum")
    public ResponseEntity<Double> getSum(@RequestParam String year) {
        return ResponseEntity.ok(dividaService.getSum(year));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<DividaLiquidaBC> save(@RequestBody @Valid DividaLiquidaBC divida) {
        return new ResponseEntity<>(dividaService.save(divida), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Divida Does Not Exist in The Database")
    })
    public ResponseEntity<Void> delete(@PathVariable long id) {
        dividaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/replace")
    public ResponseEntity<Void> replace(@RequestBody DividaLiquidaBC divida) {
        dividaService.replace(divida);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
