package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckCreateTest extends DuckClients {
    @Test(description = "Успешное создание уточки")
    @CitrusTest
    public void successfulCreate(@Optional @CitrusResource TestCaseRunner runner){
        duckCreate(runner, "green", "10.0", "wood", "muamua", "ACTIVE");
        validateResponseFromResources(runner, yellowDuckService, HttpStatus.OK, "getDuckProperties/duckGreenProperties2.json");
        duckDelete(runner, "$duckId");
    }

    @Test(description = "Cоздание уточки с пустым телом")
    @CitrusTest
    public void successfulDefaultCreate(@Optional @CitrusResource TestCaseRunner runner){
        duckCreate(runner, "{}");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{\n" +
                "    \"id\": \"@variable('duckId')@\",\n" +
                "    \"color\": \"\",\n" +
                "    \"height\": 0.0,\n" +
                "    \"material\": \"\",\n" +
                "    \"sound\": \"\",\n" +
                "    \"wingsState\": \"UNDEFINED\"\n" +
                "}");
        duckDelete(runner, "${duckId}");
    }
}
