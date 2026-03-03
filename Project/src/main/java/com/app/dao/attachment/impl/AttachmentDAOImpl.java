package com.app.dao.attachment.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.attachment.AttachmentDAO;
import com.app.dto.attachment.Attachment;
import com.app.vo.attachment.AttachmentVO;

@Repository
public class AttachmentDAOImpl implements AttachmentDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insertAttachment(AttachmentVO attachment) {
		sqlSessionTemplate.insert("attachment_mapper.insertAttachment", attachment);
	}
	
	@Override
	public List<Attachment> findAttachmentListByProject(int projectId) {
	    return sqlSessionTemplate.selectList("attachment_mapper.findAttachmentListByProject", projectId);
	}

	@Override
	public List<Attachment> findAttachmentList() {
		List<Attachment> attachmentList = sqlSessionTemplate.selectList("attachment_mapper.findAttachmentList");
		return attachmentList;
	}

	@Override
	public int saveAttachment(Attachment attachment) {
		int result = sqlSessionTemplate.insert("attachment_mapper.saveAttachment", attachment);
		return result;
	}

	@Override
	public Attachment findAttachmentById(int id) {
		Attachment attachment = sqlSessionTemplate.selectOne("attachment_mapper.findAttachmentById", id);
		return attachment;
	}

	@Override
	public int removeAttachment(int id) {
		int result = sqlSessionTemplate.delete("attachment_mapper.removeAttachment", id);
		return result;
	}

	@Override
	public int modifyAttachment(Attachment attachment) {
		int result = sqlSessionTemplate.update("attachment_mapper.modifyAttachment", attachment);
		return result;
	}

	@Override
	public int removeAttachmentByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
