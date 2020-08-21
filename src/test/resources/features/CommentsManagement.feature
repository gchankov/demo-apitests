Feature: Comments Management

  Scenario: Read all comments
    Given that the following comments are present in the system
      | id |         body | postId |
      |  1 | some comment |      1 |
      |  2 | some comment |      1 |
    When the user requests to read all comments
    Then OK response containing the following comments should be returned to the user
      | id |         body | postId |
      |  1 | Some Comment |      1 |
      |  2 | Some Comment |      1 |