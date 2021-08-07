package Resources;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Properties;

public class Utility {

    //Properties file configuration
    public static Properties getPropertyFile() throws IOException
    {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Resources/data.properties");
        prop.load(fis);
        return prop;
    }
    public static Properties setPropertyFile(Properties prop) throws IOException
    {
        FileOutputStream f = new FileOutputStream(System.getProperty("user.dir")+"/src/main/java/Resources/data.properties");
        prop.store(f,"Updated successfully");
        return prop;
    }


    public static org.json.simple.JSONObject getJSONObject(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+path));
        return (org.json.simple.JSONObject) obj;

    }
}
