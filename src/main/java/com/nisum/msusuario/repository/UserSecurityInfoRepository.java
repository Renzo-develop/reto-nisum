package com.nisum.msusuario.repository;

import com.nisum.msusuario.entity.UserSecurityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityInfoRepository extends JpaRepository<UserSecurityInfo, Integer> {

    Optional<UserSecurityInfo> findByName(String username);

}
