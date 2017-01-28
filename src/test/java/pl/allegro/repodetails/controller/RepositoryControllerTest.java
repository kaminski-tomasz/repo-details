package pl.allegro.repodetails.controller;

import org.junit.Before;
import org.junit.Test;
import pl.allegro.repodetails.service.RepositoryService;
import pl.allegro.repodetails.service.dto.RepositoryDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class RepositoryControllerTest {

    private static final String USER_NAME = "userName";
    private static final String REPO_NAME = "repoName";
    private static final Locale USER_LOCALE = Locale.ENGLISH;

    private HttpServletRequest request;
    private RepositoryDTO expectedDTO;
    private RepositoryService repoService;
    private RepositoryController repoController;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        expectedDTO = mock(RepositoryDTO.class);
        repoService = mock(RepositoryService.class);
        repoController = new RepositoryController(repoService);

        when(request.getLocale()).thenReturn(USER_LOCALE);
    }

    @Test
    public void shouldReplyWithRepositoryDetails() throws Exception {

        when(repoService.getRepositoryDetails(USER_NAME, REPO_NAME, USER_LOCALE))
                .thenReturn(Optional.of(expectedDTO));

        RepositoryDTO repositoryDTO = repoController.getRepositoryDetails(
                request, USER_NAME, REPO_NAME);

        assertThat(repositoryDTO).isEqualTo(expectedDTO);
    }

    @Test
    public void shouldThrowExceptionWhenRepositoryNotFound() {

        when(repoService.getRepositoryDetails(anyString(), anyString(), any()))
                .thenReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> {
            repoController.getRepositoryDetails(request, USER_NAME, REPO_NAME);
        });

        assertThat(thrown).isInstanceOf(RepositoryNotFoundException.class);
    }
}
