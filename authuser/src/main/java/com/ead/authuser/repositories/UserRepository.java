package com.ead.authuser.repositories;

import com.ead.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
//    @Query(value = "SELECT 0 FROM TB_USERS u WHERE u.userName = :userName",
//            nativeQuery = true)
//    Integer findByUserName(@Param("userName") String username);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
