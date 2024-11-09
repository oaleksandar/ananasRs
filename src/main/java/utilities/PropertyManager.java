package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertyManager {
    private static String url;
    private static String kljucnaRec;
    private static String driverPath;
    private static String configPath = "src/main/resources/configuration.properties";
    private static PropertyManager instance;

    public static PropertyManager getInstance() {
        if (instance == null){
            instance = new PropertyManager();
        instance.loadData();
        }
        return instance;
    }

    private void loadData(){

        Properties prop = new Properties();
        try {
            FileInputStream fi = new FileInputStream(configPath);
            prop.load(fi);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        url = prop.getProperty("url");
        kljucnaRec = prop.getProperty("kljucnaRec");
        driverPath = prop.getProperty("driverPath");
    }

    public static void changeProperty(String key, String value){
        Properties editProperties = new Properties();
        try {
            FileInputStream editfi = new FileInputStream(configPath);
            editProperties.load(editfi);
            editProperties.setProperty(key, value);
            editProperties.store(new FileOutputStream(configPath),null);
        }catch (Exception e){
            e.printStackTrace();
        }
        instance = null;
    }

    public String getUrl(){
        return url;
    }
    public String getKljucnaRec(){
        return kljucnaRec;}
    public String getDriverPath(){
        return driverPath;
    }
}
