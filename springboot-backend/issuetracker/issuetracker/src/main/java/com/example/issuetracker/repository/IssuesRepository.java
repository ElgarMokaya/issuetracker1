package com.example.issuetracker.repository;

import com.example.issuetracker.model.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssuesRepository extends CrudRepository<Issues,Long> {
    List<Issues> findByClientId(Long clientId);

    List<Issues> findByStatus(String aNew);
}
