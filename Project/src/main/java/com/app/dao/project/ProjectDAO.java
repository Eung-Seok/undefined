package com.app.dao.project;

import java.util.List;

import com.app.dto.project.Project;

public interface ProjectDAO {
	List<Project> findProjectList();
}
