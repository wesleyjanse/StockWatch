package be.thomasmore.stockwatch;

public class Crypto {
private String ticker;
private String name;
private Double price;
private Double changes;
private int marketCapitalization;

public Crypto(){

}
    public Crypto(String ticker, String name,Double price,Double changes,int marketCapitalization) {
        this.ticker = ticker;
        this.name = name;
        this.price = price;
        this.changes = changes;
        this.marketCapitalization = marketCapitalization;
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
}
