package com.greenlabs.day.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long goalId;

    private LocalDateTime entryTime;
    private Short isLeave;

    public Entry() {
        entryTime = LocalDateTime.now();
        isLeave = 0;
    }

    public Entry(Long userId, Long goalId) {
        this();
        this.userId = userId;
        this.goalId = goalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goal_id) {
        this.goalId = goal_id;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public short getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(short is_leave) {
        this.isLeave = is_leave;
    }




}
