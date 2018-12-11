package com.interview.service;

import com.interview.dao.CallDao;
import com.interview.dao.OwnerDao;
import com.interview.dao.RepositoryDao;
import com.interview.dto.RepositoryDTO;
import com.interview.model.Call;
import com.interview.model.Owner;
import com.interview.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GitHubService {

    @Value("${github.url}")
    private String gitHubUrl;

    @Value("${github.api.repos}")
    private String reposPath;

    private CallDao callDao;
    private OwnerDao ownerDao;
    private RepositoryDao repositoryDao;

    @Autowired
    public GitHubService(CallDao callDao, OwnerDao ownerDao, RepositoryDao repositoryDao) {
        this.callDao = callDao;
        this.ownerDao = ownerDao;
        this.repositoryDao = repositoryDao;
    }

    public RepositoryDTO getRepositoryInfo(String ownerName, String repositoryName) throws HttpClientErrorException {
        return new RestTemplate().getForObject(gitHubUrl + reposPath, RepositoryDTO.class, ownerName, repositoryName);
    }

    public void saveSuccessfulCall(RepositoryDTO repositoryDTO, String ip) throws HttpClientErrorException {
        Call call = prepareCall(ip);
        Owner owner = ownerDao.findByOwnerName(repositoryDTO.getOwner());
        if (owner == null) {
            owner = prepareOwner(repositoryDTO.getOwner());
            ownerDao.save(owner);
        }
        Repository repository = repositoryDao.findByRepositoryOwnerAndRepositoryName(owner,repositoryDTO.getName());
        if (repository == null) {
            repository = prepareRepository(repositoryDTO.getName(), owner);
            repositoryDao.save(repository);
        }
        call.setCallRepository(repository);
        callDao.save(call);
    }

    private Call prepareCall(String ip) {
        Call call = new Call();
        call.setCallIp(ip);
        return call;
    }

    private Owner prepareOwner(String ownerName) {
        Owner owner = new Owner();
        owner.setOwnerName(ownerName);
        return owner;
    }

    private Repository prepareRepository(String repositoryName, Owner owner) {
        Repository repository = new Repository();
        repository.setRepositoryName(repositoryName);
        repository.setRepositoryOwner(owner);
        return repository;
    }

    public List<Call> getAllSuccessfulCalls() {
        return callDao.findAll();
    }
}
