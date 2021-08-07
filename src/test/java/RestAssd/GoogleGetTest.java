package RestAssd;

import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GoogleGetTest {

    public static Properties prp;

    @BeforeClass
    public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void getGoogleAPI(){
        RestAssured.baseURI = prp.getProperty("HOST1");
        Response res = given().log().all().
                queryParam("key",prp.getProperty("Key")).
                queryParam("place_id",prp.getProperty("PlaceID")).
        when().
                get("/maps/api/place/get/json").
        then().
                assertThat().statusCode(200).extract().response();

        String responseString = res.asString();
        JsonPath jp = new JsonPath(responseString);
        Assert.assertEquals(jp.getString("name"),"Frontline housing");

    }
}
