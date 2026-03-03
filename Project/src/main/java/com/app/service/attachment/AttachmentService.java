package com.app.service.attachment;

import java.util.List;

import com.app.dto.attachment.Attachment;
import com.app.vo.attachment.AttachmentVO;

public interface AttachmentService {

	List<Attachment> findAttachmentListByProject(int projectId);
	
	int removeAttachmentByProjectId(int projectId);

	List<Attachment> findAttachmentList();

	int saveAttachment(Attachment attachment);

	Attachment findAttachmentById(int id);

	int removeAttachment(int id);

	int modifyAttachment(Attachment attachment);
	
	void insertAttachment(AttachmentVO attachment);
}
