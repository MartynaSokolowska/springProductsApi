package api.controllers;

import api.models.Product;
import api.services.ProductsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ControllerTest {

    @Mock
    private ProductsService productService;

    private Controller controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new Controller();
        controller.productService = productService;
    }

    @Test
    public void testHandleXMLUploadAndParse_FileNotFound() {
        String filePath = "example.xml";
        when(productService.parseXML(filePath)).thenReturn(-1);
        ResponseEntity<Integer> responseEntity = controller.handleXMLUploadAndParse(filePath);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(-1, responseEntity.getBody());
    }

    @Test
    public void testHandleXMLUploadAndParse_Conflict() {
        String filePath = "example.xml";
        when(productService.parseXML(filePath)).thenReturn(-2);
        ResponseEntity<Integer> responseEntity = controller.handleXMLUploadAndParse(filePath);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals(-2, responseEntity.getBody());
    }

    @Test
    public void testHandleXMLUploadAndParse_Success() {
        String filePath = "example.xml";
        when(productService.parseXML(filePath)).thenReturn(10);
        ResponseEntity<Integer> responseEntity = controller.handleXMLUploadAndParse(filePath);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(10, responseEntity.getBody());
    }


    @Test
    public void testGetAllProducts_NoProductsAvailable() throws Exception {
        when(productService.getAllProducts()).thenReturn(null);
        ResponseEntity<String> responseEntity = controller.getAllProducts();
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No products available", responseEntity.getBody());
    }

    @Test
    public void testGetAllProducts_Success() throws JsonProcessingException {
        Product product1 = new Product("1", "Test Product", "Category", "PartNumber", "Company", true);
        Product product2 = new Product("2", "Test Product2", "Category2", "PartNumber2", "Company2", true);
        List <Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        when(productService.getAllProducts()).thenReturn(list);
        ResponseEntity<String> response = controller.getAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("[{\"id\":\"1\",\"Name\":\"Test Product\",\"Category\":\"Category\",\"PartNumberNR\":\"PartNumber\",\"CompanyName\":\"Company\",\"Active\":true},{\"id\":\"2\",\"Name\":\"Test Product2\",\"Category\":\"Category2\",\"PartNumberNR\":\"PartNumber2\",\"CompanyName\":\"Company2\",\"Active\":true}]", response.getBody());
    }


    @Test
    public void testGetProductByName_NoProductAvailable() throws Exception {
        String productName = "Product1";
        when(productService.getProductByName(productName)).thenReturn(null);
        ResponseEntity<String> responseEntity = controller.getProductByName(productName);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No product under given name", responseEntity.getBody());
    }

    @Test
    public void testGetProductByName_Success() throws JsonProcessingException {
        Product product = new Product("1", "Test Product", "Category", "PartNumber", "Company", true);
        when(productService.getProductByName("Test Product")).thenReturn(product);
        ResponseEntity<String> response = controller.getProductByName("Test Product");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"id\":\"1\",\"Name\":\"Test Product\",\"Category\":\"Category\",\"PartNumberNR\":\"PartNumber\",\"CompanyName\":\"Company\",\"Active\":true}", response.getBody());
    }

    @Test
    public void testGetProductByName_EmptyName() throws JsonProcessingException {
        when(productService.getProductByName("")).thenReturn(null);
        ResponseEntity<String> response = controller.getProductByName("");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No product under given name", response.getBody());
    }

}
