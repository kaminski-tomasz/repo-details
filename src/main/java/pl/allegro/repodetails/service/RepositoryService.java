package pl.allegro.repodetails.service;

public interface RepositoryService {

    RepositoryDetailsDTO getRepositoryDetails(String userName, String repoName);
}
