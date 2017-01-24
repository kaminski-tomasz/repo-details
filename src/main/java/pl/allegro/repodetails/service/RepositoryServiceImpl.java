package pl.allegro.repodetails.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.allegro.repodetails.domain.GitHubRepoDetails;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private RestTemplate restTemplate;
    private String apiHost;

    public RepositoryServiceImpl(RestTemplate restTemplate, String apiHost) {
        this.restTemplate = restTemplate;
        this.apiHost = apiHost;
    }

    @Override
    public RepositoryDetailsDTO getRepositoryDetails(String userName, String repoName) {

        GitHubRepoDetails repo =
                restTemplate.getForObject(String.join("/", apiHost, userName, repoName),
                    GitHubRepoDetails.class);

        return RepositoryDetailsDTO.builder()
                .fullName(repo.getFullName())
                .description(repo.getDescription())
                .cloneUrl(repo.getCloneUrl())
                .stars(repo.getStargazersCount())
                .createdAt(repo.getCreatedAt())
                .build();
    }

}
