package com.app.dao.project.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.project.ProjectDAO;
import com.app.dto.project.Project;


@Repository
public class ProjectDAOImpl implements ProjectDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Project> findProjectList() {
		List<Project> projectList = sqlSessionTemplate.selectList("project_mapper.findProjectList");
		return projectList;
	}

	@Override
	public int saveProject(Project project) {
		int result = sqlSessionTemplate.insert("project_mapper.saveProject", project);
		return result;
	}

	@Override
	public Project findProjectById(int id) {
		Project project = sqlSessionTemplate.selectOne("project_mapper.findProjectById", id);
		return project;
	}

	@Override
	public int removeProject(int id) {
		int result = sqlSessionTemplate.delete("project_mapper.removeProject", id);
		return result;
	}

	@Override
	public int modifyProject(Project project) {
		int result = sqlSessionTemplate.update("project_mapper.modifyProject", project);
		return result;
	}
	
}