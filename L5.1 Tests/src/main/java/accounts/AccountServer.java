package accounts;

/**
 * AccountServer.
 */
public class AccountServer implements AccountServerI {
    private int usersLimit;

    public AccountServer(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }
}
