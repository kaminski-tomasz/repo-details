package pl.allegro.repodetails.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.repodetails.domain.RepositoryDetails;
import pl.allegro.repodetails.service.RepositoryService;

@RestController
@RequestMapping("/repository")
public class RepositoryController {

    private RepositoryService repoService;

    public RepositoryController(RepositoryService repoService) {
        this.repoService = repoService;
    }

    @RequestMapping(value = "{userName}/{repoName}", method = RequestMethod.GET)
    public RepositoryDetails getRepositoryDetails(@PathVariable String userName,
                                                  @PathVariable String repoName) {
        return repoService.getRepositoryDetails(userName, repoName);
    }
}
