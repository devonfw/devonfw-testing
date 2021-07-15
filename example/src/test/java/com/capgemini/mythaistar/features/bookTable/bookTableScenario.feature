@BookTableScenario
Feature: My Thai Star book table page

  Background:
    Given I am on the book table page
    And I click on Date and time field on the book a table form

  Scenario Outline: Test book a table form with different data
    When I set <date> date and <hour> hour and <minutes> minutes on the calendar popup
    And I complete action on the calendar popup
    And I set name field with <name> value on the book a table form
    And I set email field with <email> value on the book a table form
    And I set table guests field with <guests> value on the book a table form
    And I check accept terms checkbox on the book a table form
    When I click book table button on the book a table form
    Then I should see <status> of reservation on the book a table form

    Examples:
      | date    | hour    | minutes | name  | email                | guests  | status  |
      | current | valid   | valid   | User1 | mythaitest@gmail.com | valid   | success |
      | future  | valid   | valid   | User1 | mythaitest@gmail.com | valid   | success |
      | current | invalid | valid   | User1 | mythaitest@gmail.com | valid   | failed  |
      | current | valid   | invalid | User1 | mythaitest@gmail.com | valid   | success |
      | current | valid   | valid   | User1 | mythaitest@gmail.com | invalid | failed  |
      | current | valid   | valid   | User1 | mythaitest@gmail.com | double  | success |
      | current | valid   | valid   | User1 | mythaitest@gmail.com | valid   | success |

  @BookTableFormTestEmptyName
  Scenario: Test book a table form with empty name
    When I set actual date and time on the calendar popup
    And I complete action on the calendar popup
    And I set email field with 'mythaitest@gmail.com' value on the book a table form
    And I set table guests field with '5' value on the book a table form
    When I check accept terms checkbox on the book a table form
    Then I can not click book table button on the book a table form
    But I should see the underlined name field as required on the book a table form

  Scenario: Test book a table form with invalid email
    When I set actual date and time on the calendar popup
    And I complete action on the calendar popup
    And I set name field with 'User1' value on the book a table form
    And I set email field with 'test@' value on the book a table form
    And I check accept terms checkbox on the book a table form
    Then I can not click book table button on the book a table form
    But I should see the underlined email field as valid on the book a table form

  Scenario: Test book a table form without Accept terms
    When I set actual date and time on the calendar popup
    And I complete action on the calendar popup
    And I set name field with 'NewUser' value on the book a table form
    And I set email field with 'mythaitest@gmail.com' value on the book a table form
    And I set table guests field with '5' value on the book a table form
    Then I should see book table button as invisible on the book a table form

