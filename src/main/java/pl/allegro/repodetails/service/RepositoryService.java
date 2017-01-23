package pl.allegro.repodetails.service;

import pl.allegro.repodetails.domain.RepositoryDetails;

public interface RepositoryService {

    RepositoryDetails getRepositoryDetails(String userName, String repoName);
}
