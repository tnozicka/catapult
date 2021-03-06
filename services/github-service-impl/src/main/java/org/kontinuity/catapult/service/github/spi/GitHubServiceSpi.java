package org.kontinuity.catapult.service.github.spi;

import org.kontinuity.catapult.service.github.api.GitHubRepository;
import org.kontinuity.catapult.service.github.api.GitHubService;
import org.kontinuity.catapult.service.github.api.GitHubWebhook;
import org.kontinuity.catapult.service.github.api.NoSuchWebhookException;

import java.net.URL;

/**
 * SPI on top of GitHubService to provide with operations that are not exposed
 * in the base API (e.g., for testing purpose).
 */
public interface GitHubServiceSpi extends GitHubService {

    /**
     * Creates a repository with the given information (name and description). The repository will be
     * created by default with no homepage, issues, wiki downloads and will be public.
     *
     * @param repositoryName - the name of the repository
     * @param description - the repository description
     * @return the created {@link GitHubRepository}
     * @throws IllegalArgumentException
     */
    GitHubRepository createRepository(String repositoryName, String description) throws IllegalArgumentException;


    /**
     * Delete a repository specified by its value object representation.
     *
     * @param repository - the value object the represents the GitHub repository
     * @throws IllegalArgumentException
     */
    void deleteRepository(final GitHubRepository repository) throws IllegalArgumentException;
    
    /**
     * Delete a repository specified by its full name.
     *
     * @param repositoryName - GitHub repository name
     * @throws IllegalArgumentException
     */
    void deleteRepository(String repositoryName) throws IllegalArgumentException;

   /**
    * Returns the webhook with the specified url on the specified repository
    * @param repository
    * @param url
    * @throws IllegalArgumentException If either the repository or name are not specified
    * @throws NoSuchWebhookException If the webhook does not exist for this repo
    * @return
    */
    GitHubWebhook getWebhook(GitHubRepository repository, URL url)
            throws IllegalArgumentException, NoSuchWebhookException;

    /**
     * Deletes a webhook in a specific GitHub repository
     * 
     * @param repository - the value object that represents the GitHub repository
     * @param webhook - the value object that represents the GitHub webhook
     * @throws IllegalArgumentException If either parameter is unspecified
     */
    void deleteWebhook(final GitHubRepository repository, GitHubWebhook webhook) throws IllegalArgumentException;


    /**
     * Checks if the repository with the given name exists
     * @param repositoryName
     * @return <code>true</code> if it exists, <code>false</code> otherwise.
     */
	boolean repositoryExists(String repositoryName);


}
