package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckDeleteTest extends DuckClients {

    @Test(description = "Успешное удаление уточки")
    @CitrusTest
    public void successfulDelete(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreateResources(runner, "createDuckProperties.json");
        extractDataFromResponse(runner, yellowDuckService);
        duckDelete(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{\n" +
                "  \"message\": \"Duck is deleted\"\n" +
                "}");
    }

    @Test(description = "Успешное удаление уточки")
    @CitrusTest
    public void notFoundIdDelete(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreateResources(runner, "createDuckProperties.json");
        //id несуществующей уточки
        duckDelete(runner, "99999");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{ \n" +
                "\"message\": \"Duck with id = 99999 is not found\" \n" +
                "}");
    }
}
