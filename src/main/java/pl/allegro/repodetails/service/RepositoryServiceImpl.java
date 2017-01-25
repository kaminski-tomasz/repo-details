package pl.allegro.repodetails.service;

import org.springframework.stereotype.Service;
import pl.allegro.repodetails.github.domain.Repository;
import pl.allegro.repodetails.github.GitHubApiClient;
import pl.allegro.repodetails.service.dto.RepositoryDTO;

@Service
class RepositoryServiceImpl implements RepositoryService {

    private GitHubApiClient apiClient;

    RepositoryServiceImpl(GitHubApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public RepositoryDTO getRepositoryDetails(String userName, String repoName) {

        Repository repo = apiClient.getRepositoryDetails(userName, repoName);

        return RepositoryDTO.builder()
                .fullName(repo.getFullName())
                .description(repo.getDescription())
                .cloneUrl(repo.getCloneUrl())
                .stars(repo.getStargazersCount())
                .createdAt(repo.getCreatedAt())
                .build();
    }

}
