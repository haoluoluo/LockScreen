package utils;


import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author luoluo.hao
 * @create 2022-02-21 11:47
 **/
public class TimeUtils {

    private static final String FORMAT_YYYY = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_IS = "https://time.is";

    /**
     * 中国科学院国家授时中心
     */
    private static final String NTSC = "http://www.ntsc.ac.cn";

    public static String getTime(){
        String networkTime;
        try {
            networkTime = getNetworkTime(TIME_IS);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                networkTime = getNetworkTime(NTSC);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                networkTime = getLocalTime();
            }
        }
        return networkTime;
    }

    public static String getLocalTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_YYYY);
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public static String getNetworkTime(String webUrl) throws IOException {

        LocalDateTime dateTime;

        URL url = new URL(webUrl);


        URLConnection conn = url.openConnection();

        conn.connect();

        long dateL = conn.getDate();

        dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateL), TimeZone
                    .getDefault().toZoneId());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_YYYY);

        return dateTimeFormatter.format(dateTime);
    }
    public static LocalDateTime toLocalDateTime(String dateTime){
        return toLocalDateTime(dateTime, null);
    }
    public static LocalDateTime toLocalDateTime(String dateTime, String format) {
        if (Objects.isNull(dateTime) || Objects.equals(dateTime, "")) {
            return null;
        }
        if (Objects.isNull(format) || Objects.equals(format, "")) {
            format = FORMAT_YYYY;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTime,df);
    }
}
