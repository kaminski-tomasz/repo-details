package pl.allegro.repodetails.controller;

import pl.allegro.repodetails.domain.RepositoryDetails;
import pl.allegro.repodetails.service.RepositoryService;

public class RepositoryController {

    private RepositoryService repoService;

    public RepositoryController(RepositoryService repoService) {
        this.repoService = repoService;
    }

    public RepositoryDetails getRepositoryDetails(String userName, String repoName) {
        return repoService.getRepositoryDetails(userName, repoName);
    }
}
