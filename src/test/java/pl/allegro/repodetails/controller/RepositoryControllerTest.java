package pl.allegro.repodetails.controller;

import org.junit.Before;
import org.junit.Test;
import pl.allegro.repodetails.service.RepositoryService;
import pl.allegro.repodetails.service.dto.RepositoryDTO;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryControllerTest {

    private static final String USER_NAME = "userName";
    private static final String REPO_NAME = "repoName";

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
    public void shouldReplyWithRepositoryDetails() throws Exception {

        when(repoService.getRepositoryDetails(USER_NAME, REPO_NAME))
                .thenReturn(Optional.of(expectedDTO));

        RepositoryDTO repositoryDTO =
                repoController.getRepositoryDetails(USER_NAME, REPO_NAME);

        assertThat(repositoryDTO).isEqualTo(expectedDTO);
    }

    @Test
    public void shouldThrowExceptionWhenRepositoryNotFound() {

        when(repoService.getRepositoryDetails(USER_NAME, REPO_NAME))
                .thenReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> {
            repoController.getRepositoryDetails(USER_NAME, REPO_NAME);
        });

        assertThat(thrown).isInstanceOf(RepositoryNotFoundException.class);
    }
}
