package com.roomfit.be.chat.infrastructure;

import com.roomfit.be.chat.domain.ChatRoom;
import com.roomfit.be.chat.domain.ChatRoomType;
import com.roomfit.be.chat.domain.Message;
import com.roomfit.be.chat.infrastructure.support.ChatRoomPaginationRepository;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> , ChatRoomPaginationRepository {

    @Query("SELECT m FROM messages m WHERE m.chatRoom.id = :chatRoomId")
    List<Message> findMessagesByChatRoomId(@Param("chatRoomId") Long chatRoomId);

    List<ChatRoom> findByType(ChatRoomType type);
}