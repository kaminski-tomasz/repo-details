package pl.allegro.repodetails.domain;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class RepositoryDetails {

    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;
}
