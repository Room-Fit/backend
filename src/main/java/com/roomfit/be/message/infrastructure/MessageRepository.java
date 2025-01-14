package com.roomfit.be.message.infrastructure;

import com.roomfit.be.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
