package RestAssd;

import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

/**
 * Unit test for simple App.
 */
public class GoogleUpdateTest
{
public static Properties prp;
    @BeforeClass
     public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void googleUpdate() throws IOException, ParseException {
        //BaseURL or Host
        org.json.simple.JSONObject payload  = Utility.getJSONObject("/src/main/java/RestAssd/update.json");
        payload.put("place_id",prp.getProperty("PlaceID"));
//        RestAssured.baseURI = prp.getProperty("HOST1");
        RestAssured.baseURI = System.getProperty("HOST"); //will be coming from jenkins through maven command
        Response res = given().log().all().
                queryParam("key",prp.getProperty("Key")).
                queryParam("place_id",prp.getProperty("PlaceID")).
                body(payload).
                when().
                put("/maps/api/place/update/json").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).
                extract().response();
        String responseString = res.asString();
        JsonPath jp = new JsonPath(responseString);
        Assert.assertTrue(jp.getString("msg").contains("Address successfully updated"));
    }
}
