package database;

import Config.H2Config;
import dto.CardDto;
import dto.History;
import org.apache.commons.lang3.StringUtils;
import utils.TimeUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Database {
    public static void insertLogin(String id){
        String sql = "INSERT INTO CARD_HISTORY_TEMP(cardId, inTime) VALUES('" + id + "','"+ TimeUtils.getTime() +"')";
        System.out.println(sql);
        H2Config.update(sql);
    }
    public static History readLoginTime() {
        String sql = "select cardId, inTime from CARD_HISTORY_TEMP";
        try (Connection connection = H2Config.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                History history = new History();
                history.setCardId(resultSet.getString(1));
                history.setInTime(resultSet.getString(2));
                return history;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void insertOut(String id, String data) {
        String sql = "INSERT INTO CARD_HISTORY(cardId, inTime, outTime) VALUES('" + id + "','"+ data +"','"+ TimeUtils.getTime() +"')";
        String delete = "DELETE FROM CARD_HISTORY_TEMP";
        H2Config.execute(sql);
        H2Config.execute(delete);
    }
    public static CardDto cardInfo(String id){
        try (Connection connection = H2Config.getConnection();Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT name, permission FROM CARD WHERE id = '"+id+"'");
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                String permission = resultSet.getString(2);
                return new CardDto(id, name, permission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static boolean cardExit(String id){
        try (Connection connection = H2Config.getConnection();Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT COUNT (*) FROM CARD WHERE id = '"+id+"'");
            if (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                if (anInt > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static void insertCard(String id, String name, String permission) {
        String sql = "INSERT INTO CARD(id, name, permission) VALUES('" + id + "','" + name + "','"+permission+"')";
        H2Config.update(sql);
    }
    public static List<String[]> queryICCardHistory(String startTime, String endTime) {

        String sql = "SELECT a.id, a.name, b.inTime, b.outTime FROM CARD a, CARD_HISTORY b WHERE a.id = b.cardId order by b.id";
        if(!StringUtils.isEmpty(startTime)){
            sql += " and inTime>= '"+startTime+"'";
        }
        if(!StringUtils.isEmpty(endTime)){
            sql += " and outTime <= '"+endTime+"'";
        }

        List<String[]> list = new LinkedList<>();

        try (Connection connection = H2Config.getConnection();Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String inTime = resultSet.getString(3);
                String outTime = resultSet.getString(4);
                LocalDateTime start = TimeUtils.toLocalDateTime(inTime);
                LocalDateTime end = TimeUtils.toLocalDateTime(outTime);
                Duration between = Duration.between(start, end);


                String [] o = new String[5];
                o[0] = id;
                o[1] = name;
                o[2] = inTime;
                o[3] = outTime;
                o[4] = String.valueOf(between.toMinutes());

                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<String[]> queryICCardHistoryTemp() {

        String sql = "SELECT a.id, a.name, b.inTime FROM CARD a, CARD_HISTORY_TEMP b WHERE a.id = b.cardId";

        List<String[]> list = new LinkedList<>();

        try (Connection connection = H2Config.getConnection();Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String inTime = resultSet.getString(3);

                String [] o = new String[5];
                o[0] = id;
                o[1] = name;
                o[2] = inTime;

                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<String[]> queryICCard() {
        String sql = "select id, name, permission from CARD";
        List<String[]> list = new LinkedList<>();
        try (Connection connection = H2Config.getConnection();Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String permission = resultSet.getString(3);
                String [] o = new String[3];
                o[0] = id;
                o[1] = name;
                o[2] = permission;
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void updateCard(String id, String name, String permission) {
        String sql = "UPDATE CARD set  name = '" + name + "',permission = '"+permission+"' where id = '"+id+"'";
        H2Config.update(sql);
    }

    public static void deleteCard(String id) {
        String sql = "DELETE CARD where id= '"+id+"'";
        H2Config.update(sql);
    }

    public static void deleteAllHistory() {
        String s = "DELETE FROM CARD_HISTORY";

        H2Config.execute(s);
    }

    public static void deletAllCard() {
        H2Config.execute("DROP TABLE CARD");
    }

    public static void main(String[] args) {
        String s = "DELETE FROM CARD_HISTORY";

        H2Config.execute(s);
        String s2 = "DELETE FROM CARD_HISTORY_TEMP";


        H2Config.execute(s2);
        H2Config.execute("DROP TABLE CARD");
    }



}
