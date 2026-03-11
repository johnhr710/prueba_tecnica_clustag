package com.clustag.tasks.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskState state;

    @Column(nullable = false, updatable =  false)
    private Instant createdAt;

    private Instant runningAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @Version
    private long version;

    @PrePersist
    void prePersist(){
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
        if(state == null) state = TaskState.CREATED;
    }

    @PreUpdate
    void preUpdate(){
        updatedAt = Instant.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public TaskState getState() { return state; }
    public void setState(TaskState state) { this.state = state; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getRunningAt() { return runningAt; }
    public void setRunningAt(Instant runningAt) { this.runningAt = runningAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    public long getVersion() { return version; }
    public void setVersion(long version) { this.version = version; }


}