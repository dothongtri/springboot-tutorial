package com.example.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hello.entity.Friendship;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    
}