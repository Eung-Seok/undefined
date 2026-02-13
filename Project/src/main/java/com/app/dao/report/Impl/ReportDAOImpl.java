package com.app.dao.report.Impl;

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
	
}
