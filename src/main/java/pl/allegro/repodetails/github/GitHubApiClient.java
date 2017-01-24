package pl.allegro.repodetails.github;

import pl.allegro.repodetails.domain.Repository;

public interface GitHubApiClient {
    Repository getRepositoryDetails(String userName, String repoName);
}
