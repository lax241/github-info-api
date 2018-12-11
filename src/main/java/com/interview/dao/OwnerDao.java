package com.interview.dao;

import com.interview.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerDao extends JpaRepository<Owner, Long> {

    Owner findByOwnerName(String name);

}
