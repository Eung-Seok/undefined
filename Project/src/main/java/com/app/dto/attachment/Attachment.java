package com.app.dto.attachment;

import java.util.Date;
import lombok.Data;

@Data
public class Attachment {
    private int id;
    private int uploaderUserId;
    private Integer postId; 
    private Integer taskId;
    private Integer projectId; 
    private String fileName;
    private String originalFileName;
    private String fileUrl;
    private String category;
    private Date createdAt;
}