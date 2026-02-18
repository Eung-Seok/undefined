package com.app.dao.attachment;

import java.util.List;

import com.app.dto.attachment.Attachment;

public interface AttachmentDAO {
	List<Attachment> findAttachmentList();

	int saveAttachment(Attachment attachment);

	Attachment findAttachmentById(int id);

	int removeAttachment(int id);

	int modifyAttachment(Attachment attachment);
}
