package com.app.service.issue.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.issue.IssueDAO;
import com.app.dto.issue.Issue;
import com.app.service.issue.IssueService;

@Service
public class IssueServiceImpl implements IssueService{

	@Autowired
	IssueDAO issueDao;

	@Override
	public List<Issue> findIssueList() {
		List<Issue> issueList = issueDao.findIssueList();
		return issueList;
	}

	@Override
	public int saveIssue(Issue issue) {
		int result = issueDao.saveIssue(issue);
		return result;
	}

	@Override
	public Issue findIssueById(int id) {
		Issue issue = issueDao.findIssueById(id);
		return issue;
	}

	@Override
	public int removeIssue(int id) {
		int result = issueDao.removeIssue(id);
		return result;
	}

	@Override
	public int modifyIssue(Issue issue) {
		int result = issueDao.modifyIssue(issue);
		return result;
	}
}