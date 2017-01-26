package pl.allegro.repodetails.service;

import pl.allegro.repodetails.service.dto.RepositoryDTO;

import java.util.Optional;

public interface RepositoryService {

    Optional<RepositoryDTO> getRepositoryDetails(String userName, String repoName);
}
