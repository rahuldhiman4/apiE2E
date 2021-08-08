package RestAssd;

import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
public class GoogleDeleteTest
{
public static Properties prp;
    private String pId=null;

    public GoogleDeleteTest(String pId){
        this.pId = pId;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData(){
        Collection<Object[]> params = new ArrayList<>(2);
        params.add(new Object[]{"xyz"});
        params.add(new Object[]{"def"});
        return params;

    }

    @BeforeClass
     public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void googleDelete()  {
        //BaseURL or Host
//        RestAssured.baseURI = prp.getProperty("HOST1");
        RestAssured.baseURI = System.getProperty("HOST"); //will be coming from jenkins through maven command
        Map<String,String> map = new HashMap<>();
        map.put("place_id",pId);   //parametarization acheived through junit
        Response res = given().log().all().
                queryParam("key",prp.getProperty("Key")).
                body(map).
                when().
                delete("/maps/api/place/delete/json").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).
                body("status",equalTo("OK")).
                extract().response();
        System.out.println(map+" got deleted successfully");
    }
}
