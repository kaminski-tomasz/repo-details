package pl.allegro.repodetails;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pl.allegro.repodetails.domain.RepositoryDetails;
import pl.allegro.repodetails.service.RepositoryService;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RepositoryControllerIT {

    @LocalServerPort
    private int port;

    @MockBean
    private RepositoryService repositoryService;

    private RepositoryDetails REPO_DETAILS = RepositoryDetails.builder()
            .fullName("myRepo")
            .build();

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        RestAssured.port = port;

        when(repositoryService.getRepositoryDetails(anyString(), anyString()))
                .thenReturn(REPO_DETAILS);
    }

    @Test
    public void shouldTestWithRestAssuredBDDApproach() {
        given()
                .header("Accept-Encoding", "application/json")
        .when()
                .get("/repository/userName/repoName")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("fullName", equalTo("myRepo"));
    }

    @Test
    public void shouldTestWithRestAssuredRegularApproach() {
        // when
        Response response =
                given().
                        header("Accept-Encoding", "application/json").
                when().
                        get("/repository/userName/repoName");
        // then
        assertThat(response.contentType())
                .isEqualTo("application/json;charset=UTF-8");
        assertThat(response.statusCode())
                .isEqualTo(200);
        assertThat(response.body().jsonPath().getString("fullName"))
                .isEqualTo("myRepo");
    }

    @Test
    public void shouldTestWithRestTemplate() {

        String body = restTemplate.getForObject(
                "/repository/userName/repoName", String.class);
        assertThat(body).contains("myRepo");
    }
}
