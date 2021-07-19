@unhappypath
Feature: create_gists_unhappypath

  In order to Create_Gists,
  As a developer build a Create gist service,
  Should be able to provide below feature:
  1)Allows you to add a new gist with one or more files.

  Note: Don't name your files "gistfile" with a numerical suffix.
  This is the format of the automatic naming scheme that Gist uses internally.

  POST /gists

  Parameters :
  description
  files
  public

  Details : https://docs.github.com/en/rest/reference/gists#create-a-gist

  Background: Header setting and prerequiste steps
    Given Header authentication
    And   set the basepath /gists

  Scenario:1 Create gist through API and verify the response with empty description and empty public object value
    Given The user set the parameter with below combinations description
      |  | description |
    And  The user set the parameter with below combinations of object public
      |  | public |
    And  The user set the parameter with below combinations for creating gist files
      | RestApifile1.txt | Lets do shopping from picnic       |
      | RestApifile2.txt | Lets update apiary for api changes |
      | RestApifile3.txt | Lets update swager for api changes |
    When The user send a post request
    Then The response status code should be 422
    And  The response fields should match with below objects
      | message           | Invalid request.\n\nNo subschema in "anyOf" matched.\nFor 'anyOf/0', "" is not a boolean.\n is not a member of ["true", "false"].\nFor 'anyOf/2', "" is not a null. |
      | documentation_url | https://docs.github.com/rest/reference/gists#create-a-gist                                                                                                          |

  Scenario:2 Create gist through API and verify the response with empty description and empty public object   value and empty file content
    Given The user set the parameter with below combinations description
      |  | description |
    And  The user set the parameter with below combinations of object public
      |  | public |
    And  The user set the parameter with below combinations for creating gist files
      | RestApifile1.txt |  |
      | RestApifile2.txt |  |
      | RestApifile3.txt |  |
    When The user send a post request
    Then The response status code should be 422
    And  The response fields should match with below objects
      | message           | Invalid request.\n\nNo subschema in "anyOf" matched.\nFor 'anyOf/0', "" is not a boolean.\n is not a member of ["true", "false"].\nFor 'anyOf/2', "" is not a null. |
      | documentation_url | https://docs.github.com/rest/reference/gists#create-a-gist                                                                                                          |

  Scenario:3 Create gist through API and verify the response with file but empty content
    Given The user set the parameter with below combinations description
      | test | description |
    And  The user set the parameter with below combinations of object public
      | true | public |
    And  The user set the parameter with below combinations for creating gist files
      | RestApifile1.txt |  |
    When The user send a post request
    Then The response status code should be 422
    And  The response fields should match with below objects
      | message           | Validation Failed                               |
      | errors[0].code    | missing_field                                   |
      | errors[0].field   | files                                           |
      | documentation_url | https://docs.github.com/v3/gists/#create-a-gist |

  Scenario:4 Create gist through API and verify the response with empty file name but with content
    Given The user set the parameter with below combinations description
      | test | description |
    And  The user set the parameter with below combinations of object public
      | true | public |
    And  The user set the parameter with below combinations for creating gist files
      | RestApifile1.txt |  |
    When The user send a post request
    Then The response status code should be 422
    And  The response fields should match with below objects
      | message           | Validation Failed                               |
      | errors[0].code    | missing_field                                   |
      | errors[0].field   | files                                           |
      | documentation_url | https://docs.github.com/v3/gists/#create-a-gist |

  Scenario:4 Create gist through API and verify the response with without authorization token
    Given The user call for service with no authorization
    And   set the basepath /gists
    And The user set the parameter with below combinations description
      | test | description |
    And  The user set the parameter with below combinations of object public
      | true | public |
    And  The user set the parameter with below combinations for creating gist files
      | RestApifile1.txt | test |
    When The user send a post request
    Then The response status code should be 401


