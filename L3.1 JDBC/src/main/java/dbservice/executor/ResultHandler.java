package dbservice.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Обработчик ответа.
 * @param <T> возвращаемый класс
 */
public interface ResultHandler<T> {
    /**
     * Обработчик ответа.
     * @param resultSet ответ из базы
     * @return возвращаемый класс
     * @throws SQLException исключение
     */
    T handle(ResultSet resultSet) throws SQLException;
}
