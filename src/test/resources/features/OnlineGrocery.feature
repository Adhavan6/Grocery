Feature: API Testing with Rest Assured

  Scenario: Register a new user
    Given the user registration endpoint is available
    When I register a user with username "Surya", password "surya@3", and email "surya@gmail.com"
    Then the response status code should be 201
    And the response body should contain username "Surya" and email "surya@gmail.com"

  Scenario: Login a user
    Given the user login endpoint is available
    When I login with username "Aadhavan" and password "aadhavan@6"
    Then the response status code should be 200
    And the response body should contain message "Login successful"

  Scenario: Update user details
    Given the user update endpoint is available
    When I update user with id 1 with username "Aadhavanvais" and email "kaadhavan@gmail.com"
    Then the response status code should be 200
    And the response body should contain username "Aadhavanvais" and email "kaadhavan@gmail.com"

  Scenario: Delete a user
    Given the user delete endpoint is available
    When I delete user with id 4
    Then the response status code should be 204

  Scenario: Post a product
    Given the product post endpoint is available
    When I post a product with name "Soap", category "Body Care", price "20.00", and stockQuantity "100"
    Then the response status code should be 201
    And the response body should contain name "Soap" and category "Body Care"

  Scenario: Get a product by id
    Given the product get by id endpoint is available
    When I get the product with id 1
    Then the response status code should be 200
    And the response body should contain product name "White Rice"

  Scenario: Post an order
    Given the order post endpoint is available
    When I post an order with userid "1", productid "1", quantity "7", and totalPrice "300.00"
    Then the response status code should be 201
    And the response body should contain userid 1 and productid 1

  Scenario: Get all orders
    Given the orders get all endpoint is available
    When I get all orders
    Then the response status code should be 200
    And the response body should contain orders