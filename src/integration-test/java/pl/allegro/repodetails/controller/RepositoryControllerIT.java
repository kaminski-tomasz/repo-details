package pl.allegro.repodetails.controller;

import io.restassured.http.ContentType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import pl.allegro.repodetails.SpringContextDependend;
import pl.allegro.repodetails.StubServerDependend;
import pl.allegro.repodetails.service.RepositoryService;
import pl.allegro.repodetails.service.dto.RepositoryDTO;

import java.util.Locale;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class RepositoryControllerIT extends SpringContextDependend {

    @MockBean
    private RepositoryService repositoryService;

    private static Object[][] getLocaleTestParameters() {
        return new Object[][] {
                {"en", Locale.ENGLISH},
                {"en-gb", Locale.UK},
                {"en-us", Locale.US},
        };
    }

    @Test
    @Parameters(method = "getLocaleTestParameters")
    public void shouldCallServiceWithCorrectLocale(String acceptLang, Locale expectedLocale) {

        when(repositoryService.getRepositoryDetails(anyString(), anyString(), any()))
                .thenReturn(Optional.of(new RepositoryDTO()));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Accept-Language", acceptLang)
        .when()
                .get("/repositories/user/repo")
        .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);

        verify(repositoryService).getRepositoryDetails("user", "repo", expectedLocale);
    }

    @Test
    @Parameters({"repoName", "repoName.a", "name-with-several-parts"})
    public void shouldCallServiceWithCorrectParameters(String repoName) {

        when(repositoryService.getRepositoryDetails(anyString(), anyString(), any()))
                .thenReturn(Optional.of(new RepositoryDTO()));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Accept-Language", "en")
        .when()
                .get("/repositories/user/" + repoName)
        .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);

        verify(repositoryService).getRepositoryDetails("user", repoName, Locale.ENGLISH);
    }

    @Test
    public void shouldReturn404WhenNoRepositoryFound() {

        when(repositoryService.getRepositoryDetails(anyString(), anyString(), any()))
                .thenReturn(Optional.empty());

        given()
                .header("Accept-Encoding", "application/json")
        .when()
                .get("/repositories/user/nonExistingRepo")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON);
    }
}
