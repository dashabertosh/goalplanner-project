package by.epam.goalplanner.validate;

public class XssProtection {

    private String login;
    private String name;
    private String password;
    private String description;


    public static XssProtection signUpProtection(String name, String login, String password) {
        XssProtection protection = new XssProtection();
        protection.name = name == null ? "" : name.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        protection.login = login == null ? "" : login.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        protection.password = password == null ? "" : password.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        return protection;
    }

    public static XssProtection loginProtection(String login, String password) {
        XssProtection protection = new XssProtection();
        protection.login = login == null ? "" : login.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        protection.password = password == null ? "" : password.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        return protection;
    }

    public static XssProtection profileProtection(String name, String description) {
        XssProtection protection = new XssProtection();
        protection.name = name == null ? "" : name.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        protection.description = description == null ? "" : description.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        return protection;
    }




    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }
}
