package com.app.service.projectMember.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.projectMember.ProjectMemberDAO;
import com.app.dto.projectMember.ProjectMember;
import com.app.service.projectMember.ProjectMemberService;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

	@Autowired
	ProjectMemberDAO projectMemberDao;

	@Override
	public List<ProjectMember> findProjectMemberList() {
		List<ProjectMember> projectMemberList = projectMemberDao.findProjectMemberList();
		return projectMemberList;
	}

	@Override
	public int saveProjectMember(ProjectMember projectMember) {
		int result = projectMemberDao.saveProjectMember(projectMember);
		return result;
	}

	@Override
	public ProjectMember findProjectMemberById(int id) {
		ProjectMember projectMember = projectMemberDao.findProjectMemberById(id);
		return projectMember;
	}

	@Override
	public int removeProjectMember(int id) {
		int result = projectMemberDao.removeProjectMember(id);
		return result;
	}

	@Override
	public int modifyProjectMember(ProjectMember projectMember) {
		int result = projectMemberDao.modifyProjectMember(projectMember);
		return result;
	}

	@Override
	public List<ProjectMember> findProjectMemberListByProjectId(int projectId) {
		List<ProjectMember> projectMemberList = projectMemberDao.findProjectMemberListByProjectId(projectId);
		return projectMemberList;
	}

	@Transactional
	public int addMembersBulkPerUserRole(int projectId,
	                                     List<Integer> selectedEmpnos,
	                                     Map<Integer, String> roleByEmpno) {

	    if (selectedEmpnos == null || selectedEmpnos.isEmpty()) return 0;

	    List<Map<String, Object>> rows = new ArrayList<>();

	    for (Integer empno : selectedEmpnos) {
	        String role = roleByEmpno != null ? roleByEmpno.get(empno) : null;
	        if (role == null || role.isBlank()) role = "MEMBER";

	        Map<String, Object> row = new HashMap<>();
	        row.put("userId", empno);     // TF_PROJECT_MEMBER.USER_ID
	        row.put("projectRole", role); // TF_PROJECT_MEMBER.PROJECT_ROLE
	        rows.add(row);
	    }

	    return projectMemberDao.insertMembersBulkIgnoreDuplicate(projectId, rows);
	}
}
