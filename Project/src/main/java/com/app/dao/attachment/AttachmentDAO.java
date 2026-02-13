package com.app.dao.attachment;

import java.util.List;

import com.app.dto.attachment.Attachment;

public interface AttachmentDAO {
	List<Attachment> findAttachmentList();
}
