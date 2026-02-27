package com.app.service.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.report.ReportDAO;
import com.app.dto.report.Report;
import com.app.service.report.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	ReportDAO reportDao;

	@Override
	public List<Report> findReportList() {
		List<Report> reportList = reportDao.findReportList();
		return reportList;
	}

	@Override
	public int saveReport(Report report) {
		int result = reportDao.saveReport(report);
		return result;
	}

	@Override
	public Report findReportById(int id) {
		Report report = reportDao.findReportById(id);
		return report;
	}

	@Override
	public int removeReport(int id) {
		int result = reportDao.removeReport(id);
		return result;
	}

	@Override
	public int modifyReport(Report report) {
		int result = reportDao.modifyReport(report);
		return result;
	}

	@Override
	public List<Report> findReportByProjectId(int projectId) {
		List<Report> reportList = reportDao.findReportByProjectId(projectId);
		return reportList;
	}
}
