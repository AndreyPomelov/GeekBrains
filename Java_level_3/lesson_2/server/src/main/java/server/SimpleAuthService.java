package server;

import java.io.EOFException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService{

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    // Метод, меняющий ник пользователя
    public static void changeNick(String oldNick, String newNick) throws SQLException {
        statement.execute("UPDATE users " +
                "SET nickname = '" + newNick + "' " +
                "WHERE nickname = '" + oldNick + "'");
    }

    public static void setConnection() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:kvazi.s2db");
    }

    public static void createDb() throws SQLException {
        statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE if not exists 'users'" +
                        "('login' text PRIMARY KEY, 'password' text, 'nickname' text);");
    }

    public static void writeDb() throws SQLException {
        try {
            statement.execute("INSERT INTO 'users' ('login', 'password', 'nickname') VALUES ('qwe', 'qwe', 'qwe')");
            statement.execute("INSERT INTO 'users' ('login', 'password', 'nickname') VALUES ('asd', 'asd', 'asd')");
            statement.execute("INSERT INTO 'users' ('login', 'password', 'nickname') VALUES ('zxc', 'zxc', 'zxc')");
        } catch (SQLException e) {
            System.out.println("Исключение базы данных - база данных уже заполнена ранее.");
        }

    }

    public static void closeDB() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

//    Старая реализация
//    private class UserData {
//        String login;
//        String password;
//        String nickname;
//
//        public UserData(String login, String password, String nickname) {
//            this.login = login;
//            this.password = password;
//            this.nickname = nickname;
//        }
//    }
//
//    private List<UserData> users;

    public SimpleAuthService() throws SQLException, ClassNotFoundException {
        setConnection();
        createDb();
        writeDb();
//        Старая реализация
//        users = new ArrayList<>();
//        users.add(new UserData("qwe", "qwe", "qwe"));
//        users.add(new UserData("asd", "asd", "asd"));
//        users.add(new UserData("zxc", "zxc", "zxc"));
//        for (int i = 1; i < 10; i++) {
//            users.add(new UserData("login" + i, "pass" + i, "nick" + i));
//        }
    }

    // Метод, возвращающий ник по логину и паролю
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM users " +
                "WHERE login = '" + login + "' AND password = '" + password + "'");
        String nickname = resultSet.getString("nickname");
        return nickname;

//        Старая реализация
//        for (UserData user : users) {
//            if (user.login.equals(login) && user.password.equals(password)) {
//                return user.nickname;
//            }
//        }

        //return null;
    }

    // Метод для регистрации нового пользователя
    @Override
    public boolean registration(String login, String password, String nickname) {

        try {
            statement.execute("INSERT INTO 'users' ('login', 'password', 'nickname') " +
                    "VALUES ('" + login + "', '" + password + "', '" + nickname + "')");
            return true;
        }
        catch (SQLException e) {
            return false;
        }


//        Старая реализация
//        for (UserData user : users) {
//            if (user.login.equals(login) || user.nickname.equals(nickname)) {
//                return false;
//            }
//        }
//        users.add(new UserData(login, password, nickname));
//        return true;
    }
}
