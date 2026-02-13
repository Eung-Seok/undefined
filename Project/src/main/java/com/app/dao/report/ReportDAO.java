package com.app.dao.report;

import java.util.List;

import com.app.dto.report.Report;

public interface ReportDAO {
	List<Report> findReportList();
}
