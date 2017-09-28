package pl.allegro.repodetails.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.repodetails.service.RepositoryService;
import pl.allegro.repodetails.service.dto.RepositoryDTO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private RepositoryService repoService;

    public RepositoryController(RepositoryService repoService) {
        this.repoService = repoService;
    }

    @RequestMapping(value = "{userName}/{repoName:.*}")
    public RepositoryDTO getRepositoryDetails(
            HttpServletRequest request,
            @PathVariable String userName,
            @PathVariable String repoName
    ) throws RepositoryNotFoundException {
        return repoService.getRepositoryDetails(userName, repoName, request.getLocale())
                          .orElseThrow(RepositoryNotFoundException::new);
    }
}
