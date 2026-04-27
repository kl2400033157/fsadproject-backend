package com.webinarhub.platform.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "webinars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Webinar {

    @Id
    private String id;

    private String title;

    private String description;

    private String instructor;

    private LocalDateTime dateTime;

    private Integer durationMinutes;

    private WebinarStatus status = WebinarStatus.UPCOMING;

    private String streamUrl;

    private String coverImageUrl;

    private Integer maxParticipants = 100;

    private String category;
    
    private boolean reminderSent = false;

    @DBRef(lazy = true)
    private List<Registration> registrations = new ArrayList<>();

    @DBRef(lazy = true)
    private List<Resource> resources = new ArrayList<>();

    public enum WebinarStatus {
        UPCOMING, LIVE, COMPLETED, CANCELLED
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public WebinarStatus getStatus() { return status; }
    public void setStatus(WebinarStatus status) { this.status = status; }
    public String getStreamUrl() { return streamUrl; }
    public void setStreamUrl(String streamUrl) { this.streamUrl = streamUrl; }
    public String getCoverImageUrl() { return coverImageUrl; }
    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }
    public Integer getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public List<Registration> getRegistrations() { return registrations; }
    public void setRegistrations(List<Registration> registrations) { this.registrations = registrations; }
    public List<Resource> getResources() { return resources; }
    public void setResources(List<Resource> resources) { this.resources = resources; }
    public boolean isReminderSent() { return reminderSent; }
    public void setReminderSent(boolean reminderSent) { this.reminderSent = reminderSent; }
}
