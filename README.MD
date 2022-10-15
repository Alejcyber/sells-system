
# Webpages used to guide and tutorials

## How to run the project

You need to install Java JDK 8

## Install Java JDK

Follow the link and download the JDK

https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

or try this other link

https://files-cdn.liferay.com/mirrors/download.oracle.com/otn-pub/java/jdk/8u121-b13/

## Deploy in local environment

From a terminal go to your project folder

example: /home/your_user/Documentos/personal-proyects/SellSystem

>cd ${your_project_folder}

To runthe project execute the follow command

- In Linux
>./mvnw
- In Windows 
>mvnw

If this commands not work try this:

Download Apache Maven

https://maven.apache.org/download.cgi

and run this command

>mvn spring-boot:run

###How to use
You can use the following credentials:
- user: **user1**, password: **1**
- user: **user2**, password: **1**
- user: **user3**, password: **1**

**user1** and **user2** has **USER** role
**user3** has **ADMIN** role

![Login](/images/sells-system-login.png) 

If you log in with the user **user3** you will have access to the categories module

![Category list](/images/sells-system-admin-category-list.jpg) 

You can create or update a category

![New category](/images/sells-system-new-category.jpg) 

![Update category](/images/sells-system-update-category.jpg) 

In the **Sell** menu you will be able to see your **published products** and your **sells**

![My products](/images/sells-system-seller-my-products.png) 

![My sells](/images/sells-system-seller-my-sells.jpg) 

In the **Buy** menu you can see a menu to **buy products** and another to see the purchases made(**My shopping**)

to add to cart you just need to press the cart button

![Product list](/images/sells-system-buyer-product-list.jpg)

Then, at the top right, you will click on the cart button, this will take you to the checkout page

![Cart Button](/images/my-cart-button.jpg)

after you click the **Done** button, a redirection to **My Shopping** will be made

![Checkout](/images/sells-system-buyer-checkout.jpg)

![My shopping](/images/sells-system-buyer-my-shopping.jpg)

If you click the eye button you can see the details

![Order Details](/images/sells-system-buyer-order-detail.jpg)

### Links to documentation and guides
The next articles are used as guide

* [Build a Basic Login Form With Spring Security, Thymeleaf, and Java](https://betterprogramming.pub/spring-security-basic-login-form-7c8f6e6e9f56)
* [Spring Security — User Roles and ThymeLeaf Extras](https://betterprogramming.pub/spring-security-user-roles-and-thymeleaf-extras-c10113d4ad75)
* [Spring Security — Redirect Based on User Roles](https://betterprogramming.pub/spring-security-redirect-based-on-user-roles-cb847ab6df35)
* [Tutorial: Thymeleaf + Spring](https://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html#handling-the-command-object)
* [Spring Boot CRUD Application with Thymeleaf](https://www.baeldung.com/spring-boot-crud-thymeleaf)
* [Display Logged-in User’s Information in Thymeleaf](https://www.baeldung.com/spring-thymeleaf-user-info)
* [Display Logged-in User’s Information in Thymeleaf](https://www.baeldung.com/spring-thymeleaf-user-info)
* [Spring Security with Thymeleaf](https://www.baeldung.com/spring-security-thymeleaf)
* [Use th:replace to include HTML fragments in Thymeleaf](https://www.youtube.com/watch?v=-ptZtMsg87U)
* [Spring Boot JPA + H2 example: Build a CRUD Rest APIs](https://www.bezkoder.com/spring-boot-jpa-h2-example/)
* [Guide To Running Logic on Startup in Spring](https://www.baeldung.com/running-setup-logic-on-startup-in-spring)
* [Spring Security - Form Login with Database](https://www.tutorialspoint.com/spring_security/spring_security_form_login_with_database.htm)
* [Create a Shopping Cart Web Application with Spring Boot, Hibernate](https://o7planning.org/10683/create-a-shopping-cart-web-application-with-spring-boot-hibernate)
* [Working with Select and Option in Thymeleaf](https://www.baeldung.com/thymeleaf-select-option)
* [Javax BigDecimal Validation](https://www.baeldung.com/javax-bigdecimal-validation)
