package com.app.service.project.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.project.ProjectDAO;
import com.app.dto.project.Project;
import com.app.service.project.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectDAO projectDao;

	@Override
	public List<Project> findProjectList() {
		List<Project> projectList = projectDao.findProjectList();
		return projectList;
	}
}
