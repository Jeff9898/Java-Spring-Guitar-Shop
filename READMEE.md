Part C: Customize the HTML user interface for your customer’s application. The user interface should include   
the shop name, the product names, and the names of the parts.

I customized the mainscreen.html file on line 14 and line 19 to display  
the name of the shop "Jeff's Guitar Shop".
```html
mainscreen.html 
line 14: <title>Jeff's Guitar Shop</title>

line 19: <h1>Jeff's Guitar Shop</h1>
```


 Part D: Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation 
 to and from the “About” page and the main screen.
 
created about.html  
```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>About Us</title>
</head>
<body>

  <h1>About Jeff's Guitar Shop</h1>
  <p>Welcome to Jeff's Guitar Shop! We're a specialized guitar retailer dedicated to providing our customers with
    the best guitar products. With over 15 years of experience, we help each customer find their dream guitar!</p>

  <a href="http://localhost:8080/">Back to Main Screen</a>
</body>
</html>


Added navigation from mainscreen.html to about.html
In mainscreen.html -
line 89: <a th:href="@{about}">About us</a>


Added Mapping for the about.html page
In mainscreencontrollerr-
line 57 - 60:     @RequestMapping("/about")
public String about() {
return "about";
}
```

Part E: Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five 
products in your sample inventory and should not overwrite existing data in the database.

Added inventory to BootStrapData only when lists are empty
```html
line 76 - 169
       if (partRepository.count() == 0) {

            InhousePart strap = new InhousePart();
            strap.setName("Shoulder Strap");
            strap.setPrice(19.99);
            strap.setInv(26);


            InhousePart plate = new InhousePart();
            plate.setName("Back Plate");
            plate.setPrice(39.99);
            plate.setInv(9);


            InhousePart guard = new InhousePart();
            guard.setName("Pickguard");
            guard.setPrice(49.99);
            guard.setInv(18);


            InhousePart strings = new InhousePart();
            strings.setName("Guitar Strings");
            strings.setPrice(59.99);
            strings.setInv(7);


            InhousePart bridge = new InhousePart();
            bridge.setName("Guitar Bridge");
            bridge.setPrice(42.99);
            bridge.setInv(10);


            partRepository.save(strap);
            partRepository.save(plate);
            partRepository.save(guard);
            partRepository.save(strings);
            partRepository.save(bridge);
    }

        if (outsourcedPartRepository.count() == 0) {

            OutsourcedPart knobs = new OutsourcedPart();
            knobs.setName("Guitar Knobs");
            knobs.setPrice(16.99);
            knobs.setInv(7);
            knobs.setCompanyName("Guitars-R-Us");

            OutsourcedPart capo = new OutsourcedPart();
            capo.setName("Capo");
            capo.setPrice(9.99);
            capo.setInv(9);
            capo.setCompanyName("Guitar Land");

            OutsourcedPart head = new OutsourcedPart();
            head.setName("Guitar Head");
            head.setPrice(45.99);
            head.setInv(13);
            head.setCompanyName("Guitar Center");

            OutsourcedPart neck = new OutsourcedPart();
            neck.setName("Guitar Neck");
            neck.setPrice(22.99);
            neck.setInv(8);
            neck.setCompanyName("Guitar Shack");

            OutsourcedPart springs = new OutsourcedPart();
            springs.setName("Guitar Springs");
            springs.setPrice(19.99);
            springs.setInv(11);
            springs.setCompanyName("Guitar World");

            outsourcedPartRepository.save(knobs);
            outsourcedPartRepository.save(capo);
            outsourcedPartRepository.save(head);
            outsourcedPartRepository.save(neck);
            outsourcedPartRepository.save(springs);

        }

            if (productRepository.count() == 0) {

                Product electricGuitar = new Product("Electric Guitar", 199.99, 28);
                Product acousticGuitar = new Product("Acoustic Guitar", 159.99, 18);
                Product miniGuitar = new Product("Mini Guitar", 99.99, 8);
                Product bassGuitar = new Product("Bass Guitar", 149.99, 5);
                Product jazzGuitar = new Product("Jazz Guitar", 179.99, 7);

                productRepository.save(electricGuitar);
                productRepository.save(acousticGuitar);
                productRepository.save(miniGuitar);
                productRepository.save(bassGuitar);
                productRepository.save(jazzGuitar);

            }
```
Part F:  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

```html
Added to "buy now" button to mainscreen.html line 85
<a th:href="@{/buyProduct(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3">Buy Now</a>

Created BuyProductController to check if the selected item is instock or not. They will then  
be redirected to a purchase success or failure page.
package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class BuyProductController {
@Autowired
private ProductRepository productRepository;

@GetMapping("/buyProduct")
public String buyProduct(@RequestParam("productID") Long theId, Model theModel) {
Optional<Product> productToBuy = productRepository.findById(theId);

    if (productToBuy.isPresent()) {    //check if product in catalog
    Product product = productToBuy.get();

    if (product.getInv() > 0) {    //check if product still in stock
    product.setInv(product.getInv() - 1);   //decrement stock
    productRepository.save(product);    //save to product database

    return "/confirmbuysuccess";   //successful purchase
    } else {
    return "/confirmbuyfailure";   //purchase failed: out of stock
    }
    } else {
    return "/confirmbuyfailure";  //purchase failed: product not found
    }
    }
    }
```
created the purchase success html document "confirmbuysuccess.html"
```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Purchase Success</title>
    </head>
    <body>
    <h1>Congratulations, your purchase was successful!</h1>
    <a href="http://localhost:8080/">Link
        to Main Screen</a>
    </body>
    </html>
```
Created the purchase failure html document "confirmbuyfailure.html"
```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Purchase Failed</title>
    </head>
    <body>
    <h1>There was a problem with your purchase. Please contact customer support.</h1>
    <a href="http://localhost:8080/">Link
        to Main Screen</a>
    </body>
    </html>
```

Part G: Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

Changed Part.java to track maximum and minimum inventory
```html
    @Min (value = 0, message = "Minimum inventory must be > 0")
    int minimum;
    int maximum;


Added to the constructors
this.minimum = 0;
this.maximum = 100;


public void setMinimum(int minimum) { this.minimum = minimum; }
public int getMinimum() { return this.minimum; }

public void setMaximum(int maximum) { this.maximum = maximum; }
public int getMaximum() { return this.maximum; }
```
```html
inhousepart.java

public InhousePart() {
this.minimum = 0;
this.maximum = 100;
}


outsourcepart.java

public OutsourcedPart() {
this.minimum = 0;
this.maximum = 100;
}
```
```html
mainscreen.html
<th>Minimum</th>
<th>Maximum</th>

<td th:text="${tempPart.minimum}">1</td>
<td th:text="${tempPart.maximum}">1</td>
```

```html
inhousepartform.html and outsourcepartform.html
lines 24 and 25
<p><input type="text" path="minimum" th:field="*{minimum}" placeholder="Minimum Inventory" class="form-control mb-4 col-4"/></p>
<p><input type="text" path="maximum" th:field="*{maximum}" placeholder="Maximum Inventory" class="form-control mb-4 col-4"/></p>
```

I renamed the file persistent storage is saved to jdbc:h2:file:~/spring-boot-h2-h2-db102


Part H: Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.

```html
part.java
line 93 - 100
public boolean isInvValid(){
if (inv >= minimum && inv <= maximum){
return true;
}
else {
return false;
}
}
```
```html
addoutsourcedpartcontroller and addinhousepartcontroller
lines 41-49
        if (!part.isInvValid()) {
            if(part.getInv() < part.getMinimum()) {
                bindingResult.rejectValue("inv", "error.outsourcedpart", "Inventory value is below the minimum");
            }
            else{
                bindingResult.rejectValue("inv", "error.outsourcedpart", "Inventory value is above the maximum");
            }
        }
```

```html
enufpartsvalidator
lines 35-44
for (Part p : myProduct.getParts()) {
if (p.getInv() - 1 < p.getMinimum()) {
return false;
}
}
return true;
}
else{
return true;
}
```

Part I: Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.
```html
partTest.java
lines 160 - 176
@Test
public void testMinimumInventory() {
int min_inv=1;
partIn.setInv(min_inv);
assertEquals(min_inv,partIn.getInv());
partOut.setInv(min_inv);
assertEquals(min_inv,partOut.getInv());
}

@Test
public void testMaximumInventory() {
int max_inv=101;
partIn.setInv(max_inv);
assertEquals(max_inv,partIn.getInv());
partOut.setInv(max_inv);
assertEquals(max_inv,partOut.getInv());
}
```