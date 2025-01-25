package com.roomfit.be.chat.infrastructure.support;

import com.roomfit.be.chat.domain.Message;
import com.roomfit.be.global.response.PaginationResponse;

import java.util.List;

public interface ChatRoomPaginationRepository {
    PaginationResponse<Message> findMessagesByChatRoomIdPaginated(Long chatRoomId, Long lastMessageId, int pageSize);
    List<Message> findMessagesByChatRoomIdWithOffset(Long chatRoomId, int offset, int pageSize);
}
