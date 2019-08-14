package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.dao.builder.Builder;
import by.epam.goalplanner.dao.BaseDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.pool.ConnectionPool;
import by.epam.goalplanner.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements BaseDao<T> {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Builder<T> builder;

    AbstractDao(Builder<T> builder) {
        this.builder = builder;
    }

    protected boolean executeUpdate(String sql, Object... params) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatement(statement, params);
            LOGGER.debug("Prepared statement: {}", statement);
            return (1 == statement.executeUpdate());
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
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
            LOGGER.error(e);
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return resultList;
    }

    private void prepareStatement(PreparedStatement statement, Object... params) throws SQLException {
            for (int i = 1; i < params.length + 1; i++) {
                statement.setObject(i, params[i - 1]);
            }
    }
}
