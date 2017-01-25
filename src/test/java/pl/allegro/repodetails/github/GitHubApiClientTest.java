package pl.allegro.repodetails.github;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.allegro.repodetails.github.domain.Repository;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.allegro.repodetails.github.domain.RepositoryAssert.assertThat;

public class GitHubApiClientTest {

    private static final String API_HOST = "http://api.host";
    private static final int    API_PORT = 80;
    private static final String REPOS_METHOD = "repos";
    private static final String USER_NAME = "userName";
    private static final String REPO_NAME = "repoName";

    private RestTemplate restTemplate = mock(RestTemplate.class);

    @Test
    public void shouldCallApiWithCorrectUrl() {

        GitHubApiClient apiClient = new GitHubApiClientImpl(restTemplate, API_HOST, API_PORT);

        apiClient.getRepositoryDetails(USER_NAME, REPO_NAME);

        String correctUrl = String.join("/", API_HOST + ":" + API_PORT, REPOS_METHOD, USER_NAME, REPO_NAME);
        verify(restTemplate).getForObject(correctUrl, Repository.class);
    }

    @Test
    public void shouldReturnApiRepositoryDetails() {

        Repository expectedRepo = mock(Repository.class);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(expectedRepo);
        GitHubApiClient apiClient = new GitHubApiClientImpl(restTemplate, API_HOST, API_PORT);

        Repository repo = apiClient.getRepositoryDetails(USER_NAME, REPO_NAME);

        assertThat(repo).isEqualTo(expectedRepo);
    }
}
