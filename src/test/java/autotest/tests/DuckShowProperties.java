package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckShowProperties extends DuckClients {



    @Test(description = "Успешный показ проперти утки c wingsState: ACTIVE")
    @CitrusTest
    public void duckShowProperties1(@Optional@CitrusResource TestCaseRunner runner) {

        duckCreate(runner, "{ \"color\": \"yellow\", \"height\": 0.01, \"material\": \"rubber\", \"sound\": \"quack\", \"wingsState\": \"ACTIVE\" }");
        extractDataFromResponse(runner, yellowDuckService);
        duckShowProperties(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService,HttpStatus.OK,"{ \"color\": \"yellow\", \"height\": 0.01, \"material\": \"rubber\", \"sound\": \"quack\", \"wingsState\": \"ACTIVE\" }");
        duckDelete(runner,"${duckId}");
    }

    @Test(description = "Успешный показ проперти утки c wingsState: FIXED")
    @CitrusTest
    public void duckShowProperties2(@Optional@CitrusResource TestCaseRunner runner) {

        duckCreate(runner, "{ \"color\": \"yellow\", \"height\": 0.01, \"material\": \"rubber\", \"sound\": \"quack\", \"wingsState\": \"FIXED\" }");
        extractDataFromResponse(runner, yellowDuckService);
        duckShowProperties(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService,HttpStatus.OK,"{ \"color\": \"yellow\", \"height\": 0.01, \"material\": \"rubber\", \"sound\": \"quack\", \"wingsState\": \"FIXED\" }");
        duckDelete(runner,"${duckId}");
    }

    @Test(description = "Успешный показ проперти утки c wingsState: UNDEFINED")
    @CitrusTest
    public void duckShowProperties3(@Optional@CitrusResource TestCaseRunner runner) {

        duckCreate(runner, "{ \"color\": \"yellow\", \"height\": 0.01, \"material\": \"rubber\", \"sound\": \"quack\", \"wingsState\": \"UNDEFINED\" }");
        extractDataFromResponse(runner, yellowDuckService);
        duckShowProperties(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{ \"color\": \"yellow\", \"height\": 0.01, \"material\": \"rubber\", \"sound\": \"quack\", \"wingsState\": \"UNDEFINED\" }");
        duckDelete(runner, "${duckId}");
    }

    @Test(description = "Обращение к проперти утки не из rubber")
    @CitrusTest
    public void duckNoRubberShowProperties3(@Optional@CitrusResource TestCaseRunner runner) {

        duckCreate(runner, "{ \"color\": \"yellow\", \"height\": 0.01, \"material\": \"wood\", \"sound\": \"quack\", \"wingsState\": \"FIXED\" }");
        extractDataFromResponse(runner, yellowDuckService);
        duckShowProperties(runner, "${duckId}");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{ \"color\": \"yellow\", \"height\": 0.01, \"material\": \"rubber\", \"wood\": \"quack\", \"wingsState\": \"FIXED\" }");
        duckDelete(runner, "${duckId}");
    }
}
