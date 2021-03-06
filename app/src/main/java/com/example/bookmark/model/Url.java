package com.example.bookmark.model;

import java.io.Serializable;

public class Url implements Serializable {
    private Long id;
    private String url;
    private String observacao;
    private Double ranking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getRanking() {
        return ranking;
    }

    public void setRanking(Double ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return getRanking() + " - " + getUrl();
    }
}
