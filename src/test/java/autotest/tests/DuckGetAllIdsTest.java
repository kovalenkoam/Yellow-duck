package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckGetAllIdsTest extends DuckClients {

    @Test(description = "Проверка метода, когда заранее созданных уточек нет")
    @CitrusTest
    public void notFoundGetAllIds(@Optional @CitrusResource TestCaseRunner runner) {
        duckGetAllIds(runner);
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "[]");
    }

    @Test(description = "Проверка метода, когда созданна одна уточка")
    @CitrusTest
    public void successfulGetAllIds(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreateResources(runner, "createDuckProperties.json");
        extractIdFromResponse(runner, yellowDuckService);
        duckGetAllIds(runner);
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "[\n" +
                "    ${duckId}\n" +
                "]");
        duckDelete(runner, "${duckId}");
    }
}
