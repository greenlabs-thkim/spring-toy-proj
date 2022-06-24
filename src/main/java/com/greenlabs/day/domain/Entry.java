package com.greenlabs.day.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Entry {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    private LocalDateTime entryTime;
    private Short isLeave;

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<History> historyList;

    public void addHistory(History history) {
        historyList.add(history);
        history.setEntry(this);
    }

    public Entry() {
        entryTime = LocalDateTime.now();
        isLeave = 0;
    }

    public Entry(User user, Goal goal) {
        this();
        this.user = user;
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", user=" + user +
                ", goal=" + goal +
                "}";
    }
}
