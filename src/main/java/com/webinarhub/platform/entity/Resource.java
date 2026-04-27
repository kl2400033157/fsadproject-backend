package com.webinarhub.platform.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    private String id;

    private String title;

    private String fileUrl;

    private ResourceType fileType;

    private String description;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    @DBRef
    private Webinar webinar;

    public enum ResourceType {
        VIDEO, PDF, SLIDE, DOCUMENT, LINK, OTHER
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public ResourceType getFileType() { return fileType; }
    public void setFileType(ResourceType fileType) { this.fileType = fileType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
    public Webinar getWebinar() { return webinar; }
    public void setWebinar(Webinar webinar) { this.webinar = webinar; }
}
