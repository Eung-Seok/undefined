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
	
}