"# BookStore-tisting" 



### **BookStore Automation Test Suite**

This repository contains an automated test suite for the **BookStore website** using **Selenium WebDriver** with **Java**. The tests cover multiple functionalities of the website to ensure that it works as expected. The suite includes tests for:

1. **Login functionality** - Automates logging in with valid credentials.
2. **List/Grid toggle** - Tests the ability to switch between list and grid view of products.
3. **Add to Cart** - Verifies adding products to the cart.
4. **Checkout** - Tests the checkout process and ensures the order status page is displayed.
5. **Product Details** - Verifies clicking on product details and ensuring correct redirection.
6. **Search functionality** - Verifies searching for random characters using the search bar.
7. **Logout** - Ensures that clicking the logout button redirects to the login page.

The suite is written using **TestNG** and includes **Soft Assertions** for more flexible result reporting.

---

### **Test Execution Details:**
- The tests use **ChromeDriver** to run in Google Chrome.
- The test cases simulate real user interactions such as logging in, adding items to the cart, checking out, and more.
- The suite uses random values for actions like selecting products and typing in the search bar to cover multiple test scenarios dynamically.

### **Prerequisites:**
- Selenium WebDriver
- TestNG
- ChromeDriver

Make sure to configure your **Selenium WebDriver** and **ChromeDriver** to run the tests.

---

### **How to Run:**
1. Clone the repository.
2. Set up your **Selenium** environment.
3. Run the tests using your preferred IDE or build tool (e.g., Maven, Gradle).

---

