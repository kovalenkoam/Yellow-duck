package autotest.clients;

import autotest.BaseTest;
import com.consol.citrus.TestCaseRunner;

public class DuckActionClients extends BaseTest {

    public void duckFly(TestCaseRunner runner, String id) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/action/fly",
                "id", id);
    }

    public void duckSwim(TestCaseRunner runner, String id) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/action/swim",
                "id", id);
    }

    public void duckQuack(TestCaseRunner runner,
                          String id,
                          String repetitionCount,
                          String soundCount) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/action/quack",
                "id", id,
                "repetitionCount", repetitionCount,
                "soundCount", soundCount);
    }

    public void duckShowProperties(TestCaseRunner runner,
                                   String id) {
        sendGetRequest(runner, yellowDuckService,
                "/api/duck/action/properties",
                "id", id);
    }
}


