package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyManager {
    private static String url;
    private static String driverPath;

    public static PropertyManager getInstance(){

        PropertyManager instance = new PropertyManager();
        Properties prop = new Properties();

        try {
            FileInputStream fi = new FileInputStream("src/main/resources/configuration.properties");
            prop.load(fi);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        url = prop.getProperty("url");
        driverPath = prop.getProperty("driverPath");
        return instance; 
    }
    public String getUrl(){
        return url;
    }
    public String getDriverPath(){
        return driverPath;
    }

}
