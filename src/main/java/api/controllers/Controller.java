package api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import api.services.ProductsService;
import api.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class Controller {

    @Autowired
    ProductsService productService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "File not found under given path"),
            @ApiResponse(responseCode = "409", description = "File in a wrong format. Must be XML"),
            @ApiResponse(responseCode = "200", description = "Everything is good, the request was successfully processed")
    })
    @PostMapping("/uploadAndParseXML")
    // Load and parse file from given path (filePath), returns number of products in file
    public ResponseEntity<Integer> handleXMLUploadAndParse(@RequestParam String filePath) {
        int result = productService.parseXML(filePath);
        if (result == -1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1);
        }
        if (result == -2){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(-2);
        }
        return ResponseEntity.ok(result);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "No products available"),
            @ApiResponse(responseCode = "200", description = "Everything is good, the request was successfully processed")
    })
    @GetMapping
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException {
        // Getting all products
        List<Product> productList = productService.getAllProducts();
        if (productList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products available");
        }
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(productList));
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "No product under given name"),
            @ApiResponse(responseCode = "200", description = "Everything is good, the request was successfully processed")
    })
    @GetMapping("/{name}")
    // Getting product by given name
    public ResponseEntity<String> getProductByName(@PathVariable String name) throws JsonProcessingException {
        Product product = productService.getProductByName(name);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product under given name");
        }
        return ResponseEntity.ok(new ObjectMapper().writeValueAsString(product));
    }

}

