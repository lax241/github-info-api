package com.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Map;
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RepositoryDTO {

    private String name;
    private String owner;
    private String fullName;
    private String description;
    private String cloneUrl;
    private Integer stargazersCount;
    private String createdAt;

    @JsonProperty("owner")
    private void unpackNested(Map<String,Object> owner) {
        this.owner = owner.get("login").toString();
    }

    @JsonIgnore
    public String getOwner() {
        return owner;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }
}
