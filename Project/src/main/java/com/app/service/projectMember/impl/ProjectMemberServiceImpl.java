package com.app.service.projectMember.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.projectMember.ProjectMemberDAO;
import com.app.dto.projectMember.ProjectMember;
import com.app.service.projectMember.ProjectMemberService;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService{

	@Autowired
	ProjectMemberDAO projectMemberDao;

	@Override
	public List<ProjectMember> findProjectMemberList() {
		List<ProjectMember> projectMemberList = projectMemberDao.findProjectMemberList();
		return projectMemberList;
	}
}
