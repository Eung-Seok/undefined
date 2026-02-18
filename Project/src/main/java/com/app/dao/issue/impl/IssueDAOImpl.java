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

	@Override
	public int saveIssue(Issue issue) {
		int result = sqlSessionTemplate.insert("issue_mapper.saveIssue", issue);
		return result;
	}

	@Override
	public Issue findIssueById(int id) {
		Issue issue = sqlSessionTemplate.selectOne("issue_mapper.findIssueById", id);
		return issue;
	}

	@Override
	public int removeIssue(int id) {
		int result = sqlSessionTemplate.delete("issue_mapper.removeIssue", id);
		return result;
	}

	@Override
	public int modifyIssue(Issue issue) {
		int result = sqlSessionTemplate.update("issue_mapper.modifyIssue", issue);
		return result;
	}
	
}
