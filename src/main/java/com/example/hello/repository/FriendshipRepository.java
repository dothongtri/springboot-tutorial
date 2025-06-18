package com.example.hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hello.entity.Friendship;
import com.example.hello.entity.User;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query("SELECT f.friend FROM Friendship f WHERE f.user.id = :userId AND f.status = 'ACCEPTED' " +
            "UNION " +
            "SELECT f.user FROM Friendship f WHERE f.friend.id = :userId AND f.status = 'ACCEPTED'")
    List<User> findAcceptedFriendsByUserId(@Param("userId") Long userId);

}