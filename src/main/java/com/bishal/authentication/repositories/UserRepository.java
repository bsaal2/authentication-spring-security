package com.bishal.authentication.repositories;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bishal.authentication.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Nullable
    User findByEmail(String email);
}
