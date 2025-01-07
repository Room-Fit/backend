package com.roomfit.be.user.infrastructure;

import com.roomfit.be.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
