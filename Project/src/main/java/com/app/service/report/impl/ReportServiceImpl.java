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
}
