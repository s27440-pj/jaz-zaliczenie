package pl.PJATK.jaz_s27440_nbp.rate;


import java.util.List;

public class ExchangeRates {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;

    public ExchangeRates() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
