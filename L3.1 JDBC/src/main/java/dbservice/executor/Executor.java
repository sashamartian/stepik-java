package dbservice.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Executor.
 */
public class Executor {
    /**
     * Соединение с базой.
     */
    private final Connection connection;

    /**
     * Executor.
     * @param connectionDb соеднинение с базой
     */
    public Executor(final Connection connectionDb) {
        this.connection = connectionDb;
    }

    /**
     * Запрос обновления.
     * @param update запрос
     * @throws SQLException исключение
     */
    public void execUpdate(final String update) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(update);
        stmt.close();
    }

    /**
     * Запрос к базе.
     * @param query запрос
     * @param handler обработчик запроса
     * @param <T> возвращаемый класс
     * @return возвращаемый объект
     * @throws SQLException исключение
     */
    public <T> T execQuery(final String query,
                           final ResultHandler<T> handler)
            throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();

        return value;
    }

}
