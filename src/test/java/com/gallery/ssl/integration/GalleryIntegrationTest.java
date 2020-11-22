package com.gallery.ssl.integration;

import com.gallery.ssl.controller.ImageController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.mockito.Mockito.mock;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GalleryIntegrationTest {
    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private ImageController imageController;
    @Test
    public void testSaveImage() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        Response response = (Response) given().port(port)
                .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .queryParam("name", "Image name")
                .queryParam("description", "Image description")
                .param("file", file.getBytes())
                .when()
                .post("/saveImage")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}