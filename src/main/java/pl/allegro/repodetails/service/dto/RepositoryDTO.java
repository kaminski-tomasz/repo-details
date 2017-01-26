package pl.allegro.repodetails.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RepositoryDTO {

    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;

    public RepositoryDTO() {
    }

    @Builder
    public RepositoryDTO(String fullName, String description,
                         String cloneUrl, int stars, String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }
}
