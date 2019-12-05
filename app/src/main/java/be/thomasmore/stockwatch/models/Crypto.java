package be.thomasmore.stockwatch.models;

import androidx.annotation.NonNull;

public class Crypto {
    private int id;
    private String ticker;
    private String name;
    private Double price;
    private Double changes;
    private int marketCapitalization;

    public Crypto() {

    }

    public Crypto(int id, String ticker, String name, Double price, Double changes, int marketCapitalization) {
        this.id = id;
        this.ticker = ticker;
        this.name = name;
        this.price = price;
        this.changes = changes;
        this.marketCapitalization = marketCapitalization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getChanges() {
        return changes;
    }

    public void setChanges(Double changes) {
        this.changes = changes;
    }

    public int getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(int marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    @NonNull
    @Override
    public String toString() {
        return getTicker() + ": " + getName();
    }
}
