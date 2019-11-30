package be.thomasmore.stockwatch.models;

public class Company {

    private int id;
    private String symbol;
    private String name;
    private Double price;
    private Double beta;
    private int volAvg;
    private Double mktCap;
    private Double lastDiv;
    private String range;
    private Double changes;
    private String changesPercentage;
    private String exchange;
    private String industry;
    private String website;
    private String description;
    private String ceo;
    private String sector;
    private String image;

    public Company() {

    }


    public Company(int id, String symbol, String name, Double price, Double beta, int volAvg, Double mktCap, Double lastDiv, String range, Double changes, String changesPercentage, String exchange, String industry, String website, String description, String ceo, String sector, String image) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.beta = beta;
        this.volAvg = volAvg;
        this.mktCap = mktCap;
        this.lastDiv = lastDiv;
        this.range = range;
        this.changes = changes;
        this.changesPercentage = changesPercentage;
        this.exchange = exchange;
        this.industry = industry;
        this.website = website;
        this.description = description;
        this.ceo = ceo;
        this.sector = sector;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public int getVolAvg() {
        return volAvg;
    }

    public void setVolAvg(int volAvg) {
        this.volAvg = volAvg;
    }

    public Double getMktCap() {
        return mktCap;
    }

    public void setMktCap(Double mktCap) {
        this.mktCap = mktCap;
    }

    public Double getLastDiv() {
        return lastDiv;
    }

    public void setLastDiv(Double lastDiv) {
        this.lastDiv = lastDiv;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getChangesPercentage() {
        return changesPercentage;
    }

    public void setChangesPercentage(String changesPercentage) {
        this.changesPercentage = changesPercentage;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
