package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckDbTest extends DuckClients {
    @Test(description = "Проверка успешного плаванья уточки с созданием уточки через БД")
    @CitrusTest
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId", "777");
        defaultDuckCreate(runner);
        duckSwim(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{ “message”: “I’m swimming”}");
        duckDelete(runner);
    }

    @Test (description = "Проверка создания уточки и валидации через БД")
    @CitrusTest
    public void successfulCreate(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "green", "10.0", "wood", "muamua", "ACTIVE");
        extractIdFromResponse(runner, yellowDuckService);
        validateDuckInDatabase(runner, "${duckId}", "green", "10.0", "wood", "muamua", "ACTIVE");
        duckDelete(runner);
    }

    @Test(description = "Проверка метода, когда заранее созданных уточек нет с предварительным удалением через БД")
    @CitrusTest
    public void notFoundGetAllIds(@Optional @CitrusResource TestCaseRunner runner) {
        deleteDatabase(runner);
        duckGetAllIds(runner);
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "[]");
    }

    @Test(description = "Проверка метода, когда заранее созданных уточек нет с предварительным удалением через БД")
    @CitrusTest
    public void successfulUpdate(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "green", "11.0", "plastic", "quack", "FIXED");
        extractIdFromResponse(runner, yellowDuckService);
        duckUpdate(runner, "${duckId}", "red", "12.0", "wood", "meow", "ACTIVE");
        validateDuckInDatabase(runner, "${duckId}", "red", "12.0", "wood", "meow", "ACTIVE");
        duckDelete(runner, "${duckId}");
    }
}
