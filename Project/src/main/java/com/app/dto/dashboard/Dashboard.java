package com.app.dto.dashboard;

import java.sql.Date;

import lombok.Data;

@Data
public class Dashboard {
	private int id;
    private String projectName;
    private String pmName;
    private int memberCount;
    private int remainTaskCount;
    private Date endDate;
}
