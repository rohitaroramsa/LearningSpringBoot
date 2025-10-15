Feature: Products can be viewed & added

  Scenario: Product can be added
    Given baseurl and endpoints
    When we add a product
    Then we get success
    And we can view all products
    And we can delete a product