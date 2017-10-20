 /**
 * Created by justinrobinson on 10/19/17.
 */
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.io.IOUtils;
import org.json.*;
import com.google.gson.*;

import javax.ws.rs.*;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Path("/spottimes")
public class spotTimes {

    @GET
    @Produces
    public String exampleCurl() {
        String curlExample = "Please curl against to check data, example:" +
                "curl -d \"2015-07-01T07:00:00Z to 2015-07-01T12:00:00Z\" http://localhost:8080/spotHeroApp_war_exploded/spottimes" +
                "this should yeild a return of 1500";

        return curlExample;
    }

    @POST
    @Consumes
    public String checkTimes(String resource) throws Exception {
        //TODO:: Need to clean up unused code, and clean up function.
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");

        Calendar calStart = new GregorianCalendar();
        Calendar calEnd = new GregorianCalendar();

        String[] timeInput = resource.split("(\\sto\\s)");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(timeInput[0]);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(timeInput[1]);

        String fileName = "/rates.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        InputStream is = getClass().getResourceAsStream("/rates.json");
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject)jsonParser.parse(
                new InputStreamReader(is, "UTF-8"));
        JsonArray jsonObject2 = jsonObject.getAsJsonArray("rates");
        JsonObject jObj = jsonObject2.get(0).getAsJsonObject();
        JsonElement jEle = jObj.get("days");
        JsonElement jEle2 = jObj.get("times");
        JsonElement jEle3 = jObj.get("price");

        String[] jDays = jEle.toString().split(",");
        String[] jTimes = jEle2.toString().split("-");

        JsonObject jObj2 = jsonObject2.get(1).getAsJsonObject();
        JsonElement jEle4 = jObj2.get("days");
        JsonElement jEle5 = jObj2.get("times");
        JsonElement jEle6 = jObj2.get("price");
        String[] jDays2 = jEle4.toString().split(",");
        String[] jTimes2 = jEle5.toString().split("-");

        String startDay = simpleDateformat.format(startDate);
        String endDay = simpleDateformat.format(startDate);

        boolean startWeekday = Arrays.stream(jDays).anyMatch(startDay.toLowerCase()::equals);
        boolean startWeekend = Arrays.stream(jDays2).anyMatch(startDay.toLowerCase()::equals);

        boolean endWeekday = Arrays.stream(jDays).anyMatch(endDay.toLowerCase()::equals);
        boolean endWeekend = Arrays.stream(jDays2).anyMatch(endDay.toLowerCase()::equals);
        //TODO:: Write logic to check the true values against the time values as well
        if (startWeekday) {

        } else if (startWeekend) {

        }

        if (endWeekday) {

        } else if (endWeekend) {

        }

        return endDay;
    }
}
