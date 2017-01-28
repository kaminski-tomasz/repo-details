package pl.allegro.repodetails.service;

import org.springframework.stereotype.Service;
import pl.allegro.repodetails.datetime.DateTimeUtils;
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
    public Optional<RepositoryDTO> getRepositoryDetails(
            String userName, String repoName, Locale locale) {
        Repository repository = apiClient.getRepositoryDetails(userName, repoName);
        RepositoryDTO repositoryDTO = buildLocalizedRepositoryDTO(repository, locale);
        return Optional.ofNullable(repositoryDTO);
    }

    private RepositoryDTO buildLocalizedRepositoryDTO(
            Repository repository, Locale locale) {
        if (repository == null) {
            return null;
        }
        RepositoryDTO repositoryDTO = buildRepositoryDTO(repository);
        if (repository.getCreatedAt() != null) {
            String formattedDate = DateTimeUtils.formatDateTime(repository.getCreatedAt(), locale);
            repositoryDTO.setCreatedAt(formattedDate);
        }
        return repositoryDTO;
    }

    private RepositoryDTO buildRepositoryDTO(Repository repo) {
        RepositoryDTO repositoryDTO = new RepositoryDTO();
        repositoryDTO.setFullName(repo.getFullName());
        repositoryDTO.setDescription(repo.getDescription());
        repositoryDTO.setCloneUrl(repo.getCloneUrl());
        repositoryDTO.setStars(repo.getStargazersCount());
        return repositoryDTO;
    }
}
