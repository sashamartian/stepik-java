package dbservice;

import dbservice.dao.UsersDAO;
import dbservice.dataSets.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Сервис работы с пользователями.
 */
public class AccountService {
    /**
     * Отображение отладки.
     */
    private static final String HIBERNATE_SHOW_SQL = "false";

    /**
     * Создание таблиц.
     */
    private static final String HIBERNATE_HBM_2_DDL_AUTO = "update";

    /**
     * Фабрика соединений.
     */
    private final SessionFactory sessionFactory;

    /**
     * Сервис работы с пользователями.
     */
    public AccountService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    /**
     * Конфигурация базы H2.
     * @return конфигурация
     */
    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM_2_DDL_AUTO);
        return configuration;
    }

    /**
     * Фабрика соединений.
     * @param configuration конфигурация
     * @return фабрика
     */
    private static SessionFactory createSessionFactory(final Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Проверка пользователя в базе.
     * @param login логин
     * @param password пароль
     * @return есть ли пльзователь
     * @throws DBException исключение
     */
    public boolean userExists(final String login,
                              final String password) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            UsersDataSet dataSet = dao.getUser(login, password);
            session.close();
            return dataSet != null;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    /**
     * Добавление пользователя в базу.
     * @param login логин
     * @param password пароль
     * @throws DBException исключение
     */
    public void insertUser(final String login,
                           final String password) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            long id = dao.insertUser(login, password);
            System.out.println(id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
}
