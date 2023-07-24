package autotest.clients;

import autotest.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;;
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

    @Description("Валидация полученного ответа (String)")
    public void validateResponseString(TestCaseRunner runner, String response) {
        runner.$(http().client(yellowDuckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(response));
    }

    @Description("Валидация полученного ответа (из папки Resources)")
    public void validateResponseResourcesFolder(TestCaseRunner runner, String expectedResources) {
        runner.$(http().client(yellowDuckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(new ClassPathResource(expectedResources)));
    }

    @Description("Валидация полученного ответа (из папки Payload)")
    public void validateResponsePayloadFolder(TestCaseRunner runner, String expectedPayload) {
        runner.$(http().client(yellowDuckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(new ObjectMappingPayloadBuilder(expectedPayload, new ObjectMapper())));
    }
}


