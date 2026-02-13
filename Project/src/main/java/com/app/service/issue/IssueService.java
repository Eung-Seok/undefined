package com.app.service.issue;

import java.util.List;

import com.app.dto.issue.Issue;

public interface IssueService {
	List<Issue> findIssueList();

	int saveIssue(Issue issue);

	Issue findIssueById(int id);

	int removeIssue(int id);

	int modifyIssue(Issue issue);
}
