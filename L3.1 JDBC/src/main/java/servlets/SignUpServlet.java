package servlets;

import dbservice.AccountService;
import dbservice.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервис регистрации пользователя.
 */
public class SignUpServlet extends HttpServlet {
    /**
     * Сервис работы с пользователями.
     */
    private final AccountService accountService;

    /**
     * Сервлет регистрации пользователя.
     * @param accountService сервис работы с пользователями
     */
    public SignUpServlet(final AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Регистрация пользователя.
     * @param request запрос с формы
     * @param response ответ
     * @throws ServletException ошибка
     * @throws IOException ошибка
     */
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            if (!accountService.userExists(login, password)) {
                accountService.insertUser(login, password);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
