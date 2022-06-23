package com.greenlabs.day.domain;

public class History {
    private long id;
    private long join_id;
    private String start_time;
    private String end_time;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJoin_id() {
        return join_id;
    }

    public void setJoin_id(long join_id) {
        this.join_id = join_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

}
