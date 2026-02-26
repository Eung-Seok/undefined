package com.app.service.projectMember;

import java.util.List;
import java.util.Map;

import com.app.dto.projectMember.ProjectMember;

public interface ProjectMemberService {
	List<ProjectMember> findProjectMemberList();

	List<ProjectMember> findProjectMemberListByProjectId(int projectId);

	int saveProjectMember(ProjectMember projectMember);

	ProjectMember findProjectMemberById(int id);

	int removeProjectMember(int id);

	int modifyProjectMember(ProjectMember projectMember);

	int addMembersBulkPerUserRole(int projectId, List<Integer> selectedEmpnos, Map<Integer, String> roleByEmpno);
	
	int removeProjectMemberByProjectIdAndUserId(Long projectId, Long empno);
	
	int updateMemberRole(int projectId, int empno, String projectRole);
}
