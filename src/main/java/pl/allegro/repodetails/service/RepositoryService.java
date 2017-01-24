package pl.allegro.repodetails.service;

public interface RepositoryService {

    RepositoryDTO getRepositoryDetails(String userName, String repoName);
}
