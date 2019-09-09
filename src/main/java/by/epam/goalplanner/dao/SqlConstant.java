package by.epam.goalplanner.dao;

public enum SqlConstant {
    GET_ALL_GOALS_WHERE("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` WHERE `user_id` = ?"),
    GET_GOALS_BY_DATE("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` WHERE `begin_date` = ? AND `end_date` = ?"),
    CREATE_GOAL("INSERT INTO `goal` (`name`, `description`, `begin_date`, `end_date`, " +
            "`user_id`, `type_id`) VALUES (?, ?, ?, ?, ?, ?)"),
    UPDATE_GOAL("UPDATE `goal` SET `name` = ?, `description` = ?, " +
            "`begin_date` = ?, `end_date` = ?, `user_id` = ?, `type_id` = ? WHERE `goal`.`id` = ?"),
    DELETE_GOAL("DELETE FROM `goal` WHERE `goal`.`id` = ?"),
    DELETE_WITH_TYPE("DELETE FROM `goal` WHERE `goal`.`type_id` = ?"),
    GET_ALL_GOALS("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` "),
    GET_GOALS_BY_NAME("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` WHERE `name` = ?"),
    SELECT_GOALS_BY_ID("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` WHERE `id` = ?"),
    SELECT_TYPE_BY_ID("SELECT `type`.`id`, `type`.`name` FROM `type` inner join `goal` on " +
            "`type`.`id` = `goal`.`type_id` where `goal`.`id` = ?"),
    DELETE_WITH_USER("DELETE FROM goal WHERE user_id IN (?)"),

    SELECT_ALL_TASK_WHERE("SELECT `id`, `name`, `description`, `date`, `goal_id` FROM `task` WHERE `task`.`goal_id` = ?"),
    GET_TASK_BY_ID("SELECT id, `name`, `description`, `date`, `goal_id` FROM `task` WHERE `id` = ?"),
    CREATE_TASK("INSERT INTO `task` (`name`, `description`, `date`, `goal_id`) VALUES (?, ?, ?, ?)"),
    UPDATE_TASK("UPDATE `task` SET `name` = ?, `description` = ?, `date` = ?, " +
            "`goal_id` = ?, `done` = ? WHERE `task`.`id` = ?"),
    DELETE_TASK("DELETE FROM `task` WHERE `task`.`id` = ?"),
    DELETE_TASK_WITH_GOAL("DELETE FROM `task` WHERE `goal_id` = ?"),
    DELETE_WITH_USER_GOAL("DELETE FROM task WHERE goal_id IN (SELECT `id` FROM `goal` WHERE user_id in (?))"),

    SELECT_ALL_TYPES_WHERE("SELECT `type`.`id`, `type`.`name` FROM `type` inner join goal on type.id = goal.type_id WHERE goal.`user_id` = ?"),
    SELECT_ALL_TYPES("SELECT `id`, `name` FROM `type`"),
    CREATE_TYPE("INSERT INTO `type` (`name`) VALUES (?)"),
    UPDATE_TYPE("UPDATE `type` SET `name` = ? WHERE `type`.`id` = ?"),
    DELETE_TYPE("DELETE FROM `type` WHERE `type`.`id` = ?"),
    SELECT_ID_BY_NAME("SELECT `id`, `name` FROM `type` WHERE `name` = ?"),
    SELECT_SOME_TYPES("SELECT `id`, `name` FROM `type` WHERE `name` IN('Life', 'Study', 'Health', 'Home', 'Hobby', 'Beauty')"),

    FIND_ALL_ROLES("SELECT `id`, `name` FROM `role` WHERE `id` IN (1,2)"),
    SELECT_ALL_USERS_WHERE("SELECT `id`, `login`, `password`, `name`, `role_id` FROM `user` ?"),
    SELECT_ALL_USERS("SELECT `id`, `login`, `password`, `name`, `role_id` FROM `user`"),
    CREATE_USER("INSERT INTO `user` (`login`, `password`, `name`,  `role_id`) VALUES (?, ?, ?, ?)"),
    DELETE_USER("DELETE FROM `user` WHERE `goalplanner`.`user`.`id` = ?"),
    UPDATE_USER("UPDATE `user` SET `login` = ?, `password` = ?, `name` = ?, `role_id` = ? WHERE `user`.`id` = ?"),
    SELECT_USER_BY_LOGIN_AND_PASSWORD("SELECT id, login, password, name, role_id FROM `user` WHERE user.login = ? AND user.password = ?");

    private String name;

    SqlConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
