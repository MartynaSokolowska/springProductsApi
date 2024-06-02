package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class Product {
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Category")
    private String category;

    @JsonProperty("PartNumberNR")
    private String partNumberNR;

    @JsonProperty("CompanyName")
    private String companyName;

    @JsonProperty("Active")
    private boolean active;

    public Product() {
    }

    public Product(String id, String name, String category, String partNumberNR, String companyName, boolean active) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.partNumberNR = partNumberNR;
        this.companyName = companyName;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

