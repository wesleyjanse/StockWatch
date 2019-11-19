package be.thomasmore.stockwatch;

import java.util.Date;

public class Forex {
    private String ticker;
    private double bid;
    private double ask;
    private double open;
    private double low;
    private double high;
    private double changes;
    private String date;

    public Forex(){

    }
    public Forex(String ticker,double bid, double ask,double open,double low, double high, double changes, String date) {
        this.ticker = ticker;
        this.bid= bid;
        this.ask = ask;
        this.open =open;
        this.low = low;
        this.high = high;
        this.changes = changes;
        this.date = date;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getChanges() {
        return changes;
    }

    public void setChanges(double changes) {
        this.changes = changes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
