package pl.allegro.repodetails.service;

import org.springframework.stereotype.Service;
import pl.allegro.repodetails.github.GitHubApiClient;
import pl.allegro.repodetails.github.domain.Repository;
import pl.allegro.repodetails.service.dto.RepositoryDTO;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private static final String DATE_TIME_PATTERN = "dd MMMM uuuu, HH:mm:ss O";

    private GitHubApiClient apiClient;

    RepositoryServiceImpl(GitHubApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Optional<RepositoryDTO> getRepositoryDetails(
            String userName, String repoName, Locale locale) {
        Repository repo = apiClient.getRepositoryDetails(userName, repoName);
        return Optional.ofNullable(buildLocalizedRepositoryDTO(repo, locale));
    }

    private RepositoryDTO buildLocalizedRepositoryDTO(
            Repository repository, Locale locale) {
        if (repository == null) {
            return null;
        }
        RepositoryDTO repositoryDTO = buildRepositoryDTO(repository);
        if (repository.getCreatedAt() != null) {
            String formattedDate = formatDateTime(repository.getCreatedAt(), locale);
            repositoryDTO.setCreatedAt(formattedDate);
        }
        return repositoryDTO;
    }

    private RepositoryDTO buildRepositoryDTO(Repository repo) {
        return RepositoryDTO.builder()
                    .fullName(repo.getFullName())
                    .description(repo.getDescription())
                    .cloneUrl(repo.getCloneUrl())
                    .stars(repo.getStargazersCount())
                    .build();
    }

    private String formatDateTime(OffsetDateTime dateTime, Locale locale) {
        return dateTime == null ? null : DateTimeFormatter
                .ofPattern(DATE_TIME_PATTERN, locale)
                .format(dateTime);
    }
}
