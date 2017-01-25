package pl.allegro.repodetails.github;

import org.springframework.web.client.RestTemplate;
import pl.allegro.repodetails.github.domain.Repository;

class GitHubApiClientImpl implements GitHubApiClient {

    private static final String REPOS_METHOD = "repos";

    private RestTemplate restTemplate;
    private String apiUrl;

    GitHubApiClientImpl(RestTemplate restTemplate, String apiHost, int apiPort) {
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
