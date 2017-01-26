package pl.allegro.repodetails.service.dto;

public final class RepositoryDTOBuilder {

    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;

    public RepositoryDTOBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public RepositoryDTOBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RepositoryDTOBuilder cloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
        return this;
    }

    public RepositoryDTOBuilder stars(int stars) {
        this.stars = stars;
        return this;
    }

    public RepositoryDTOBuilder createdAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public RepositoryDTO build() {
        RepositoryDTO repositoryDTO = new RepositoryDTO();
        repositoryDTO.setFullName(fullName);
        repositoryDTO.setDescription(description);
        repositoryDTO.setCloneUrl(cloneUrl);
        repositoryDTO.setStars(stars);
        repositoryDTO.setCreatedAt(createdAt);
        return repositoryDTO;
    }
}
