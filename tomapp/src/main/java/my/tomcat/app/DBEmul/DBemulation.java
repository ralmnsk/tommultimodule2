package my.tomcat.app.DBEmul;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBemulation {
    private String password;
    private String login;
    private String role;

    public String getRole() {
        role=getProperty("role");
        return role;
    }

    public String getPassword() {
        password=getProperty("password");
        return password;
    }

    private String getProperty(String prop){
        FileInputStream fis=null;
        try {
            Properties properties=new Properties();
            fis=new FileInputStream("tomapp/src/main/resources/login.properties");
            properties.load(fis);
            String value=properties.getProperty(prop);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String getLogin() {
        login=getProperty("login");
        return login;
    }

}
