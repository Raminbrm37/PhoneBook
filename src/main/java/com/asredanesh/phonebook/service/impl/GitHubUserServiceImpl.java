package com.asredanesh.phonebook.service.impl;

import com.asredanesh.phonebook.model.GitHubRepository;
import com.asredanesh.phonebook.model.GitHubUser;
import com.asredanesh.phonebook.repository.GitHubUserRepository;
import com.asredanesh.phonebook.service.GitHubUserService;
import com.asredanesh.phonebook.util.exception.GitHubAccountExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GitHubUserServiceImpl implements GitHubUserService {
    private final String GIT_HUB_API_FETCH_REPOSITORY = "https://api.github.com/users/";
    @Autowired
    private GitHubUserRepository gitHubUserRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GitHubUser save(GitHubUser entity) {

        return gitHubUserRepository.save(entity);
    }

    @Override
    public GitHubUser findById(Long id) {
        return null;
    }

    @Override
    public List<GitHubUser> findAll() {
        return null;
    }

    @Override
    public Boolean remove(Long entity) {
        return null;
    }

    @Override
    public Boolean removeById(GitHubUser id) {
        return null;
    }

    @Override
    public Boolean checkExistGitHubUsername(String username) {
        return gitHubUserRepository.findByLogin(username) != null;
    }

    @Override
    public GitHubUser createGitHubUser(String gitHubUsername) {
        if (checkExistGitHubUsername(gitHubUsername)) {
            throw new GitHubAccountExistException(" : نام کاربری اکانت github  وارد شده تکراری است" + gitHubUsername);
        }
        GitHubUser gitHubUser = new GitHubUser();
        gitHubUser.setLogin(gitHubUsername);
        Set<GitHubRepository> set = new HashSet<>();
        for (LinkedHashMap<String, String> repo : test(gitHubUsername)) {
            GitHubRepository gitHubRepository = new GitHubRepository();
            gitHubRepository.setName(repo.get("name"));
            set.add(gitHubRepository);
        }

        gitHubUser.setGitHubRepositorySet(set);
        return gitHubUserRepository.save(gitHubUser);

    }

    @Override
    public List<LinkedHashMap<String, String>> test(String gitHubUsername) {
        List<LinkedHashMap<String, String>> repos = new ArrayList<>();
        int pageIterator = 0;
        do {
            pageIterator++;
            repos.addAll(restTemplate.getForObject(
                    GIT_HUB_API_FETCH_REPOSITORY + gitHubUsername + "/repos?per_page=100&page=" + pageIterator, List.class));

        } while (repos.size() == 100 * pageIterator);
        return repos;
    }
}
