package com.alu.skyesque.models;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Sunday 24, 03 2024
 */
public class WeatherChannel {
    public String title;
    public String link;
    public String description;
    public String language;
    public String copyright;
    public String pubDate;
    public String dcDate;
    public String dcLanguage;
    public String dcRights;
    public String atomLink;
    public WeatherUnit weatherUnit;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDcDate() {
        return dcDate;
    }

    public void setDcDate(String dcDate) {
        this.dcDate = dcDate;
    }

    public String getDcLanguage() {
        return dcLanguage;
    }

    public void setDcLanguage(String dcLanguage) {
        this.dcLanguage = dcLanguage;
    }

    public String getDcRights() {
        return dcRights;
    }

    public void setDcRights(String dcRights) {
        this.dcRights = dcRights;
    }

    public String getAtomLink() {
        return atomLink;
    }

    public void setAtomLink(String atomLink) {
        this.atomLink = atomLink;
    }

    public WeatherUnit getWeatherUnit() {
        return weatherUnit;
    }

    public void setWeatherUnit(WeatherUnit weatherUnit) {
        this.weatherUnit = weatherUnit;
    }

    @Override
    public String toString() {
        return "WeatherChannel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", copyright='" + copyright + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", dcDate='" + dcDate + '\'' +
                ", dcLanguage='" + dcLanguage + '\'' +
                ", dcRights='" + dcRights + '\'' +
                ", atomLink='" + atomLink + '\'' +
                ", weatherUnit=" + weatherUnit +
                '}';
    }
}
