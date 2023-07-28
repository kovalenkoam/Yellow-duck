package autotest.tests;

import autotest.clients.DuckClients;
import autotest.payloads.DefaultDuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.CitrusParameters;
import org.springframework.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckCreateParamTest extends DuckClients {

    DefaultDuckProperties duckProperties1 = new DefaultDuckProperties()
            .color("yellow")
            .height(0.1)
            .material("rubber")
            .sound("quack").
            wingsState("ACTIVE");
    DefaultDuckProperties duckProperties2 = new DefaultDuckProperties()
            .color("red")
            .height(0.2)
            .material("wood")
            .sound("quack")
            .wingsState("FIXED");
    DefaultDuckProperties duckProperties3 = new DefaultDuckProperties()
            .color("green")
            .height(0.3)
            .material("plastic")
            .sound("quack")
            .wingsState("ACTIVE");
    DefaultDuckProperties duckProperties4 = new DefaultDuckProperties()
            .color("black")
            .height(0.4)
            .material("stone")
            .sound("quack")
            .wingsState("FIXED");
    DefaultDuckProperties duckProperties5 = new DefaultDuckProperties()
            .color("pink")
            .height(0.5)
            .material("slate")
            .sound("quack")
            .wingsState("ACTIVE");

    @Test(dataProvider = "duckList", description = "Параметризированный тест создания уточки")
    @CitrusTest
    @CitrusParameters({"payload", "response", "runner"})

    public void createDuckList(Object payloads, String response, @Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner,payloads);
        validateResponseFromResourcesAndExtractId(runner, yellowDuckService, HttpStatus.OK, response);
        duckDelete(runner, "${duckId}");
    }

    @DataProvider(name = "duckList")
    public Object [][] DataProvider() {
        return new Object[][] {
                {duckProperties1, "getDuckProperties/duckYellowProperties.json", null},
                {duckProperties2, "getDuckProperties/duckRedProperties.json", null},
                {duckProperties3, "getDuckProperties/duckGreenProperties.json", null},
                {duckProperties4, "getDuckProperties/duckBlackProperties.json", null},
                {duckProperties5, "getDuckProperties/duckPinkProperties.json", null}
        };
    }
}
