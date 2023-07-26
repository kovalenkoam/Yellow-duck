package autotest.clients;

import autotest.BaseTest;
import com.consol.citrus.TestCaseRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

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

    public void duckCreate(TestCaseRunner runner, String body){
        sendPostRequest(runner, yellowDuckService, "/api/duck/create", body);
    }

    public void duckCreateResources(TestCaseRunner runner, String expectedResources){
        runner.$(http()
                .client(yellowDuckService)
                .send()
                .post("/api/duck/create")
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ClassPathResource(expectedResources)));
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

    public void duckFly(TestCaseRunner runner, String id) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/action/fly",
                "id", id);
    }

    public void duckSwim(TestCaseRunner runner, String id) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/action/swim",
                "id", id);
    }

    public void duckQuack(TestCaseRunner runner,
                          String id,
                          String repetitionCount,
                          String soundCount) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/action/quack",
                "id", id,
                "repetitionCount", repetitionCount,
                "soundCount", soundCount);
    }

    public void duckShowProperties(TestCaseRunner runner,
                                   String id) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/action/properties",
                "id", id);
    }
}