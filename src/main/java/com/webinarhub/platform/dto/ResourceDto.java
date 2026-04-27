package com.webinarhub.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {

    private String id;
    private String title;
    private String fileUrl;
    private String fileType;
    private String description;
    private LocalDateTime uploadedAt;
    private String webinarId;
    private String webinarTitle;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
    public String getWebinarId() { return webinarId; }
    public void setWebinarId(String webinarId) { this.webinarId = webinarId; }
    public String getWebinarTitle() { return webinarTitle; }
    public void setWebinarTitle(String webinarTitle) { this.webinarTitle = webinarTitle; }
}
