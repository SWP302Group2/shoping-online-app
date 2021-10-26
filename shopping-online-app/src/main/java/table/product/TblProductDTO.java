/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.product;

/**
 *
 * @author hai
 */
public class TblProductDTO {
    private String id;
    private String name;
    private String unit;
    private String description;
    private float price;
    private String image;
    private int quantity;

    public TblProductDTO() {
    }

    public TblProductDTO(String id, String name, String unit, String description, float price, String image, int quantity) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
   
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

   
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
