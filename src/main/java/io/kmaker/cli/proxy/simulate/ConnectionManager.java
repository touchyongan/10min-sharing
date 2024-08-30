package io.kmaker.cli.proxy.simulate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static Connection getConnection(final boolean autoCommit) throws SQLException {
        if (connectionHolder.get() == null) {
            final var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sharing?user=postgres&password=root");
            connection.setAutoCommit(autoCommit);
            connectionHolder.set(connection);
        }
        return connectionHolder.get();
    }

    public static Connection getConnection() throws SQLException {
        return getConnection(true);
    }

    public static void commit() throws SQLException {
        final var connection = connectionHolder.get();
        if (connection != null) {
            connection.commit();
            close();
        }
    }

    public static void rollback() throws SQLException {
        final var connection = connectionHolder.get();
        if (connection != null) {
            connection.rollback();
            close();
        }
    }

    private static void close() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection != null) {
            connection.close();
            connectionHolder.remove();
        }
    }

}
