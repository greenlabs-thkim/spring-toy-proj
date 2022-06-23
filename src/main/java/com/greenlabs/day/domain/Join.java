package com.greenlabs.day.domain;

public class Join {
    private long id;
    private long user_id;
    private long goal_id;

    private String join_time;
    private short is_leave;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(long goal_id) {
        this.goal_id = goal_id;
    }

    public String getJoin_time() {
        return join_time;
    }

    public void setJoin_time(String join_time) {
        this.join_time = join_time;
    }

    public short getIs_leave() {
        return is_leave;
    }

    public void setIs_leave(short is_leave) {
        this.is_leave = is_leave;
    }




}
