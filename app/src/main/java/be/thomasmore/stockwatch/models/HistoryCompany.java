package be.thomasmore.stockwatch.models;

public class HistoryCompany {

    private String date;
    private Double close;

    public HistoryCompany() {

    }

    public HistoryCompany(String date, Double close) {
        this.date = date;
        this.close = close;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }
}

