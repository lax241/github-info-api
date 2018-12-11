package com.interview.dao;

import com.interview.model.Owner;
import com.interview.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryDao extends JpaRepository<Repository, Long> {

    Repository findByRepositoryOwnerAndRepositoryName(Owner repositoryOwner, String repositoryName);

}