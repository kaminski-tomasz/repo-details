package pl.allegro.repodetails.controller;

import org.junit.Before;
import org.junit.Test;
import pl.allegro.repodetails.service.dto.RepositoryDTO;
import pl.allegro.repodetails.service.RepositoryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryControllerTest {

    private static final String USER_NAME = "fakeUserName";
    private static final String REPO_NAME = "fakeRepoName";

    private RepositoryDTO expectedDTO;
    private RepositoryService repoService;
    private RepositoryController repoController;

    @Before
    public void setUp() {
        expectedDTO = mock(RepositoryDTO.class);
        repoService = mock(RepositoryService.class);
        repoController = new RepositoryController(repoService);
    }

    @Test
    public void shouldReplyWithRepositoryDetails() {

        when(repoService.getRepositoryDetails(USER_NAME, REPO_NAME))
                .thenReturn(expectedDTO);

        RepositoryDTO repositoryDTO =
                repoController.getRepositoryDetails(USER_NAME, REPO_NAME);

        assertThat(repositoryDTO).isEqualTo(expectedDTO);
    }
}
