package pl.PJATK.jaz_s27440_nbp.clientQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Schema(description = "Information about user's inquiries")
public class ClientQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique id of query", example = "2")
    private Long id;
    @Schema(description = "Currency name", example = "USD")
    private String currency;
    @Schema(description = "Beginning of interval", example = "2024-05-01")
    private LocalDate startDate;
    @Schema(description = "End of interval", example = "2024-05-21")
    private LocalDate endDate;
    @Schema(description = "Calculated mean of currency from startDate to endDate")
    private Double meanCourse;
    @Schema(description = "Date and time of query", example = "2024-03-25 14:55:00")
    private LocalDateTime queryDate;

    public ClientQuery() {
    }

    public ClientQuery(String currency, LocalDate startDate, LocalDate endDate, LocalDateTime queryDate, Double meanCourse) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.queryDate = queryDate;
        this.meanCourse = meanCourse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getMeanCourse() {
        return meanCourse;
    }

    public void setMeanCourse(Double meanCourse) {
        this.meanCourse = meanCourse;
    }

    public LocalDateTime getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(LocalDateTime queryDate) {
        this.queryDate = queryDate;
    }
}
