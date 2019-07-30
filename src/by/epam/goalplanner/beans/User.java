package by.epam.goalplanner.beans;

public class User {

    private long id;
    private String login;
    private String password;
    private String name;
    private long roles_ID;

    public User() {

    }

    public User(long id, String login, String password, String name, long roles_ID) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.roles_ID = roles_ID;
    }

    public long getRoles_ID() {
        return roles_ID;
    }

    public void setRoles_id(long roles_id) {
        this.roles_ID = roles_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", roles_ID=" + roles_ID +
                '}';
    }
}
