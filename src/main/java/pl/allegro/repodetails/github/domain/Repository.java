package pl.allegro.repodetails.github.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("stargazers_count")
    private int stargazersCount;

    public Repository() {
    }

    @Builder
    public Repository(String name, String fullName, String description,
                      String createdAt, String cloneUrl, int stargazersCount) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.createdAt = createdAt;
        this.cloneUrl = cloneUrl;
        this.stargazersCount = stargazersCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }
}
