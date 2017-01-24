package pl.allegro.repodetails.service;

import pl.allegro.repodetails.service.dto.RepositoryDTO;

public interface RepositoryService {

    RepositoryDTO getRepositoryDetails(String userName, String repoName);
}
