package com.app.dao.projectMember.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.projectMember.ProjectMemberDAO;
import com.app.dto.projectMember.ProjectMember;

@Repository
public class ProjectMemberDAOImpl implements ProjectMemberDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<ProjectMember> findProjectMemberList() {
		List<ProjectMember> projectMemberList = sqlSessionTemplate.selectList("projectMember_mapper.findProjectMemberList");
		return projectMemberList;
	}
	
}
