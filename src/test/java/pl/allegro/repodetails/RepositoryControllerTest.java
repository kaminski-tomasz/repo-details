package pl.allegro.repodetails;

import org.junit.Before;
import org.junit.Test;
import pl.allegro.repodetails.controller.RepositoryController;
import pl.allegro.repodetails.service.RepositoryDetailsDTO;
import pl.allegro.repodetails.service.RepositoryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryControllerTest {

    private static final String USER_NAME = "fakeUserName";
    private static final String REPO_NAME = "fakeRepoName";

    private RepositoryDetailsDTO givenDetails;
    private RepositoryService repoService;
    private RepositoryController repoController;

    @Before
    public void setUp() {
        givenDetails = mock(RepositoryDetailsDTO.class);
        repoService = mock(RepositoryService.class);
        repoController = new RepositoryController(repoService);
    }

    @Test
    public void shouldReturnRepositoryDetails() {
        when(repoService.getRepositoryDetails(USER_NAME, REPO_NAME))
                .thenReturn(givenDetails);

        RepositoryDetailsDTO resultingDetails =
                repoController.getRepositoryDetails(USER_NAME, REPO_NAME);

        assertThat(resultingDetails).isEqualTo(givenDetails);
    }
}
