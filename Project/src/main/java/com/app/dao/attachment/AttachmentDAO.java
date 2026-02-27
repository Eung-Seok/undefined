package com.app.dao.attachment;

import java.util.List;

import com.app.dto.attachment.Attachment;
import com.app.vo.attachment.AttachmentVO;

public interface AttachmentDAO {

	List<Attachment> findAttachmentListByProject(int projectId);

	List<Attachment> findAttachmentList();

	int saveAttachment(Attachment attachment);

	Attachment findAttachmentById(int id);

	int removeAttachment(int id);

	int modifyAttachment(Attachment attachment);
	
	void insertAttachment(AttachmentVO attachment);
}
