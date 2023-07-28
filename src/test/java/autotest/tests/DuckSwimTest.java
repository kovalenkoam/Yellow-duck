package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckSwimTest extends DuckClients {
    @Test(description = "Проверка успешного плаванья уточки")
    @CitrusTest
    public void successfulSwim(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreateResources(runner, "createDuckProperties.json");
        extractDataFromResponse(runner, yellowDuckService);
        duckSwim(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{ “message”: “I’m swimming”}");
        duckDelete(runner, "${duckId}");
    }
}
