package RestAssd;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import Resources.Utility;
import Resources.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class GooglePostTest
{
public static Properties prp;
    @BeforeClass
     public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void googlePost() throws IOException {
        //BaseURL or Host
//        RestAssured.baseURI = prp.getProperty("HOST1");
        RestAssured.baseURI = System.getProperty("HOST");  //will be coming from jenkins through maven command
        Response res = given().log().all().
                queryParam("key",prp.getProperty("Key")).
                body(payload.googlePostPayload()).
                when().
                post("/maps/api/place/add/json").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).and().
                body("status",equalTo("OK")).
                extract().response();
        String responseString = res.asString();
        JsonPath js = new JsonPath(responseString);
        String placeId = js.getString("place_id");
        prp.setProperty("PlaceID",placeId);
        Utility.setPropertyFile(prp);
        System.out.println(placeId);

    }
}
