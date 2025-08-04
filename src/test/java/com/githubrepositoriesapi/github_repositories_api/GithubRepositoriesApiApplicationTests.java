package com.githubrepositoriesapi.github_repositories_api;

import com.githubrepositoriesapi.github_repositories_api.model.BranchDto;
import com.githubrepositoriesapi.github_repositories_api.model.RepositoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GithubApiIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnRepositoriesForExistingUser() {
		ResponseEntity<RepositoryDto[]> response =
				restTemplate.getForEntity("/repositories/BartoszJatczyszyn", RepositoryDto[].class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().length > 0);

		for (RepositoryDto repo : response.getBody()) {
			assertNotNull(repo.getName());
			assertNotNull(repo.getOwnerLogin());
			assertNotNull(repo.getBranches());
			for (BranchDto branch : repo.getBranches()) {
				assertNotNull(branch.getName());
				assertNotNull(branch.getLastCommitSha());
			}
		}
	}
}
