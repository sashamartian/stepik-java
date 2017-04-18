package dbservice.dataSets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * DataSet пользователя.
 */
@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    /**
     * ID пользователя.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Login пользователя.
     */
    @Column(name = "login")
    private String login;

    /**
     * Password пользователя.
     */
    @Column(name = "password")
    private String password;

    /**
     * Important to Hibernate!
     */
    public UsersDataSet() {
    }

    public UsersDataSet(final String login,
                        final String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * getId.
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * setId
     * @param userId id
     */
    public void setId(final long userId) {
        this.id = userId;
    }

    /**
     * getLogin.
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * setLogin.
     * @param userLogin login
     */
    public void setLogin(final String userLogin) {
        this.login = userLogin;
    }

    /**
     * getPassword.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword.
     * @param userPassword password
     */
    public void setPassword(final String userPassword) {
        this.password = userPassword;
    }
}
