package dbservice;

/**
 * Исключения при работе с базой.
 */
public class DBException extends Exception {
    /**
     * Исключения при работе с базой.
     * @param throwable ошибки
     */
    public DBException(final Throwable throwable) {
        super(throwable);
    }
}
