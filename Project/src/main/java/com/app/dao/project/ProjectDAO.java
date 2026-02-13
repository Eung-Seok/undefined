package com.app.dao.project;

import java.util.List;

import com.app.dto.project.Project;

public interface ProjectDAO {
	List<Project> findProjectList();
	
	int saveProject(Project project);

	Project findProjectById(int id);

	int removeProject(int id);

	int modifyProject(Project project);
}
