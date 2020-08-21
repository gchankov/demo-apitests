Feature: Posts Management

  Scenario: Read all posts
    Given that the following posts are present in the system
      | id | title  |
      | 1  | Post 1 |
      | 2  | Post 2 |
      | 3  | Post 3 |
    When the user requests to read all posts
    Then OK response containing the following posts should be returned to the user
      | id | title  |
      | 1  | Post 1 |
      | 2  | Post 2 |
      | 3  | Post 3 |