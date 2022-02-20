package Config;

import utils.FileUtils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class H2Config {
    /**
     * 以嵌入式(本地)连接方式连接H2数据库
     */
    private static String DRIVER_CLASS;
    private static String JDBC_URL;

    private static String USER;
    private static String PASSWORD;

    static {
        try {
            Properties properties = FileUtils.readProperties(Config.H2_PROPERTIES);
            DRIVER_CLASS = properties.getProperty("driver-class-name");
            JDBC_URL = (String) properties.get("url");
            USER = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            Class.forName(DRIVER_CLASS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    public static void execute(String sql){
        try (Connection connection = getConnection();Statement statement = connection.createStatement()){
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        execute("CREATE TABLE CARD(id VARCHAR(10) PRIMARY KEY, name VARCHAR(100))");
//        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
//        Statement statement = conn.createStatement();
//        statement.execute("DROP TABLE IF EXISTS CARD");
//        statement.execute("CREATE TABLE CARD(id VARCHAR(10) PRIMARY KEY, name VARCHAR(100))");

//        statement.executeUpdate("INSERT INTO USER_INF VALUES(1, 'tom', '男') ");
//        statement.executeUpdate("INSERT INTO USER_INF VALUES(2, 'jack', '女') ");
//        statement.executeUpdate("INSERT INTO USER_INF VALUES(3, 'marry', '男') ");
//        statement.executeUpdate("INSERT INTO USER_INF VALUES(4, 'lucy', '男') ");
//
//        ResultSet resultSet = statement.executeQuery("select * from USER_INF");
//
//        while (resultSet.next()) {
//            System.out.println(
//                    resultSet.getInt("id") + ", " + resultSet.getString("name") + ", " + resultSet.getString("sex"));
//        }


    }
}
