package com.app.dao.attachment.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.attachment.AttachmentDAO;
import com.app.dto.attachment.Attachment;

@Repository
public class AttachmentDAOImpl implements AttachmentDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Attachment> findAttachmentList() {
		List<Attachment> attachmentList = sqlSessionTemplate.selectList("attachment_mapper.findAttachmentList");
		return attachmentList;
	}
	
}
