package com.app.service.projectMember;

import java.util.List;

import com.app.dto.projectMember.ProjectMember;

public interface ProjectMemberService {
	List<ProjectMember> findProjectMemberList();

	int saveProjectMember(ProjectMember projectMember);

	ProjectMember findProjectMemberById(int id);

	int removeProjectMember(int id);

	int modifyProjectMember(ProjectMember projectMember);
}
