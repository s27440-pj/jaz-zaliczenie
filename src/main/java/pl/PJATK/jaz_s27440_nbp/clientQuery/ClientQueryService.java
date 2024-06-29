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

    private final ClientQueryRepository ClientQueryRepository;
    private final RestTemplate restTemplate;

    public ClientQueryService(ClientQueryRepository ClientQueryRepository, RestTemplate restTemplate) {
        this.ClientQueryRepository = ClientQueryRepository;
        this.restTemplate = restTemplate;
    }

    public double getMeanOfCurrency(String currency, Integer days) {
        LocalDateTime today = LocalDateTime.now();
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days -1);

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
        Inquiry inquiry = new Inquiry(currency, days, today, meanCourse);
        ClientQueryRepository.save(inquiry);

        return meanCourse;
    }

    public ExchangeRates getCurrency(String currency, Integer days) {
        LocalDateTime today = LocalDateTime.now();
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days -1);

        ResponseEntity<ExchangeRates> response = restTemplate
                .getForEntity("https://api.nbp.pl/api/exchangerates/rates/A/{currency}/{startDate}/{endDate}?format=json",
                        ExchangeRates.class, currency, startDate, endDate);

        return response.getBody();
    }
}
