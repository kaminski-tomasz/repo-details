package pl.allegro.repodetails.service.dto;

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
