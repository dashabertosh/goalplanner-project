package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.dao.BaseDao;
import by.epam.goalplanner.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements BaseDao<T> {

    private  ProxyConnection connection;
    private Builder<T> builder;

    public AbstractDao(ProxyConnection connection,Builder<T> builder) {
        this.connection = connection;
        this.builder = builder;
    }

    protected boolean executeUpdate(String sql, Object ... params) {
       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           prepareStatement(statement, params);
           return(1 == statement.executeUpdate());
       } catch (SQLException e) {
           return false;
       }
    }

    protected long executeCreateAndGetId(String sql) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (1 == statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        }
        return -1;
    }

    protected List<T> executeQuery(String query, Object... params) {
        List<T> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatement(statement, params);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (!resultSet.wasNull()) {
                        T builtObject = builder.build(resultSet);
                        resultList.add(builtObject);
                    }
                }
            }
        } catch (SQLException e) {
            //logger
        }
        return resultList;
    }

    protected Optional<T> executeQueryForSingleResult(String query, Object... params) {
        List<T> itemsList = executeQuery(query, params);
        Optional<T> result = Optional.empty();
        if (itemsList.size() == 1) {
            T firstItem = itemsList.get(0);
            result = Optional.of(firstItem);
        }
        return result;
    }


    private void prepareStatement(PreparedStatement statement, Object... params) throws SQLException {
        try {
            for (int i = 1; i < params.length + 1; i++) {
                statement.setObject(i, params[i - 1]);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
