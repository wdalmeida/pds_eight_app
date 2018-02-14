package eight.ing3.esipe.fr.rssfeed.bean;

import java.util.Date;

public class RSSFeed {
    private String title;
    private String link;
    private String description;
    private String imgLink;
    private Date publishedDate;

    public RSSFeed() {
    }

    public RSSFeed(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public RSSFeed(String title, String link, String description, String imgLink) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.imgLink = imgLink;
    }

    public RSSFeed(String title, String link, String description, String imgLink, Date publishedDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.imgLink = imgLink;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "RSSFeed{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                '}';
    }
}
