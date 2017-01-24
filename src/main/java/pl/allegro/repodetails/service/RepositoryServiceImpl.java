package pl.allegro.repodetails.service;

import org.springframework.stereotype.Service;
import pl.allegro.repodetails.domain.RepositoryDetails;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Override
    public RepositoryDetails getRepositoryDetails(String userName, String repoName) {
        return RepositoryDetails.builder()
                .fullName("importantName")
                .build();
    }
}
