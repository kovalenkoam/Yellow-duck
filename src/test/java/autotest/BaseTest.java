package autotest;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class BaseTest extends TestNGCitrusSpringSupport {

    @Autowired
    protected HttpClient yellowDuckService;

    protected void sendGetRequest(TestCaseRunner runner,
                                  HttpClient URL,
                                  String path) {
        runner.$(http()
                .client(URL)
                .send()
                .get(path));
    }

    protected void sendGetRequest(TestCaseRunner runner,
                                  HttpClient URL,
                                  String path,
                                  String nameQueryParam, String valueQueryParam ) {
        runner.$(http()
                .client(URL)
                .send()
                .get(path)
                .queryParam(nameQueryParam, valueQueryParam));
    }

    protected void sendGetRequest(TestCaseRunner runner,
                                               HttpClient URL,
                                               String path,
                                               String nameQueryParam, String valueQueryParam,
                                               String nameQueryParam1, String valueQueryParam1,
                                               String nameQueryParam2, String valueQueryParam2) {
        runner.$(http()
                .client(URL)
                .send()
                .get(path)
                .queryParam(nameQueryParam, valueQueryParam)
                .queryParam(nameQueryParam1, valueQueryParam1)
                .queryParam(nameQueryParam2, valueQueryParam2));
    }

    protected void sendPostRequest(TestCaseRunner runner,
                                   HttpClient URL,
                                   String path,
                                   Object body) {
        runner.$(http()
                .client(URL)
                .send()
                .post(path)
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ObjectMappingPayloadBuilder(body, new ObjectMapper())));
    }

    protected void sendPostRequest(TestCaseRunner runner,
                                   HttpClient URL,
                                   String path,
                                   String body) {
        runner.$(http()
                .client(URL)
                .send()
                .post(path)
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body));
    }

    protected void sendDeleteRequest(TestCaseRunner runner,
                                  HttpClient URL,
                                  String path,
                                  String nameQueryParam, String valueQueryParam ) {
        runner.$(http().
                client(URL)
                .send()
                .delete(path)
                .queryParam(nameQueryParam, valueQueryParam));
    }

    protected void sendPutRequest(TestCaseRunner runner,
                                  HttpClient URL,
                                  String path,
                                  String nameQueryParam, String valueQueryParam,
                                  String nameQueryParam1, String valueQueryParam1,
                                  String nameQueryParam2, String valueQueryParam2,
                                  String nameQueryParam3, String valueQueryParam3,
                                  String nameQueryParam4, String valueQueryParam4,
                                  String nameQueryParam5, String valueQueryParam5) {
        runner.$(http()
                .client(URL)
                .send()
                .put(path)
                .queryParam(nameQueryParam, valueQueryParam)
                .queryParam(nameQueryParam2, valueQueryParam2)
                .queryParam(nameQueryParam1, valueQueryParam1)
                .queryParam(nameQueryParam3, valueQueryParam3)
                .queryParam(nameQueryParam4, valueQueryParam4)
                .queryParam(nameQueryParam5, valueQueryParam5));
    }

    protected void validateResponse1 (TestCaseRunner runner,
                                     HttpClient URL,
                                     HttpStatus status,
                                     String body) {
        runner.$(http()
                .client(URL)
                .receive()
                .response(status)
                .message().type(MessageType.JSON)
                .body(body));
    }

    protected void validateResponse2 (TestCaseRunner runner,
                                     HttpClient URL,
                                     HttpStatus status,
                                     String expectedResources) {
        runner.$(http()
                .client(URL)
                .receive()
                .response(status)
                .message().type(MessageType.JSON)
                .body(new ClassPathResource(expectedResources)));
    }

    protected void validateResponse3 (TestCaseRunner runner,
                                     HttpClient URL,
                                     HttpStatus status,
                                     String expectedPayload) {
        runner.$(http()
                .client(URL)
                .receive()
                .response(status)
                .message().type(MessageType.JSON)
                .body(new ObjectMappingPayloadBuilder(expectedPayload, new ObjectMapper())));
    }
}
