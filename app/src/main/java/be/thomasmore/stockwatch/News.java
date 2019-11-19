package be.thomasmore.stockwatch;

public class News {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;

    public News(){

    }
    public News(String author,String title,String description,String url,String urlToImage) {
        this.author = author;
        this.title=title;
        this.description=description;
        this.url=url;
        this.urlToImage=urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
