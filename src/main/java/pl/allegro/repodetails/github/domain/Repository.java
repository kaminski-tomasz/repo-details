package pl.allegro.repodetails.github.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import pl.allegro.repodetails.github.OffsetDateTimeDeserializer;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("created_at")
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    private OffsetDateTime createdAt;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("stargazers_count")
    private int stargazersCount;

    public Repository() {
    }

    @Builder
    public Repository(String name, String fullName, String description,
                      OffsetDateTime createdAt, String cloneUrl, int stargazersCount) {
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
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
