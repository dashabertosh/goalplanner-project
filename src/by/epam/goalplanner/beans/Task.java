package by.epam.goalplanner.beans;

import java.util.Date;

public class Task {

    private long id;
    private String name_task;
    private String description_task;
    private Date date;
    private byte isDone;
    private long goal_id;

    public Task() {

    }

    public Task(long id, String name_task, String description_task, Date date, byte isDone, long goals_ID) {
        this.id = id;
        this.name_task = name_task;
        this.description_task = description_task;
        this.date = date;
        this.isDone = isDone;
        this.goal_id = goals_ID;
    }

    public long getGoals_ID() {
        return goal_id;
    }

    public void setGoals_ID(long goals_ID) {
        this.goal_id = goals_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName_task() {
        return name_task;
    }

    public void setName_task(String name_task) {
        this.name_task = name_task;
    }

    public String getDescription_task() {
        return description_task;
    }

    public void setDescription_task(String description_task) {
        this.description_task = description_task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte getIsDone() {
        return isDone;
    }

    public void setIsDone(byte isDone) {
        this.isDone = isDone;
    }

    public long getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(long goal_id) {
        this.goal_id = goal_id;
    }

    public int isDone() {
        return isDone;
    }

    public void setDone(byte done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name_task='" + name_task + '\'' +
                ", description_task='" + description_task + '\'' +
                ", date=" + date +
                ", isDone=" + isDone +
                ", goal_id=" + goal_id +
                '}';
    }
}
