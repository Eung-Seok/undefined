package com.app.service.attachment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.attachment.AttachmentDAO;
import com.app.dto.attachment.Attachment;
import com.app.service.attachment.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	AttachmentDAO attachmentDao;

	@Override
	public List<Attachment> findAttachmentList() {
		List<Attachment> attachmentList = attachmentDao.findAttachmentList();
		return attachmentList;
	}
}