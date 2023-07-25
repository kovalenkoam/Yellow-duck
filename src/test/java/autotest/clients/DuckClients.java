package autotest.clients;

import autotest.BaseTest;
import com.consol.citrus.TestCaseRunner;
import org.springframework.http.HttpStatus;

public class DuckClients extends BaseTest {

    public void duckCreate(TestCaseRunner runner,
                           String color,
                           String height,
                           String material,
                           String sound,
                           String wingsState) {
        sendPostRequest(runner, yellowDuckService, "/api/duck/create", "{\n" +
                "  \"color\": \"" + color + "\",\n" +
                "  \"height\": " + height + ",\n" +
                "  \"material\": \"" + material + "\",\n" +
                "  \"sound\": \"" + sound + "\",\n" +
                "  \"wingsState\": \"" + wingsState + "\"\n" +
                "}");
    }

    public void duckCreate(TestCaseRunner runner, Object response){
        sendPostRequest(runner, yellowDuckService, "/api/duck/create", response);
    }

    public void duckDelete(TestCaseRunner runner,
                           String id) {
        sendDeleteRequest(runner, yellowDuckService,
                "/api/duck/delete",
                "id", id);
    }

    public void duckGetAllIds(TestCaseRunner runner) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/getAllIds" );
    }

    public void duckUpdate(TestCaseRunner runner,
                           String id,
                           String color,
                           String height,
                           String material,
                           String sound,
                           String wingsState) {
        sendPutRequest(runner, yellowDuckService,
                "/api/duck/update",
                "id", id,
                "color", color,
                "height", height,
                "material", material,
                "sound", sound,
                "wingsState", wingsState);
    }

    public void validateResponse(TestCaseRunner runner, String response) {
        validateResponse2(runner, yellowDuckService, HttpStatus.OK, response);
    }
}