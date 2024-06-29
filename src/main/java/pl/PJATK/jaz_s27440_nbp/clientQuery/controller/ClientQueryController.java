package pl.PJATK.jaz_s27440_nbp.clientQuery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.PJATK.jaz_s27440_nbp.clientQuery.service.ClientQueryService;

import java.time.LocalDate;

@RestController
public class ClientQueryController {
    private final ClientQueryService clientQueryService;

    public ClientQueryController(ClientQueryService ClientQueryService) {
        this.clientQueryService = ClientQueryService;
    }

    @GetMapping("/{currency}/{startDate}/{endDate}")
    @Operation(summary = "Get mean of currency from startDate to endDate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully calculated mean of currency"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Currency or dates not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Double> getMeanOfCurrency(@PathVariable String currency,
                                                    @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return ResponseEntity.ok(clientQueryService.getMeanOfCurrency(currency, startDate, endDate));
    }

    //tests - to manually check currencies from startDate to ednDate
//    @GetMapping("/list/{currency}/{startDate}/{endDate}")
//    @Operation(summary = "Get all currencies value form startDate to ednDate")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Succesfully obtained list of currency values"),
//            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Currency or dates not found", content = @Content),
//            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
//    })
//    public ResponseEntity<ExchangeRates> getCurrency(@PathVariable String currency,
//                                                     @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
//        return ResponseEntity.ok(clientQueryService.getCurrency(currency, startDate, endDate ));
//    }
}
