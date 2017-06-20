package smit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;
import org.springframework.web.util.UriTemplateHandler;
import smit.entity.Book;
import smit.repository.BookRepository;
import smit.service.BookService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

/**
 * Created by Admin on 20/06/2017.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = smit.config.DatabaseConfig.class)
@Transactional
public class BookIntegrationTest {

    @Autowired
    private BookService _service;

    @Autowired
    private BookRepository _repository;

    @Autowired
    private RestTemplate _template;

    private MockRestServiceServer _mockMVC;

    @Before
    public void setup(){
        this._mockMVC = MockRestServiceServer.createServer(_template);
    }

    @Test
    public void TestBookService(){
        String url = "http://localhost:3333/service/healthcheck";

        _mockMVC.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .body("healthy")
                        .contentType(MediaType.TEXT_PLAIN)
                );
        String result = _template.getForObject(url, String.class);
        _mockMVC.verify();
        assertThat(result.equalsIgnoreCase("healthy"));

    }

    @Test
    public void TestBookRepository() {
        Book new_book = this._service.save(new Book("Harry Potter", "available"));
        Book book = this._repository.findOne(new_book.getId());
        assertThat(book.getName()).isEqualTo("Harry Potter");
        assertThat(book.getStatus()).isEqualTo("available");
    }
}

