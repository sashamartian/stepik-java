package dbservice.dao;

import dbservice.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * UsersDAO.
 */
public class UsersDAO {
    /**
     * Executor выполнения запросов.
     */
    private Executor executor;

    /**
     * UsersDAO.
     * @param connection соединение с базой
     */
    public UsersDAO(final Connection connection) {
        this.executor = new Executor(connection);
    }

    /**
     * Создание таблицы.
     * @throws SQLException исключение
     */
    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id));");
    }

    /**
     * Добавление пользователя.
     * @param login логин
     * @param password пароль
     * @throws SQLException исключение
     */
    public void insertUser(final String login,
                           final String password) throws SQLException {
        executor.execUpdate("insert into users (login, password) values ('" + login + "', '" + password + "')");
    }

    /**
     * Получение количества строк в базе.
     * @return количество строк
     * @throws SQLException исключение
     */
    public long getCount() throws SQLException {
        return executor.execQuery("select count(*) from users", result -> {
            result.next();
            return result.getLong(1);
        });
    }


    /**
     * Проверка наличия пользователя.
     * @param login логин
     * @param password пароль
     * @return наличие пользователя
     * @throws SQLException исключение
     */
    public boolean userExists(final String login,
                              final String password) throws SQLException {
        return executor.execQuery("select count(*) from users where login = '" + login + "' and password = '" + password + "'", result -> {
            result.next();
            return result.getLong(1);
        }) > 0;
    }
}
