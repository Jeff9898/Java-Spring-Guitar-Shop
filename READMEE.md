Part C: I customized the mainscreen.html file on line 14 and line 19 to display  
the name of the shop "Jeff's Guitar Shop".
```html
mainscreen.html 
line 14: <title>Jeff's Guitar Shop</title>

line 19: <h1>Jeff's Guitar Shop</h1>
```


 Part D: created about.html  
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
