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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    protected long executeCreateAndGetId(String sql) throws SQLException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (1 == statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return -1;
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

    protected Optional<T> executeQueryForSingleResult(String query, Object... params) throws DaoException {
        List<T> itemsList = executeQuery(query, params);
        Optional<T> result = Optional.empty();
        if (itemsList.size() == 1) {
            T firstItem = itemsList.get(0);
            result = Optional.of(firstItem);
        }
        return result;
    }


    private void prepareStatement(PreparedStatement statement, Object... params) throws SQLException {
            for (int i = 1; i < params.length + 1; i++) {
                statement.setObject(i, params[i - 1]);
            }
    }
}
