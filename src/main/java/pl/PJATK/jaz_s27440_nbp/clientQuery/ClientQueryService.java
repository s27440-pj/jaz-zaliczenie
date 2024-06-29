package pl.PJATK.jaz_s27440_nbp.clientQuery;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.PJATK.jaz_s27440_nbp.rate.ExchangeRates;
import pl.PJATK.jaz_s27440_nbp.rate.Rate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ClientQueryService {

    private final ClientQueryRepository clientQueryRepository;
    private final RestTemplate restTemplate;

    public ClientQueryService(ClientQueryRepository clientQueryRepository, RestTemplate restTemplate) {
        this.clientQueryRepository = clientQueryRepository;
        this.restTemplate = restTemplate;
    }

    public double getMeanOfCurrency(String currency, LocalDate startDate, LocalDate endDate) {
        LocalDateTime today = LocalDateTime.now();

        ResponseEntity<ExchangeRates> response = restTemplate
                .getForEntity("https://api.nbp.pl/api/exchangerates/rates/A/{currency}/{startDate}/{endDate}?format=json",
                        ExchangeRates.class, currency, startDate, endDate);

        ExchangeRates exchangeRates = response.getBody();
        double sum = 0;

        for (Rate rate : exchangeRates.getRates()) {
            sum += rate.getMid();
        }
        Double meanCourse = sum/(exchangeRates.getRates().size());
        //add to base
        ClientQuery query = new ClientQuery(currency, startDate, endDate, today, meanCourse);
        clientQueryRepository.save(query);

        return meanCourse;
    }

    public ExchangeRates getCurrency(String currency, LocalDate startDate, LocalDate endDate) {
        LocalDateTime today = LocalDateTime.now();

        ResponseEntity<ExchangeRates> response = restTemplate
                .getForEntity("https://api.nbp.pl/api/exchangerates/rates/A/{currency}/{startDate}/{endDate}?format=json",
                        ExchangeRates.class, currency, startDate, endDate);

        return response.getBody();
    }
}
