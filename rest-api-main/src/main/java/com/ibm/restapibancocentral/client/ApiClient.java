package com.ibm.restapibancocentral.client;

// MÉTODO ANTIGO

//@Log4j2
//@Configuration
//public class ApiClient implements CommandLineRunner {


//    @Autowired
//    private DividaLiquidaBCRepository dividaRepository;
//
//
//    private static final String DIVIDA_API = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json";
//
//    @Override
//    public void run(String... args) throws Exception {

//        Divida[] divida = restTemplate.getForObject(DIVIDA_API, Divida[].class);
//
//        log.info(Arrays.toString(divida));

//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<List<DividaLiquidaBC>> response = restTemplate.exchange(DIVIDA_API, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});
//
//        List<DividaLiquidaBC> listaDivida = response.getBody();
//
//        log.info(listaDivida);
//
//        dividaRepository.saveAll(listaDivida);


//    }
//}
