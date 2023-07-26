package autotest.tests;

import autotest.clients.DuckClients;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckQuackTest extends DuckClients {

    @Test(description = "Нулевое кол-во повторов при запуске метода quack")
    @CitrusTest
    public void noRepeatsQuack(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "red", "23", "rubber", "meow", "FIXED");
        extractDataFromResponse(runner, yellowDuckService);
        duckQuack(runner, "${duckId}", "0", "2");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{\n" +
                "  \"sound\": \"\"\n" +
                "}");
        duckDelete(runner, "${duckId}");
    }

    @Test(description = "Нулевое кол-во повторов при запуске метода quack")
    @CitrusTest
    public void repeatsMoreQuack(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "red", "23", "rubber", "meow", "ACTIVE");
        extractDataFromResponse(runner, yellowDuckService);
        duckQuack(runner, "${duckId}", "3", "2");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{ “sound”: “quack-quack, quack-quack, quack-quack” }");
        duckDelete(runner, "${duckId}");
    }

    @Test(description = "Нулевое кол-во повторов при запуске метода quack")
    @CitrusTest
    public void quackMoreQuack(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "red", "23", "rubber", "meow", "ACTIVE");
        extractDataFromResponse(runner, yellowDuckService);
        duckQuack(runner, "${duckId}", "2", "3");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{\n" +
                "  \"sound\": \"quack-quack-quack, quack-quack-quack\"\n" +
                "}");
        duckDelete(runner, "${duckId}");
    }

    @Test(description = "Нулевое кол-во повторов при запуске метода quack")
    @CitrusTest
    public void equalsQuackAndRepeats(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "red", "23", "rubber", "meow", "ACTIVE");
        extractDataFromResponse(runner, yellowDuckService);
        duckQuack(runner, "${duckId}", "3", "3");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{ \"sound\": \"quack-quack-quack, quack-quack-quack, quack-quack-quack\" }");
        duckDelete(runner, "${duckId}");
    }

    @Test(description = "Нулевое кол-во кряков при запуске метода quack")
    @CitrusTest
    public void repeatsQuack(@Optional@CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "red", "23", "rubber", "meow", "ACTIVE");
        extractDataFromResponse(runner, yellowDuckService);
        duckQuack(runner, "${duckId}", "0", "3");
        validateResponseFromBody(runner, yellowDuckService, HttpStatus.OK, "{\n" +
                "  \"sound\": \"\"\n" +
                "}");
        duckDelete(runner, "${duckId}");
    }
}
