Feature: feature to test login
  
  @PCCWAutoExam
  Scenario: Validate Positive LogIn test
    Given browser is open and launch the url provided
    When user log-in his credentials, username and password
    And user click submit button
    Then user is redirected to new page
 

  @PCCWAutoExam
  Scenario: Validate Negative username test
    Given browser is open and launch the url provided
    When user log-in his credentials, incorrect username and correct password
    And user click submit button
    Then the system displays username error message
    
  @PCCWAutoExam
  Scenario: Validate Negative password test
    Given browser is open and launch the url provided
    When user log-in his credentials, username and incorrect password
    And user click submit button
    Then the system displays password error message
       