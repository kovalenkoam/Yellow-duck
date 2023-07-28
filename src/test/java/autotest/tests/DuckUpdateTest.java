package autotest.tests;

import autotest.clients.DuckClients;
import autotest.payloads.ResponseMessage;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckUpdateTest extends DuckClients {

    ResponseMessage responseMessage = new ResponseMessage().message("Duck with id = ${duckId} is updated");

    @Test(description = "Проверка изменения характеристик уточки")
    @CitrusTest

    public void successfulUpdate(Object payloads, @Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "green", "11.0", "plastic", "quack", "FIXED");
        extractIdFromResponse(runner, yellowDuckService);
        duckUpdate(runner, "${duckId}", "red", "12.0","wood", "meow", "ACTIVE");
        validateResponseFromPayload(runner, yellowDuckService, HttpStatus.OK, responseMessage );
        duckDelete(runner, "${duckId}");
    }
}
