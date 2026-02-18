package com.app.service.project;

import java.util.List;

import com.app.dto.project.Project;

public interface ProjectService {
	List<Project> findProjectList();

	int saveProject(Project project);

	Project findProjectById(int id);

	int removeProject(int id);

	int modifyProject(Project project);
}
