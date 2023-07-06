package com.example.issuetracker.repository;
import com.example.issuetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);

    User findByResetToken(String resetToken);
}
