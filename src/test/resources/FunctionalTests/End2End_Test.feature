Feature: Login Action
  @regressionM1 @TestRails(C223421)
  Scenario: B2B Successful Login with Valid Credentials
    Given B2B User is on login Page
    And B2B User enters "liennth7@unifiedpost.com" and "0983791128a@"
    And B2B User hit login button
    Then B2B Verify user login successfully

#    Given Fintek User is on login Page
#    And Fintek User select language
#    And Fintek User select country then hit Next button
#    And Fintek User select method login "Username"
#    And Fintexk User enters "Qa_admin" and "Testing1!"
#    Then Fintek Verify user login successfully

#  Scenario: Successful Login with Valid Credentials
#    Given User is on login Page
#    And User enters "liennth7@unifiedpost.com" and "0983791128a@"
#    And User hit login button
#    And User clicks Library tap
#    And User clicks API name
#    Then Verify user login successfully

#  Scenario: B2B Successful Login with Valid Credentials
#    Given B2B User is on login Page
#    And B2B User enters "liennth7@unifiedpost.com" and "0983791128a@"
#    And B2B User hit login button
#    Then B2B Verify user login successfully

#  Scenario: Successful Login with Valid Credentials
#    Given User enters "liennth7@unifiedpost.com" and "0983791128a@"
#    And User clicks Library tab
#    And User choose the API to view
#    And User clicks API name
#    Then Library Detail page displays
#    Then Verify the order details

#  Scenario: Successful Login with Valid Credentials
#    Given User enters Credentials to LogIn
#      | liennth7@unifiedpost.com          | P@ssword1|
#      | liennth7+client12@unifiedpost.com | P@ssword1|
#    And User clicks Library tab
#    Then Library page displays
#
#  Scenario Outline: Successful Login with Valid Credentials
#    Given User enters enters "<username>" and "<password>"
#    And User clicks Library tab
#    Then Library page displays
#
#    Examples:
#      | username                          | password |
#      | liennth7@unifiedpost.com          | P@ssword1|
#      | liennth7+client12@unifiedpost.com | P@ssword1|