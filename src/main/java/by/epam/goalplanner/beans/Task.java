package by.epam.goalplanner.beans;

import java.util.Date;

public class Task {
    private long id;
    private String name;
    private String description;
    private Date date;
    private long goalId;

    public Task() {

    }

    public Task(long id, String name, String description, Date date, long goalId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.goalId = goalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getGoalId() {
        return goalId;
    }

    public void setGoalId(long goalId) {
        this.goalId = goalId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", goalId=" + goalId +
                '}';
    }
}
