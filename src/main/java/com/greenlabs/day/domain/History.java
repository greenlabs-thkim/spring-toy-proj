package com.greenlabs.day.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class History {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    private Entry entry;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public History() {
        startTime = LocalDateTime.now();
    }

    public History(Entry entry) {
        this();
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "History{" +
                "Entry=" + entry.getUser().getEmail() + "/" + entry.getGoal().getName() +
                ", startTime=" + startTime.toString() +
                ", endTime=" + (endTime == null ? "null" : endTime.toString()) +
                "}";
    }
}
