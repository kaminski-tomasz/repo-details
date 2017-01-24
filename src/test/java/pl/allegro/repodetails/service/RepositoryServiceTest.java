package pl.allegro.repodetails.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.allegro.repodetails.domain.GitHubRepoDetails;

import static org.mockito.Mockito.*;
import static pl.allegro.repodetails.domain.RepositoryDetailsAssert.assertThat;

public class RepositoryServiceTest {

    private static final String API_HOST = "http://api.github.com/repos";
    private static final String USER_NAME = "octokit";
    private static final String REPO_NAME = "octokit.rb";
    private static final String CORRECT_URL = String.join("/", API_HOST, USER_NAME, REPO_NAME);

    private static final GitHubRepoDetails GIT_HUB_REPO_DETAILS = GitHubRepoDetails.builder()
            .fullName("octokit/octokit.rb")
            .description("Ruby toolkit for the GitHub API")
            .cloneUrl("https://github.com/octokit/octokit.rb.git")
            .stargazersCount(2512)
            .createdAt("2009-12-10T21:41:49Z")
            .build();

    private RestTemplate restTemplate = mock(RestTemplate.class);
    private RepositoryService repositoryService;

    @Before
    public void setUp() {
        repositoryService = new RepositoryServiceImpl(restTemplate, API_HOST);
    }

    @Test
    public void shouldRequestApiWithCorrectUrl() {
        when(restTemplate.getForObject(CORRECT_URL, GitHubRepoDetails.class))
                .thenReturn(GIT_HUB_REPO_DETAILS);

        repositoryService.getRepositoryDetails(USER_NAME, REPO_NAME);

        verify(restTemplate).getForObject(CORRECT_URL, GitHubRepoDetails.class);
    }

    @Test
    public void shouldReturnRepositoryDetails() {
        when(restTemplate.getForObject(CORRECT_URL, GitHubRepoDetails.class))
                .thenReturn(GIT_HUB_REPO_DETAILS);

        RepositoryDetailsDTO result =
                repositoryService.getRepositoryDetails(USER_NAME, REPO_NAME);

        assertThat(result)
                .hasFullName(GIT_HUB_REPO_DETAILS.getFullName())
                .hasDescription(GIT_HUB_REPO_DETAILS.getDescription())
                .hasCloneUrl(GIT_HUB_REPO_DETAILS.getCloneUrl())
                .hasStars(GIT_HUB_REPO_DETAILS.getStargazersCount())
                .hasCreatedAt(GIT_HUB_REPO_DETAILS.getCreatedAt());
    }
}
