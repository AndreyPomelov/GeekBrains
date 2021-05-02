package serialize;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Класс для тестирования БД, в работе сервера не участвует
public class DataBaseTest {

    protected static Connection connection;
    protected static Statement statement;
    protected static ResultSet resultSet;

    public static void main(String[] args) throws SQLException {
        new DataBaseHandler();
//        resultSet = statement.executeQuery("select * from users");
//        System.out.println(resultSet.getString(2));
        System.out.println(DataBaseHandler.getPassword("MyLog"));

    }
}
