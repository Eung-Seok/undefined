package com.app.dao.projectMember;

import java.util.List;
import java.util.Map;

import com.app.dto.projectMember.ProjectMember;

public interface ProjectMemberDAO {
	List<ProjectMember> findProjectMemberList();

	List<ProjectMember> findProjectMemberListByProjectId(int projectId);

	int saveProjectMember(ProjectMember projectMember);

	ProjectMember findProjectMemberById(int id);

	int removeProjectMember(int id);

	int modifyProjectMember(ProjectMember projectMember);

	int insertMembersBulkIgnoreDuplicate(int projectId, List<Map<String, Object>> rows);
}
