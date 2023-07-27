package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.CitrusParameters;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckUpdateTest extends DuckClients {
    @Test(description = "Проверка изменения характеристик уточки")
    @CitrusTest

    public void successfulUpdate(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "green", "11.0", "plastic", "quack", "FIXED");
        extractDataFromResponse(runner, yellowDuckService);
        duckUpdate(runner, "${duckId}", "red", "12.0","wood", "meow", "ACTIVE");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{\n" +
                "  \"message\": \"Duck with id = ${duckId} is updated\"\n" +
                "}");
        duckDelete(runner, "${duckId}");
    }
}
