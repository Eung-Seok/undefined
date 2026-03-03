package com.app.service.attachment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.attachment.AttachmentDAO;
import com.app.dto.attachment.Attachment;
import com.app.service.attachment.AttachmentService;
import com.app.vo.attachment.AttachmentVO;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	AttachmentDAO attachmentDao;
	
	@Override
	public void insertAttachment(AttachmentVO attachment) {
	    attachmentDao.insertAttachment(attachment); // DAO를 호출합니다.
	}

	@Override
	public List<Attachment> findAttachmentListByProject(int projectId) {
	    return attachmentDao.findAttachmentListByProject(projectId);
	}
	
	@Override
	public List<Attachment> findAttachmentList() {
		List<Attachment> attachmentList = attachmentDao.findAttachmentList();
		return attachmentList;
	}

	@Override
	public int saveAttachment(Attachment attachment) {
		int result = attachmentDao.saveAttachment(attachment);
		return result;
	}

	@Override
	public Attachment findAttachmentById(int id) {
		Attachment attachment = attachmentDao.findAttachmentById(id);
		return attachment;
	}

	@Override
	public int removeAttachment(int id) {
		int result = attachmentDao.removeAttachment(id);
		return result;
	}

	@Override
	public int modifyAttachment(Attachment attachment) {
		int result = attachmentDao.modifyAttachment(attachment);
		return result;
	}

	@Override
	public int removeAttachmentByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return 0;
	}
}