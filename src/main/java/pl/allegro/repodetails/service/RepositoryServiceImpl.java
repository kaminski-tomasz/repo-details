package pl.allegro.repodetails.service;

import org.springframework.stereotype.Service;
import pl.allegro.repodetails.github.GitHubApiClient;
import pl.allegro.repodetails.github.domain.Repository;
import pl.allegro.repodetails.service.dto.RepositoryDTO;

import java.util.Locale;
import java.util.Optional;

@Service
class RepositoryServiceImpl implements RepositoryService {

    private GitHubApiClient apiClient;

    RepositoryServiceImpl(GitHubApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Optional<RepositoryDTO> getRepositoryDetails(String userName, String repoName, Locale locale) {
        Repository repo = apiClient.getRepositoryDetails(userName, repoName);
        return Optional.ofNullable(buildRepositoryDTO(repo));
    }

    private RepositoryDTO buildRepositoryDTO(Repository repo) {
        return repo != null
                ? RepositoryDTO.builder()
                        .fullName(repo.getFullName())
                        .description(repo.getDescription())
                        .cloneUrl(repo.getCloneUrl())
                        .stars(repo.getStargazersCount())
                        .createdAt(repo.getCreatedAt().toString())
                        .build()
                : null;
    }

}
