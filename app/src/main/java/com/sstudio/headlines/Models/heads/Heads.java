
package com.sstudio.headlines.Models.heads;

import java.util.List;

public class Heads {

    private String status;
    private List<Article> articles = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Heads() {
    }

    /**
     * 
     * @param articles
     * @param status
     */
    public Heads(String status, List<Article> articles) {
        super();
        this.status = status;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
