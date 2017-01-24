package pl.allegro.repodetails.service;

import org.junit.Test;
import pl.allegro.repodetails.domain.RepositoryDetails;

import static pl.allegro.repodetails.domain.RepositoryDetailsAssert.assertThat;

public class RepositoryServiceTest {

    @Test
    public void shouldBeGood() {

        RepositoryService repositoryService = new RepositoryServiceImpl();

        RepositoryDetails details = repositoryService.getRepositoryDetails(
                "userName", "userRepo");
        assertThat(details).hasFullName("importantName");
    }
}
