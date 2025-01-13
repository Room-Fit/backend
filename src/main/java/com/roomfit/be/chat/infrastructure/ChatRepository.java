package com.roomfit.be.chat.infrastructure;

import com.roomfit.be.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
}