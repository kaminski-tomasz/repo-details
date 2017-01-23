package pl.allegro.repodetails;

import org.junit.Test;
import pl.allegro.repodetails.controller.RepositoryController;
import pl.allegro.repodetails.domain.RepositoryDetails;
import pl.allegro.repodetails.service.RepositoryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryControllerTest {

    private static final String USER_NAME = "fakeUserName";
    private static final String REPO_NAME = "fakeRepoName";

    @Test
    public void shouldReturnRepositoryDetails() {

        RepositoryDetails givenDetails = mock(RepositoryDetails.class);
        RepositoryService repoService = mock(RepositoryService.class);
        when(repoService.getRepositoryDetails(USER_NAME, REPO_NAME))
                .thenReturn(givenDetails);

        RepositoryController repoController = new RepositoryController(repoService);
        RepositoryDetails resultingDetails = repoController.getRepositoryDetails(USER_NAME, REPO_NAME);

        assertThat(resultingDetails).isEqualTo(givenDetails);
    }
}
