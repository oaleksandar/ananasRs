package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyManager {
    private static String url;
    private static String driverPath;
    private static String loginEmail;
    private static String badPassword;
    private static String loginPassword;

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
        loginEmail = prop.getProperty("loginEmail");
        badPassword = prop.getProperty("badLoginPassword");
        loginPassword = prop.getProperty("loginPassword");
        return instance; 
    }
    public String getUrl(){
        return url;
    }
    public String getDriverPath(){
        return driverPath;
    }
    public String getLoginEmail(){
        return loginEmail;}
    public String getBadPassword(){
        return badPassword;}
    public String getLoginPassword(){
        return loginPassword;
    }

}
