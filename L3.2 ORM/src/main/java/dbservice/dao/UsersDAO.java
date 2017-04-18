package dbservice.dao;

import dbservice.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * UsersDAO.
 */
public class UsersDAO {
    /**
     * Сессия к базе.
     */
    private Session session;

    /**
     * UsersDAO.
     * @param dbSession сессия к базе
     */
    public UsersDAO(final Session dbSession) {
        this.session = dbSession;
    }

    /**
     * Проверка наличия пользователя в базе.
     * @param login логин
     * @param password пароль
     * @return DataSet пользователя
     * @throws HibernateException исключение
     */
    public UsersDataSet getUser(final String login,
                                final String password) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return (UsersDataSet) criteria
                .add(Restrictions.eq("login", login))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    /**
     * Добавление пользователя в базу.
     * @param login логин
     * @param password пароль
     * @return id пользователя
     * @throws HibernateException исключение
     */
    public long insertUser(final String login,
                           final String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login, password));
    }
}
