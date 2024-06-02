package api.services;

import api.models.Product;
import api.models.Products;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductsService {
    private Products products;

    public int parseXML(String filePath) {
        // Load and parse file from given path (filePath), returns number of products in file
        File xmlFile = new File(filePath);
        if (!xmlFile.exists()) {
            return -1;
        }
        try {
            XmlMapper xmlMapper = new XmlMapper();
            products = xmlMapper.readValue(xmlFile, Products.class);
            return products.getProductList().size();
        } catch (IOException e) {
            return -2;
        }
    }


    public List<Product> getAllProducts() {
        // Getting all products
        if (products == null || products.listIsEmpty()) return null;
        return products.getProductList();
    }


    public Product getProductByName(String name){
        // Getting product by given name
        if (products == null) return null;
        return products.getProductList().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
