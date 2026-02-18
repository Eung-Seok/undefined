package com.app.service.attachment;

import java.util.List;

import com.app.dto.attachment.Attachment;

public interface AttachmentService {
	List<Attachment> findAttachmentList();

	int saveAttachment(Attachment attachment);

	Attachment findAttachmentById(int id);

	int removeAttachment(int id);

	int modifyAttachment(Attachment attachment);
}
