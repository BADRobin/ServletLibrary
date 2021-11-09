package oleg.bryl.jdbc;

import oleg.bryl.jdbc.exception.ConnectionException;
import oleg.bryl.jdbc.exception.PropertiesException;
import oleg.bryl.jdbc.exception.ResourcesException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    private String user;
    private String password;
    private String driver;
    private String url;
    private int poolSize;
    private int timeOut;
    private ResourcesQueue<Connection> connections = null;
    private static ConnectionPool connectionPool;

    private ConnectionPool() {
        try {
            init();
            log.info("Инициализирован Connection Pool");
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        log.info("Предоставлен Connection Pool");
        return connectionPool;
    }

    private void init() throws ConnectionException {
        try {
            loadProperties();
            connections = new ResourcesQueue<>(poolSize, timeOut);

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new ConnectionException("Cant find driver for JDBC MySql", e);
            }

            while (connections.size() < poolSize) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connections.addResource(connection);
            }
        } catch (SQLException | PropertiesException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() throws PropertiesException {
        Properties properties = new Properties();
        try {
            properties.load(ConnectionPool.class.getResourceAsStream("/db.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            poolSize = Integer.parseInt(properties.getProperty("pool_size"));
            timeOut = Integer.parseInt(properties.getProperty("timeout"));
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            throw new PropertiesException("Not found properties file with connecting settings", e);
        }
    }

    public Connection getConnection() throws ResourcesException {
        log.info("Запрос на Connection");
        return connections.takeResource();
    }

    public void returnConnection(Connection connection) {
        log.info("Освобожден Connection");
        connections.returnResource(connection);
    }
}
