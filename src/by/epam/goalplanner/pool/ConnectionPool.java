package by.epam.goalplanner.pool;

import by.epam.goalplanner.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;
    
    private static int POOL_SIZE = 5;

    private static BlockingQueue<ProxyConnection> openConnection = new ArrayBlockingQueue<>(POOL_SIZE);
    private static BlockingQueue<ProxyConnection> usedConnection = new ArrayBlockingQueue<>(POOL_SIZE);

    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);


    private ConnectionPool() {
        init();
    }

    public void init() {
        ConnectionCreator creator = ConnectionCreator.INSTANCE;
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = creator.createConnection();
                ProxyConnection proxyConnectionConnection = new ProxyConnection(connection);
                openConnection.add(proxyConnectionConnection);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = openConnection.take();
            usedConnection.add(connection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void closePool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                openConnection.take().realClose();
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            ProxyConnection pConnection = (ProxyConnection) connection;
            usedConnection.remove(connection);
            openConnection.offer(pConnection);
        }
    }

}
