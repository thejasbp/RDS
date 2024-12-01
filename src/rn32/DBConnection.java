package rn32;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static Properties loadproperties()
    {
    Properties prop=new Properties();
        try(FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\java2prac\\src\\db.properties"))
        {
            if(fis!=null)
            {
                System.out.println("The FIle is found");
            }
            prop.load(fis);
        } catch (IOException e)
        {
            System.out.println("The file is not found"+e.getMessage());
        }
        return prop;

    }

    public static Connection getConnection()
    {
        Properties prop=loadproperties();
        try
        {
            String URL=prop.getProperty("url");
            String USER=prop.getProperty("user");
            String PASSWD=prop.getProperty("password");
            Connection con= DriverManager.getConnection(URL,USER,PASSWD);
            if(con!=null)
            {
                System.out.println("Database Connected Successfully");
            }
            return con;
        }
        catch (SQLException e)
        {
            System.out.println("The Database is not Connected "+e.getMessage());
        }
        return null;
    }
}
