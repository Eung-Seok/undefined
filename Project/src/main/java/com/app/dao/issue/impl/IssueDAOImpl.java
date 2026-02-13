package com.app.dao.issue.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.issue.IssueDAO;
import com.app.dto.issue.Issue;


@Repository
public class IssueDAOImpl implements IssueDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Issue> findIssueList() {
		List<Issue> issueList = sqlSessionTemplate.selectList("issue_mapper.findIssueList");
		return issueList;
	}
	
}
