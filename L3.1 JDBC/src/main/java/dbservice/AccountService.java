package dbservice;

import dbservice.dao.UsersDAO;
import org.h2.jdbcx.JdbcDataSource;
//import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Сервис по работе с клиентами.
 */
public class AccountService {
    /**
     * Соединение с базой.
     */
    private final Connection connection;

    /**
     * Сервис по работе с клиентами.
     */
    public AccountService() {
        this.connection = getH2Connection();
        try {
            createTable();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создание таблицы.
     * @throws DBException ошибка
     */
    public void createTable() throws DBException {
        try {
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /**
     * Добавление пользователя.
     * @param login логин
     * @param password пароль
     * @throws DBException исключение
     */
    public void insertUser(final String login,
                           final String password) throws DBException {
        try {
            UsersDAO dao = new UsersDAO(connection);
            dao.insertUser(login, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /**
     * Количесво строк в базе.
     * @return количество строк
     * @throws DBException исключение
     */
    public long rowCnt() throws DBException {
        try {
            UsersDAO dao = new UsersDAO(connection);
            return dao.getCount();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /**
     * Проверка наличия пользователя.
     * @param login логин
     * @param password пароль
     * @return наличие пользователя
     * @throws DBException исключение
     */
    public boolean userExists(final String login,
                              final String password) throws DBException {
        try {
            UsersDAO dao = new UsersDAO(connection);
            return dao.userExists(login, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /**
     * Соединение в базой H2.
     * @return соединение с базой
     */
    public static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2_db";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
