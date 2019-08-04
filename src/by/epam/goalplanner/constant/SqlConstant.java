package by.epam.goalplanner.constant;

public enum SqlConstant {
    WHERE_GOAL_ID(" WHERE `task`.`goal_id` = '%d'"),
    WHERE_ID("WHERE id=%d"),

    GET_ALL_GOALS_WHERE("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` ?"),
    GET_GOALS_BY_DATE("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` WHERE `begin_date` = ? AND `end_date` = ?"),
    CREATE_GOAL("INSERT INTO `goal` (`name`, `description`, `begin_date`, `end_date`, " +
            "`user_id`, `type_id`) VALUES (?, ?, ?, ?, ?, ?)"),
    UPDATE_GOAL("UPDATE `goal` SET `name` = '?', `description` = ?, " +
            "`begin_date` = ?, `end_date` = ?, `user_id` = ?, `type_id` = ? WHERE `goal`.`id` = ?"),
    DELETE_GOAL("DELETE FROM `goal` WHERE `goal`.`id` = ?"),
    GET_ALL_GOALS("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal`"),
    GET_GOALS_BY_NAME("SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` WHERE `name` = ?"),

    SELECT_ALL_TASK_WHERE("SELECT `id`, `name`, `description`, `date`, `goal_id` FROM `task` ?"),
    GET_TASKS_BY_DATE("SELECT `id`, `name`, `description`, `date`, `goal_id`" +
            " FROM `task` WHERE `date` = ?"),
    CREATE_TASK("INSERT INTO `task` (`name`, `description`, `date`, `goal_id`, " +
            "`done`) VALUES (?, ?, ?, ?, ?)"),
    UPDATE_TASK("UPDATE `task` SET `name` = ?, `description` = ?, `date` = ?, " +
            "`goal_id` = ?, `done` = ? WHERE `task`.`id` = ?"),
    DELETE_TASK("DELETE FROM `task` WHERE `task`.`id` = ?"),

    SELECT_ALL_TYPES_WHERE("SELECT `id`, `name_type` FROM `type` ?"),
    CREATE_TYPE("INSERT INTO `type` (`name`) VALUES (?)"),
    UPDATE_TYPE("UPDATE `type` SET `name` = ? WHERE `type`.`id` = ?"),
    DELETE_TYPE("DELETE FROM `type` WHERE `type`.`id` = ?"),
    SELECT_TYPE_ID_BY_NAME("SELECT `id` FROM `type` WHERE `name` = ?"),

    FIND_USER_BY_NAME("SELECT name FROM `user` WHERE user.name = ?"),
    SELECT_ALL_USERS_WHERE("SELECT `id`, `login`, `password`, `name`, `role_id` FROM `user` ?"),
    SELECT_ALL_USERS("SELECT `id`, `login`, `password`, `name`, `role_id` FROM `user`"),
    CREATE_USER("INSERT INTO `user` (`login`, `password`, `name`,  `role_id`) VALUES (?, ?, ?, ?)"),
    DELETE_USER("DELETE FROM `user` WHERE `goalplanner`.`user`.`id` = ?"),
    UPDATE_USER("UPDATE `user` SET `login` = ?, `password` = ?, `name` = ?, `roles_id` = ? WHERE `user`.`id` = ?"),
    SELECT_USER_BY_LOGIN_AND_PASSWORD("SELECT id, login, password, name, role_id FROM `user` WHERE user.login = ? AND user.password = ?");

    private String name;

    SqlConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
