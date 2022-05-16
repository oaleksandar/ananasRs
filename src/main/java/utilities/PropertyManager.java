package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertyManager {
    private static String url;
    private static String driverPath;
    private static String loginEmail;
    private static String badPassword;
    private static String loginPassword;
    private static String configPath = "src/main/resources/configuration.properties";
    private static PropertyManager instance;
    private static String regEmail;
    private static String firstName;
    private static String lastName;
    private static String password;
    private static String address;
    private static String city;
    private static String postCode;
    private static String mobPhone;

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
        driverPath = prop.getProperty("driverPath");
        loginEmail = prop.getProperty("loginEmail");
        badPassword = prop.getProperty("badLoginPassword");
        loginPassword = prop.getProperty("loginPassword");
        regEmail = prop.getProperty("regEmail");
        firstName = prop.getProperty("firstName");
        lastName= prop.getProperty("lastName");
        password= prop.getProperty("password");
        address= prop.getProperty("address");
        city = prop.getProperty("city");
        postCode = prop.getProperty("postCode");
        mobPhone = prop.getProperty("mobPhone");

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

    public String getRegEmail() {
        return regEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public static String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getMobPhone() {
        return mobPhone;
    }
}
