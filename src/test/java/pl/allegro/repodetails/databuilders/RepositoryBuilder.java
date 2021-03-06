package pl.allegro.repodetails.databuilders;

import pl.allegro.repodetails.github.domain.Repository;

import java.time.OffsetDateTime;

public final class RepositoryBuilder {

    private String fullName;
    private String description;
    private OffsetDateTime createdAt;
    private String cloneUrl;
    private int stargazersCount;

    public static RepositoryBuilder aRepository() {
        return new RepositoryBuilder();
    }

    public RepositoryBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public RepositoryBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RepositoryBuilder createdAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public RepositoryBuilder cloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
        return this;
    }

    public RepositoryBuilder stargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
        return this;
    }

    public Repository build() {
        Repository repository = new Repository();
        repository.setFullName(fullName);
        repository.setDescription(description);
        repository.setCreatedAt(createdAt);
        repository.setCloneUrl(cloneUrl);
        repository.setStargazersCount(stargazersCount);
        return repository;
    }
}
