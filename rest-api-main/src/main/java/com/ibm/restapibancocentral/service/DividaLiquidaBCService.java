package com.ibm.restapibancocentral.service;

import com.ibm.restapibancocentral.domain.DivLiqURL;
import com.ibm.restapibancocentral.domain.DividaLiquidaBC;
import com.ibm.restapibancocentral.exeception.BadRequestException;
import com.ibm.restapibancocentral.repository.DividaLiquidaBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class DividaLiquidaBCService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    @Autowired
    private DividaLiquidaBCRepository dividaRepository;

    private static final String DIVIDA_API = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json";

    @Transactional
    public List<DividaLiquidaBC> callApi() {

        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<DividaLiquidaBC>> response = restTemplate.exchange(DIVIDA_API, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        List<DividaLiquidaBC> listaDivida = response.getBody();

        if (listaDivida != null) {
            return dividaRepository.saveAll(listaDivida);
        }
        throw new RuntimeException("Não foi possível acessar a API do Banco Central");
    }

    @Transactional
    public List<DividaLiquidaBC> saveApi(DivLiqURL div) {

        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<DividaLiquidaBC>> response = restTemplate.exchange(div.getDiv(), HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        List<DividaLiquidaBC> listaDivida = response.getBody();

        if (listaDivida != null) {
            return dividaRepository.saveAll(listaDivida);
        }
        throw new RuntimeException("Não foi possível acessar a API do Banco Central");

    }

    public Page<DividaLiquidaBC> listPageable(Pageable pageable) {
        return dividaRepository.findAll(pageable);
    }

    public List<DividaLiquidaBC> listAll() {
        return dividaRepository.findAll();
    }

    public List<DividaLiquidaBC> findByValor(Double valor) {
        return dividaRepository.findByValor(valor);
    }

    public List<DividaLiquidaBC> findByValor2(Double valor) {
        return dividaRepository.findByValor2(valor);
    }

    public List<DividaLiquidaBC> findByDataBetween(Date startDate, Date endDate) {
        return dividaRepository.findByDataBetween(startDate, endDate);
    }


    public List<DividaLiquidaBC> findByData(Date data)  {
        return dividaRepository.findByData(data);
    }

    public DividaLiquidaBC findByIdOrThrowBadRequestException(long id) {
        return dividaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Divida not Found"));
    }

    public List<DividaLiquidaBC> findByYear(String year) {
        return dividaRepository.findByYear(year);
    }

    public Double getSum(String year) {
        List<DividaLiquidaBC> byYear = dividaRepository.findByYear(year);

        double sum = 0;

        for(int i=0; i<byYear.size(); i++) {
            sum += byYear.get(i).getValor();
        }
        return sum;
    }

    @Transactional
    public DividaLiquidaBC save(DividaLiquidaBC divida) {
        return dividaRepository.save(divida);
    }

    public void delete(long id) {
        dividaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(DividaLiquidaBC divida) {
        DividaLiquidaBC savedDivida = findByIdOrThrowBadRequestException(divida.getId());
        divida.setId(savedDivida.getId());
        dividaRepository.save(divida);
    }

}