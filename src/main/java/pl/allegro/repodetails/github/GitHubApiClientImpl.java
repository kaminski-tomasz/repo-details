package pl.allegro.repodetails.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.allegro.repodetails.github.domain.Repository;

@Service
class GitHubApiClientImpl implements GitHubApiClient {

    private static final String REPOS_METHOD = "repos";

    private RestTemplate restTemplate;
    private String apiUrl;

    GitHubApiClientImpl(RestTemplate restTemplate,
                        @Value("${github.api.host}") String apiHost,
                        @Value("${github.api.port}") int apiPort) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiHost + ":" + apiPort;
    }

    @Override
    public Repository getRepositoryDetails(String userName, String repoName) {
        return restTemplate.getForObject(createUrl(userName, repoName), Repository.class);
    }

    private String createUrl(String userName, String repoName) {
        return String.join("/", apiUrl, REPOS_METHOD, userName, repoName);
    }
}
