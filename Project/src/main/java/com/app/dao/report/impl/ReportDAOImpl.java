package com.app.dao.report.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.report.ReportDAO;
import com.app.dto.report.Report;

@Repository
public class ReportDAOImpl implements ReportDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Report> findReportList() {
		List<Report> reportList = sqlSessionTemplate.selectList("report_mapper.findReportList");
		return reportList;
	}

	@Override
	public int saveReport(Report report) {
		int result = sqlSessionTemplate.insert("report_mapper.saveReport", report);
		return result;
	}

	@Override
	public Report findReportById(int id) {
		Report report = sqlSessionTemplate.selectOne("report_mapper.findReportById",id);
		return report;
	}

	@Override
	public int removeReport(int id) {
		int result = sqlSessionTemplate.delete("report_mapper.removeReport", id);
		return result;
	}

	@Override
	public int modifyReport(Report report) {
		int result = sqlSessionTemplate.update("report_mapper.modifyReport", report);
		return result;
	}

	@Override
	public List<Report> findReportByProjectId(int projectId) {
		List<Report> reportList = sqlSessionTemplate.selectList("report_mapper.findReportByProjectId", projectId);
		return reportList;
	}

	@Override
	public int removeReportByProjectId(int projectId) {
		int result = sqlSessionTemplate.delete("report_mapper.removeReportByProjectId", projectId);
		return result;
	}
	
}
