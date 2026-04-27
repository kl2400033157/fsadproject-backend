package com.webinarhub.platform.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "registrations")
@CompoundIndex(def = "{'userId': 1, 'webinarId': 1}", unique = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @Id
    private String id;

    // Explicit string IDs for reliable querying (avoids @DBRef ObjectId mismatch)
    private String userId;
    private String webinarId;

    @DBRef
    private User user;

    @DBRef
    private Webinar webinar;

    private LocalDateTime registeredAt = LocalDateTime.now();
    private Boolean attended = false;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getWebinarId() { return webinarId; }
    public void setWebinarId(String webinarId) { this.webinarId = webinarId; }
    public User getUser() { return user; }
    public void setUser(User user) {
        this.user = user;
        if (user != null) this.userId = user.getId();
    }
    public Webinar getWebinar() { return webinar; }
    public void setWebinar(Webinar webinar) {
        this.webinar = webinar;
        if (webinar != null) this.webinarId = webinar.getId();
    }
    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }
    public Boolean getAttended() { return attended; }
    public void setAttended(Boolean attended) { this.attended = attended; }
}
