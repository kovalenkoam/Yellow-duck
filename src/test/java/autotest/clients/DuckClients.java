package autotest.clients;

import autotest.BaseTest;
import com.consol.citrus.TestCaseRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;

import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.actions.ExecuteSQLQueryAction.Builder.query;
import static com.consol.citrus.container.FinallySequence.Builder.doFinally;

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
        sendPostRequestFromResources(runner, yellowDuckService, "/api/duck/create", expectedResources );
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

    public void duckDelete(TestCaseRunner runner) {
        runner.$(sql(db)
                .statement("DELETE FROM DUCK WHERE ID=${duckId}"));
    }

    public void defaultDuckCreate(TestCaseRunner runner) {
        runner.$(sql(db)
                .statement("INSERT INTO DUCK VALUES (${duckId}, 'green', 10.0, 'wood', 'gav','ACTIVE')"));
    }

    public void validateDuckInDatabase(TestCaseRunner runner, String id,
                                       String color,
                                       String height,
                                       String material,
                                       String sound,
                                       String wingsState) {
        runner.$(query(db)
                .statement("SELECT * FROM DUCK WHERE ID=" + id)
                .validate("COLOR", color)
                .validate("HEIGHT", height)
                .validate("MATERIAL", material)
                .validate("SOUND", sound)
                .validate("WINGS_STATE", wingsState));
    }

    public void deleteDatabase(TestCaseRunner runner) {
        runner.$(sql(db)
                .statement("DELETE FROM DUCK"));
}
}