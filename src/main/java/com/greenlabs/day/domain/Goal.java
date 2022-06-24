package com.greenlabs.day.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Goal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createTime;
    private Short status;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Entry> entryList = new ArrayList<Entry>();

    public void addEntry(Entry entry) {
        entryList.add(entry);
        entry.setGoal(this);
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }


    public Goal() {
        status = 0;
        createTime = LocalDateTime.now();
    }

    public Goal(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", name=" + description +
                ", status=" + status +
                "}";
    }

}
