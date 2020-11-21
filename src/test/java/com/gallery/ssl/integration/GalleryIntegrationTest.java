package com.gallery.ssl.integration;

import com.gallery.ssl.SslApplication;
import com.gallery.ssl.controller.ImageController;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.mock;


@SpringBootTest(classes = SslApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("local")
public class GalleryIntegrationTest {

    @Autowired
    private ImageController imageController;
    @Test
    public void testSaveImage() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        Response response = (Response) given()
                .contentType("multipart/form-data" )
                .queryParam("name", "Image name")
                .queryParam("description", "Image description")
                .param("file", file.getBytes())
                .when()
                .post("/saveImage")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}