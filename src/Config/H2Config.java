package Config;

import utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class H2Config {
    private static String JDBC_URL;

    private static String USER;
    private static String PASSWORD;

    static {
        try {
            Properties properties = FileUtils.readProperties(Config.H2_PROPERTIES);

            String DRIVER_CLASS = properties.getProperty("driver-class-name");
            File file = new File("");
            JDBC_URL = "jdbc:h2:file:"+file.getAbsolutePath()+"/H2/db";
//            JDBC_URL = properties.getProperty("url");
            USER = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            Class.forName(DRIVER_CLASS);
            execute("CREATE TABLE IF NOT EXISTS CARD(id VARCHAR(20) PRIMARY KEY, name VARCHAR(100), permission VARCHAR(1))");
            execute("CREATE TABLE IF NOT EXISTS CARD_HISTORY(id INTEGER auto_increment,cardId VARCHAR(20), inTime DATETIME, outTime DATETIME," +
                    " PRIMARY KEY (id)" +
                    ")");
            execute("CREATE TABLE IF NOT EXISTS CARD_HISTORY_TEMP(cardId VARCHAR(20), inTime DATETIME)");
        } catch (IOException | ClassNotFoundException e) {
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
    public static boolean update(String sql){
        try (Connection connection = getConnection();Statement statement = connection.createStatement()){
            boolean execute = statement.execute(sql);
            connection.commit();
            return execute;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void executeQueryCARD(){
        try (Connection connection = getConnection();Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from CARD");
            while (resultSet.next()) {
                List<String> list = new LinkedList<>();
                try {
                    list.add(resultSet.getString(1));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
