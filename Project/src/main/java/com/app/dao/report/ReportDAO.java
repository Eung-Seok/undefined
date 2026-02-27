package com.app.dao.report;

import java.util.List;

import com.app.dto.report.Report;

public interface ReportDAO {
	List<Report> findReportList();
	
	List<Report> findReportByProjectId(int projectId);
	
	int saveReport(Report report);

	Report findReportById(int id);

	int removeReport(int id);

	int modifyReport(Report report);


}
