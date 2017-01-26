package pl.allegro.repodetails.service;

import org.junit.Before;
import org.junit.Test;
import pl.allegro.repodetails.github.GitHubApiClient;
import pl.allegro.repodetails.github.domain.Repository;
import pl.allegro.repodetails.service.dto.RepositoryDTO;

import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.allegro.repodetails.service.dto.RepositoryDTOAssert.assertThat;

public class RepositoryServiceTest {

    private static final String USER_NAME = "octokit";
    private static final String REPO_NAME = "octokit.rb";
    private static final Locale USER_LOCALE = Locale.ENGLISH;

    private static final Repository REPOSITORY = Repository.builder()
            .fullName("anyRepository")
            .description("repoDescription")
            .cloneUrl("someCloneURL")
            .stargazersCount(1024)
            .createdAt(OffsetDateTime.parse("2009-12-10T21:41:49Z"))
            .build();

    private GitHubApiClient gitHubApiClient = mock(GitHubApiClient.class);
    private RepositoryService repositoryService;

    @Before
    public void setUp() {
        repositoryService = new RepositoryServiceImpl(gitHubApiClient);
    }

    @Test
    public void shouldReturnEmptyOptionalWhenNoRepositoryWasPresent() {

        when(gitHubApiClient.getRepositoryDetails(USER_NAME, REPO_NAME))
                .thenReturn(null);

        Optional<RepositoryDTO> resultDTO =
                repositoryService.getRepositoryDetails(USER_NAME, REPO_NAME, USER_LOCALE);

        assertThat(resultDTO).isNotNull().isNotPresent();
    }

    @Test
    public void shouldReturnCorrectRepositoryDTO() {

        when(gitHubApiClient.getRepositoryDetails(USER_NAME, REPO_NAME))
                .thenReturn(REPOSITORY);

        Optional<RepositoryDTO> resultDTO =
                repositoryService.getRepositoryDetails(USER_NAME, REPO_NAME, USER_LOCALE);

        assertThat(resultDTO.get())
                .hasFullName(REPOSITORY.getFullName())
                .hasDescription(REPOSITORY.getDescription())
                .hasCloneUrl(REPOSITORY.getCloneUrl())
                .hasStars(REPOSITORY.getStargazersCount());
    }

    @Test
    public void shouldReturnCorrectCreatedDateTimeDTO() {

        when(gitHubApiClient.getRepositoryDetails(USER_NAME, REPO_NAME))
                .thenReturn(REPOSITORY);

        Optional<RepositoryDTO> resultDTO =
                repositoryService.getRepositoryDetails(USER_NAME, REPO_NAME, USER_LOCALE);

        assertThat(resultDTO.get())
                .hasCreatedAt(REPOSITORY.getCreatedAt().toString());   // FIXME
    }
}
