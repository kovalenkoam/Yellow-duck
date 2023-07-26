package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;

public class DuckGetAllIdsTest extends DuckClients {

    @org.testng.annotations.Test(description = "Проверка метода, когда заранее созданных уточек нет")
    @CitrusTest
    public void notFoundGetAllIds(@Optional @CitrusResource TestCaseRunner runner) {
        duckGetAllIds(runner);
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "[]");
    }

    @org.testng.annotations.Test(description = "Проверка метода, когда создана одна уточка")
    @CitrusTest
    public void successfulGetAllIds(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreateResources(runner, "createDuckProperties.json");
        extractDataFromResponse(runner, yellowDuckService);
        duckGetAllIds(runner);
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "[\n" +
                "    ${duckId}\n" +
                "]");
        duckDelete(runner, "${duckId}");
    }
}
