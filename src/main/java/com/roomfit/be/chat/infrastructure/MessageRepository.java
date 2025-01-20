package com.roomfit.be.chat.infrastructure;

import com.roomfit.be.chat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
