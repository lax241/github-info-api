package com.interview.dao;

import com.interview.model.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallDao extends JpaRepository<Call, Long> {
}
