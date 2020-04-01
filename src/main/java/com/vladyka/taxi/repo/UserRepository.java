package com.vladyka.taxi.repo;

import com.vladyka.taxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumber(String phoneNumber);

    User findByUserName(String userName);

    List<User> findAll();

    boolean existsUserByUserName(String userName);

}
