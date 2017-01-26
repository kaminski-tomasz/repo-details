package pl.allegro.repodetails.github;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.xebialabs.restito.support.junit.NeedsServer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.allegro.repodetails.StubServerDependend;
import pl.allegro.repodetails.TestUtils;
import pl.allegro.repodetails.github.domain.Repository;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.*;
import static com.xebialabs.restito.semantics.Condition.uri;
import static pl.allegro.repodetails.github.domain.RepositoryAssert.assertThat;

public class GitHubApiClientIT extends StubServerDependend {

    private GitHubApiClient apiClient;
    private String apiResponse;

    @Before
    public void setUp() throws Exception {
        apiResponse = TestUtils.loadFileContent("github-api-response.json");
        apiClient = new GitHubApiClientImpl(
                new RestTemplate(), "http://localhost", server.getPort());
    }

    @Test
    @NeedsServer
    public void shouldReadGithubRepositoryDetails() {

        whenHttp(server).match(uri("/repos/octokit/octokit.rb"))
                .then(ok(), stringContent(apiResponse), contentType("application/json"));

        Repository repo = apiClient.getRepositoryDetails("octokit", "octokit.rb");

        assertThat(repo)
                .hasFullName("octokit/octokit.rb")
                .hasDescription("Ruby toolkit for the GitHub API")
                .hasCloneUrl("https://github.com/octokit/octokit.rb.git")
                .hasStargazersCount(2512)
                .hasCreatedAt("2009-12-10T21:41:49Z");
    }
}
