@happypath
Feature: create_gists

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

  Scenario:1 Create gist through API and verify the response

    Given The user set the parameter with below combinations description
      | API Testing Examples | description |
    And  The user set the parameter with below combinations of object public
      | true | public |
    And  The user set the parameter with below combinations for creating gist files
      | RestApifileA.txt | Lets do shopping from picnic       |
      | RestApifileB.txt | Lets update apiary for api changes |
      | RestApifileC.txt | Lets update swager for api changes |
    When The user send a post request
    Then The response status code should be 201
    And  The response fields should match with below objects
      | public                            | true                               |
      | description                       | API Testing Examples               |
      | files["RestApifileC.txt"].content | Lets update swager for api changes |
      | files["RestApifileB.txt"].content | Lets update apiary for api changes |
      | files["RestApifileA.txt"].content | Lets do shopping from picnic       |
    And The response body should match the JSON schema response objects create_gist_schema.json


  Scenario:2 Create gist through API and verify the response with public key to false

    Given The user set the parameter with below combinations description
      | API Testing Examples | description |
    And  The user set the parameter with below combinations of object public
      | false | public |
    And  The user set the parameter with below combinations for creating gist files
      | RestApifileA.txt | Lets do shopping from picnic       |
      | RestApifileB.txt | Lets update apiary for api changes |
      | RestApifileC.txt | Lets update swager for api changes |
    When The user send a post request
    Then The response status code should be 201
    And  The response fields should match with below objects
      | public                            | false                              |
      | description                       | API Testing Examples               |
      | files["RestApifileC.txt"].content | Lets update swager for api changes |
      | files["RestApifileB.txt"].content | Lets update apiary for api changes |
      | files["RestApifileA.txt"].content | Lets do shopping from picnic       |
    And The response body should match the JSON schema response objects create_gist_schema.json


  Scenario:3 Create gist through API and verify the response with description to empty value

    Given The user set the parameter with below combinations description
      |  | description |
    And  The user set the parameter with below combinations of object public
      | false | public |
    And  The user set the parameter with below combinations for creating gist files
      | RestApifileA.txt | Lets do shopping from picnic       |
      | RestApifileB.txt | Lets update apiary for api changes |
      | RestApifileC.txt | Lets update swager for api changes |
    When The user send a post request
    Then The response status code should be 201
    And  The response fields should match with below objects
      | public                            | false                              |
      | description                       |                                    |
      | files["RestApifileC.txt"].content | Lets update swager for api changes |
      | files["RestApifileB.txt"].content | Lets update apiary for api changes |
      | files["RestApifileA.txt"].content | Lets do shopping from picnic       |
    And The response body should match the JSON schema response objects create_gist_schema.json


  Scenario:4 Create gist through API and verify the response with file empty name but with content

    Given The user set the parameter with below combinations description
      | test | description |
    And  The user set the parameter with below combinations of object public
      | true | public |
    And  The user set the parameter with below combinations for creating gist files
      |  | test the conent without file1 name |
    When The user send a post request
    Then The response status code should be 201
    And  The response fields should match with below objects
      | public                          | true          |
      | description                     | test          |
      | files["gistfile1.txt"].filename | gistfile1.txt |


  Scenario:5 Create gist through API and verify the response with one file name empty
  and second having name and with its content

    Given The user set the parameter with below combinations description
      | test | description |
    And  The user set the parameter with below combinations of object public
      | true | public |
    And  The user set the parameter with below combinations for creating gist files
      |           | test the conent without file1 name |
      | testfileB | test the conent without file2 name |
    When The user send a post request
    Then The response status code should be 201
    And  The response fields should match with below objects
      | public                      | true      |
      | description                 | test      |
      | files["testfileB"].filename | testfileB |


