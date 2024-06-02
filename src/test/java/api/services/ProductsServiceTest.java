package api.services;

import static org.junit.jupiter.api.Assertions.*;

import api.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.net.URL;
import java.util.List;

public class ProductsServiceTest {

    private ProductsService productsService;


    @BeforeEach
    public void setUp() {
        productsService = new ProductsService();
    }


    @Test
    public void testParseXMLFileNotFound() {
        int result = productsService.parseXML("non_existent_file.xml");
        assertEquals(-1, result, "Expected -1 when file is not found");
    }


    @Test
    public void testParseXMLIOException() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("file_with_error.xml");
            assertNotNull(resource, "File 'products.xml' not found in resources");
            String filePath = resource.getFile();
            int result = productsService.parseXML(filePath);
            assertEquals(-2, result, "Expected -2, because file with error");
        } catch (Exception e) {
            fail("Exception thrown during test: " + e.getMessage());
        }
    }


    @Test
    public void testParseXMLSuccess() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("products.xml");
            assertNotNull(resource, "File 'products.xml' not found in resources");
            String filePath = resource.getFile();
            int result = productsService.parseXML(filePath);
            assertEquals(3, result, "Expected 3 products in the XML file");
        } catch (Exception e) {
            fail("Exception thrown during test: " + e.getMessage());
        }
    }


    @Test
    void testGetAllProducts() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("products.xml");
        assertNotNull(resource, "File 'products.xml' not found in resources");
        String filePath = resource.getFile();
        productsService.parseXML(filePath);

        List<Product> retrievedProducts = productsService.getAllProducts();
        assertNotNull(retrievedProducts, "Returned products should not be null");
        assertEquals(3, retrievedProducts.size(), "Expected 3 products in the list");
    }


    @Test
    void testGetProductByName() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("products.xml");
        assertNotNull(resource, "File 'products.xml' not found in resources");
        String filePath = resource.getFile();
        productsService.parseXML(filePath);

        Product retrievedProduct = productsService.getProductByName("apple");
        assertNotNull(retrievedProduct, "Returned product should not be null");
        assertEquals("apple", retrievedProduct.getName(), "Expected product name to be 'Apple'");
    }


    @Test
    void testGetAllProductsNull(){
        List<Product> retrievedProducts = productsService.getAllProducts();
        assertNull(retrievedProducts, "Returned products should be null");
    }


    @Test
    void testGetProductByNameNull() {
        Product retrievedProduct = productsService.getProductByName("apple");
        assertNull(retrievedProduct, "Returned product should be null");
    }

}

