package autotest.clients;

import autotest.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import com.consol.citrus.http.client.HttpClient;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckActionClients extends TestNGCitrusSpringSupport {

    @Autowired
    private HttpClient yellowDuckService;

    public void duckFly(TestCaseRunner runner, String id) {
        runner.$(http()
                .client(yellowDuckService)
                .send()
                .get("/api/duck/action/fly")
                .queryParam("id", id));
    }

    public void duckSwim(TestCaseRunner runner, String id) {
        runner.$(http()
                .client(yellowDuckService)
                .send()
                .get("/api/duck/action/swim")
                .queryParam("id", id));
    }

    public void duckQuack(TestCaseRunner runner,
                          String id,
                          String repetitionCount,
                          String soundCount) {
        runner.$(http()
                .client(yellowDuckService)
                .send()
                .get("/api/duck/action/swim")
                .queryParam("id", id)
                .queryParam("repetitionCount", repetitionCount)
                .queryParam("soundCount", soundCount));
    }

    public void duckShowProperties(TestCaseRunner runner,
                                   String id) {
        runner.$(http()
                .client(yellowDuckService)
                .send()
                .get("/api/duck/action/swim")
                .queryParam("id", id));
    }
}


