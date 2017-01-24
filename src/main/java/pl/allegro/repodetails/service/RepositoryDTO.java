package pl.allegro.repodetails.service;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class RepositoryDTO {

    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;
}
