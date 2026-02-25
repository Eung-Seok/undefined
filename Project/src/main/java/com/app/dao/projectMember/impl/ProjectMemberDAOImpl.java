package com.app.dao.projectMember.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<ProjectMember> projectMemberList = sqlSessionTemplate
				.selectList("projectMember_mapper.findProjectMemberList");
		return projectMemberList;
	}

	@Override
	public int saveProjectMember(ProjectMember projectMember) {
		int result = sqlSessionTemplate.insert("projectMember_mapper.saveProjectMember", projectMember);
		return result;
	}

	@Override
	public ProjectMember findProjectMemberById(int id) {
		ProjectMember projectMember = sqlSessionTemplate.selectOne("projectMember_mapper.findProjectMemberById", id);
		return projectMember;
	}

	@Override
	public int removeProjectMember(int id) {
		int result = sqlSessionTemplate.delete("projectMember_mapper.removeProjectMember", id);
		return result;
	}

	@Override
	public int modifyProjectMember(ProjectMember projectMember) {
		int result = sqlSessionTemplate.update("projectMember_mapper.modifyProjectMember", projectMember);
		return result;
	}

	@Override
	public List<ProjectMember> findProjectMemberListByProjectId(int projectId) {
		List<ProjectMember> projectMemberList = sqlSessionTemplate
				.selectList("projectMember_mapper.findProjectMemberListByProjectId", projectId);
		return projectMemberList;
	}

	@Override
	public int insertMembersBulkIgnoreDuplicate(int projectId, List<Map<String, Object>> rows) {
	    Map<String, Object> param = new HashMap<>();
	    param.put("projectId", projectId);
	    param.put("rows", rows);

	    return sqlSessionTemplate.insert(
	        "projectMember_mapper.insertMembersBulk",
	        param
	    );
	}

	@Override
	public int removeProjectMemberByProjectIdAndUserId(Long projectId, Long empno) {
		
		Map<String, Object> param = new HashMap<>();
	    param.put("projectId", projectId);
	    param.put("empno", empno);
		
		int result = sqlSessionTemplate.delete("projectMember_mapper.removeProjectMemberByProjectIdAndUserId", param);
		return result;
	}

	@Override
	public int updateMemberRole(int projectId, int empno, String projectRole) {
		
		Map<String, Object> param = new HashMap<>();
	    param.put("projectId", projectId);
	    param.put("empno", empno);
	    param.put("projectRole", projectRole);
		int result = sqlSessionTemplate.update("projectMember_mapper.updateMemberRole", param);
		return result;
	}


}
