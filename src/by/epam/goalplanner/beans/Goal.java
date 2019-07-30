package by.epam.goalplanner.beans;


import java.util.List;

public class Goal {

    private long id;
    private String name;
    private String description;
    private long beginDate;
    private long endDate;
    private long userId;
    private long type_id;
    private List<Task> tasks;

    public Goal() {

    }

    public Goal(long id, String name, String description, long beginDate, long endDate, long user, long type_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.userId = user;
        this.type_id = type_id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public long getType_id() {
        return type_id;
    }

    public void setType_id(long type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", userId=" + userId +
                '}';
    }
}
