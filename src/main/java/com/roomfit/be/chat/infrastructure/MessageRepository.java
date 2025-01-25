package com.roomfit.be.chat.infrastructure;

import com.roomfit.be.chat.domain.Message;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m.chatRoom.id AS roomId, COUNT(m) AS messageCount FROM messages m GROUP BY m.chatRoom.id")
    List<Tuple> countMessagesByRoomIds();
}
