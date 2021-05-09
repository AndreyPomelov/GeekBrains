import lombok.extern.slf4j.Slf4j;

import java.sql.*;

// Это класс, взаимодействующий с базой данных
@Slf4j
public class DataBaseHandler {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public DataBaseHandler() {
        try {
            setConnection();
            createDB();
            initWriteDB();
            // TODO убрать после тестирования строку ниже:
            DataBaseTest.statement = statement;
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Ошибка базы данных");
        }

    }

    // Открываем соединение
    private static void setConnection() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:claudia.s2db");
    }

    // Создаём базу данных
    private static void createDB() throws SQLException {
        statement = connection.createStatement();
        statement.execute("create table if not exists 'users'" +
                "('login' text primary key, 'password' text);");
    }

    // Заполняем базу данных первоначальными данными
    private static void initWriteDB() {
        try {
            statement.execute("insert into 'users' ('login', 'password') values ('qwe', 'qwe')");
            statement.execute("insert into 'users' ('login', 'password') values ('asd', 'asd')");
            statement.execute("insert into 'users' ('login', 'password') values ('zxc', 'zxc')");
        } catch (SQLException e) {
            log.error("Попытка повторного создания базы данных. База данных уже создана.");
        }
    }

    // Метод, закрывающий базу данных
    private static void closeDB() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

    // Метод, добавляющий в базу данные нового пользователя (регистрация)
    protected static boolean registration(String login, String password) {
        try {
            statement.execute("insert into 'users' ('login', 'password') values ('" + login + "', '" + password + "')");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // Метод, возвращающий пароль пользователя по его логину (для проверки в авторизации)
    protected static String getPassword(String login) throws SQLException {
        return statement.executeQuery("select * from users where login = '" + login + "';").getString("password");
    }
}
