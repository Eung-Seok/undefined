package com.app.service.report;

import java.util.List;

import com.app.dto.report.Report;

public interface ReportService {
	List<Report> findReportList();

	int saveReport(Report report);

	Report findReportById(int id);

	int removeReport(int id);

	int modifyReport(Report report);
	 // ✅ 주간 공정율 계산 메서드 추가
    double calculateWeeklyProgress(int userId);
}
