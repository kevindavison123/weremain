package com.weremain.fund.wrw.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 11/15/16.
 */
public class User
{
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("joined")
    private Date joined;
    @JsonProperty("updated")
    private Date updated;
    @JsonProperty("charities")
    private List<String> charities;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonIgnore
    private String content;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<String> getCharities() {
        return charities;
    }

    public void setCharities(List<String> charities) {
        this.charities = charities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
