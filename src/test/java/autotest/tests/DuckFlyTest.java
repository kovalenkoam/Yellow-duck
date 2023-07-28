package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckFlyTest extends DuckClients{
    @Test(description = "Проверка успешного взлета уточки с WingState = ACTIVE")
    @CitrusTest
    public void successfulFly(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "red", "23", "rubber", "meow", "ACTIVE");
        extractIdFromResponse(runner, yellowDuckService);
        duckFly(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "\"message\": \"I’m flying\"" );
        duckDelete(runner, "${duckId}");
    }

    @Test(description = "Проверка успешного взлета уточки с WingState = FIXED")
    @CitrusTest
    public void successfulNotFly(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "red", "23", "rubber", "meow", "FIXED");
        extractIdFromResponse(runner, yellowDuckService);
        duckFly(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{ “message”: “I can’t fly”}" );
        duckDelete(runner, "${duckId}");
    }
}
