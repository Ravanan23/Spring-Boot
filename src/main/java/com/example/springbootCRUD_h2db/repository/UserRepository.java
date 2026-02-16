package com.example.springbootCRUD_h2db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootCRUD_h2db.entity.User;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    //JPQL Query
    // @Query("SELECT u FROM User u WHERE u.email = :email")
    @Query(value = "SELECT * FROM employees WHERE email = :email and name= :name", nativeQuery = true)
    User findByEmail(@Param("email") String email, @Param("name") String name);

    //Native Query
    @Query(value = "SELECT * FROM employees WHERE name = :name", nativeQuery = true)
    List<User> findByNameNative(@Param("name") String name);

    //Update Query
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name WHERE u.id = :id")
    int updateUserName(@Param("id") Long id,
            @Param("name") String name);

            

    //Delete Query
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteUserByIdCustom(@Param("id") Long id);

}
