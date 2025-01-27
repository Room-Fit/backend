package com.roomfit.be.survey.infrastructure.support;

import com.roomfit.be.survey.application.dto.ReplyDTO;

public interface ReplyRepository {
    void saveBulkReply(Long userId, ReplyDTO.Create request);

}
