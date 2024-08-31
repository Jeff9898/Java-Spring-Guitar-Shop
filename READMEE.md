WGU D287 Java Frameworks PA - Jeff Starr

NOTE: The current code in this project was all done on the branch "working-version2". The other branches are from a previous attempt until I decided to restart the project due to bugs/errors.
I prefer to keep the code in case I want to look back at it.

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



Added navigation from mainscreen.html to about.html. Link is at the bottom of the mainscreen.
In mainscreen.html -
line 94: <a th:href="@{about}">About us</a>



Added Mapping for the about.html page
In mainscreencontrollerr-
line 57 - 60:     @RequestMapping("/about")
public String about() {
return "about";
}
```

Part E: Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five 
products in your sample inventory and should not overwrite existing data in the database.


```html
Added inventory to BootStrapData only when lists are empty. Five parts and five products were added. 
line 76 - 169
if (partRepository.count() == 0) {

InhousePart strap = new InhousePart();
strap.setId(1);
strap.setName("Shoulder Strap");
strap.setPrice(19.99);
strap.setInv(26);
strap.setMinimum(1);
strap.setMaximum(50);


InhousePart strings = new InhousePart();
strings.setId(2);
strings.setName("Guitar Strings");
strings.setPrice(59.99);
strings.setInv(7);
strings.setMinimum(1);
strings.setMaximum(65);


InhousePart bridge = new InhousePart();
bridge.setId(3);
bridge.setName("Guitar Bridge");
bridge.setPrice(42.99);
bridge.setInv(10);
bridge.setMinimum(1);
bridge.setMaximum(40);

partRepository.save(strap);
partRepository.save(strings);
partRepository.save(bridge);
}

if (outsourcedPartRepository.count() == 0) {

OutsourcedPart knobs = new OutsourcedPart();
knobs.setId(4);
knobs.setName("Guitar Knobs");
knobs.setPrice(16.99);
knobs.setInv(7);
knobs.setCompanyName("Guitars-R-Us");
knobs.setMinimum(1);
knobs.setMaximum(80);

OutsourcedPart capo = new OutsourcedPart();
capo.setId(5);
capo.setName("Capo");
capo.setPrice(9.99);
capo.setInv(9);
capo.setCompanyName("Guitar Land");
capo.setMinimum(1);
capo.setMaximum(25);

outsourcedPartRepository.save(knobs);
outsourcedPartRepository.save(capo);


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
Added to "buy now" button to mainscreen.html line 89
<a th:href="@{/buyProduct(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3">Buy Now</a>

Created BuyProductController to check if the selected item is instock or not. They will then  
be redirected to a purchase success or failure page.

@Controller
public class BuyProductController {
@Autowired
private ProductRepository productRepository;

@GetMapping("/buyProduct")
public String buyProduct(@RequestParam("productID") Long theId, Model theModel) {
Optional<Product> productToBuy = productRepository.findById(theId);

 if (productToBuy.isPresent()) {    
 Product product = productToBuy.get();

 if (product.getInv() > 0) {    
 product.setInv(product.getInv() - 1);   
 productRepository.save(product);    

 return "/confirmbuysuccess";   
 } else {
 return "/confirmbuyfailure";   
 }
 } else {
 return "/confirmbuyfailure";  
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

```html
Changed Part.java to track maximum and minimum inventory
lines 32-34
    @Min (value = 0, message = "Minimum inventory must be > 0")
    int minimum;
    int maximum;


Added to the constructors- lines 48/49 and lines 57/58
this.minimum = 0;
this.maximum = 100;

Addition of getters and setters - lines 110-114

public void setMinimum(int minimum) { this.minimum = minimum; }
public int getMinimum() { return this.minimum; }

public void setMaximum(int maximum) { this.maximum = maximum; }
public int getMaximum() { return this.maximum; }
```
```html
inhousepart.java - lines 18/19

public InhousePart() {
this.minimum = 0;
this.maximum = 100;
}


outsourcepart.java - lines 18/19

public OutsourcedPart() {
this.minimum = 0;
this.maximum = 100;
}
```
```html
mainscreen.html
lines 38 and 39
<th>Minimum</th>
<th>Maximum</th>

lines 48 and 49
<td th:text="${tempPart.minimum}">1</td>
<td th:text="${tempPart.maximum}">1</td>
```

```html
I added text inputs so the user can input a minimum or maximum amount of parts
inhousepartform.html and outsourcepartform.html
lines 24 and 25
<p><input type="text" path="minimum" th:field="*{minimum}" placeholder="Minimum Inventory" class="form-control mb-4 col-4"/></p>
<p><input type="text" path="maximum" th:field="*{maximum}" placeholder="Maximum Inventory" class="form-control mb-4 col-4"/></p>
```
Application Properties - line 6
I renamed the file persistent storage is saved to spring.datasource.url=jdbc:h2:file:~/Jeff's-Guitars-h2-db102

```html
part.java
Logic that will be used to enforce that the inventory is between the minimum and maximum amount
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


Part H: Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.


```html
addoutsourcedpartcontroller and addinhousepartcontroller
Validation that the inhouse and outsourced parts are between the minimum and maximum inventory amount
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
Check if the inventory is less than the minimum
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
Two unit tests are located at the bottom of the PartTest class. Both tests passed.
Test > com.example.demo > domain > PartTest
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

Part J: Remove the class files for any unused validators in order to clean your code.

I deleted the DeletePartValidator as intelliJ informed there were no usages for it.