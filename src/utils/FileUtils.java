package utils;


import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author hll
 * @create 2021-09-29 14:59
 **/
public class FileUtils {
    private static final ClassLoader CLASS_LOADER = FileUtils.class.getClassLoader();
    public static final String FOLDER_SEPARATOR = File.separatorChar + "";
    /**
     * 获取文件名去除后缀
     * @param file 文件名
     * @return 去后缀的文件名
     */
    public static String getFileName(File file) {
        return file.isDirectory()?file.getName():file.getName().substring(0, file.getName().lastIndexOf("."));
    }

    /**
     * 获取文件名去除后缀
     * @param file 文件名
     * @return  去后缀的文件名
     */
    public static String getFileName(String file) {
        return getFileName(new File(file));
    }

    public static InputStream loadFile(String filePath ){
        File file = new File(loadResource(filePath).getPath());
        if(file.exists()){
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return CLASS_LOADER.getResourceAsStream(filePath);
    }
    public static URL loadResource(String filePath){
        return CLASS_LOADER.getResource(filePath);
    }
    /**
     * 写入文件
     * @param path 位置
     * @param content 内容
     */
    public static void write(String path,String content, Charset cs)  {
        Path filePath = Paths.get(path);
        if(!Files.exists(filePath)){
            try{
                Files.createFile(filePath);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(path), cs)){
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void write(String path,String content)  {
        write(path, content, StandardCharsets.UTF_8);
    }
    public static String read(String file){
        StringBuffer sb = new StringBuffer();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));){
            sb.append(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static  Map<String, String> parse(InputStream inputStream) throws IOException {
        return parse(new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)));
    }
    public static  Map<String, String> parse(BufferedReader in) throws IOException {
        Map<String, String> map = new HashMap<>();
        String s;
        while ((s = in.readLine()) != null){
            String[] split = s.split(":");
            if(split.length != 2){
                continue;
            }
            map.put(split[0], split[1]);
        }
        return map;
    }


    /**
     * 获取文件路径中除了文件名称外的基础路径部分
     @method StringUtil: BasePath()
     @memo TODO
     @param path
     @param separator
     @return String
     */
    static public String BasePath(String path, String separator) {
        if(null==path) return path;
        separator = null==separator ? FOLDER_SEPARATOR : separator;
        int index = path.lastIndexOf(separator);
        return path.substring(0,0==index?0:index+1);
    }
    static public String BasePath(String path) {
        return BasePath(path,null);
    }
    public static Properties readProperties(String file) throws IOException {
        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        properties.load(bufferedReader);
        return properties;
    }
}
