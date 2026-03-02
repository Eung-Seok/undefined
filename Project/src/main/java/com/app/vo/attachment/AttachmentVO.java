package com.app.vo.attachment; // 실제 본인 패키지 경로로 수정하세요!

import java.util.Date;

public class AttachmentVO {
    private int id;
    private int uploaderUserId;
    private int projectId;
    private int postId;
    private int taskId;
    private String fileName;
    private String originalFileName;
    private String fileUrl;
    private Date createdAt;
    private String category;

    // 기본 생성자
    public AttachmentVO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUploaderUserId() { return uploaderUserId; }
    public void setUploaderUserId(int uploaderUserId) { this.uploaderUserId = uploaderUserId; }
    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getOriginalFileName() { return originalFileName; }
    public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}