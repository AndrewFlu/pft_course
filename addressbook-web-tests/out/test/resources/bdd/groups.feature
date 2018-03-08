Feature: Groups
  Scenario Outline: Group creation
    Given a set of groups
    When I add a new group with name <name>, header <header> and footer <footer>
    Then the new set of groups is equal to old set with added group


    Examples:

    | name      | header       | footer      |
    | testName  | testHeader   | testFooter  |
