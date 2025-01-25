package com.DeeksVault.SpringBoot.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
}
