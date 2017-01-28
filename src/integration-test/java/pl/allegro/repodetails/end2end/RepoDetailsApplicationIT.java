package pl.allegro.repodetails.end2end;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import pl.allegro.repodetails.SpringContextDependend;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class RepoDetailsApplicationIT extends SpringContextDependend {

    @Test
    public void shouldReceiveOctokitRepositoryAcceptingPolish() {

        given()
                .header("Accept-Encoding", "application/json")
                .header("Accept-Language", "pl-pl")
        .when()
                .get("/repositories/octokit/octokit.rb")
        .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("fullName", equalTo("octokit/octokit.rb"))
                .body("description", equalTo("Ruby toolkit for the GitHub API"))
                .body("cloneUrl", equalTo("https://github.com/octokit/octokit.rb.git"))
                .body("stars", greaterThan(2512))
                .body("createdAt", equalTo("10 grudnia 2009, 21:41:49 GMT"));
    }

    @Test
    public void shouldReceiveOctokitRepositoryAcceptingEnglish() {

        given()
                .header("Accept-Encoding", "application/json")
                .header("Accept-Language", "en")
        .when()
                .get("/repositories/octokit/octokit.rb")
        .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("createdAt", equalTo("10 December 2009, 21:41:49 GMT"));
    }

    @Test
    public void shouldReceive404WhenNoRepositoryFound() {

        given()
                .header("Accept-Encoding", "application/json")
                .header("Accept-Language", "pl-pl")
        .when()
                .get("/repositories/nonExistingUser/nonExistingRepo")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON);
    }
}
