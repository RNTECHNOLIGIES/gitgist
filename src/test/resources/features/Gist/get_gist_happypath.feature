@happypath
Feature: get_a_gist_happypath

  In order to build service Get a gist,
  As a developer should be a able to build a get gist service and Should be able to provide below feature:
  1)Allows you to retrieve gists files using gist_id.

  Note: Don't name your files "gistfile" with a numerical suffix.
  This is the format of the automatic naming scheme that Gist uses internally.

  get /gists/{gist_id}

  Parameters :
  Setting to application/vnd.github.v3+json is recommended.
  gist_id 	string 	path

  Details : https://docs.github.com/en/rest/reference/gists#get-a-gist

  Background: Header setting and prerequiste steps for Get gist
    Given Gistget Request Header authentication
    And   The user send a get request to fetch different gist_id

  Scenario:1 Get gists call through API and verify the response

    Given The user send a call get gist service request with fetched gist_id
    And   The user set the gistId and base path gists/{id}
    When  The Get gist service response code should be 200
    Then  The response fields should match with below get gist file objects
      | owner.login           | ZINGYRAJEEV |
      | owner.id              | 39244132    |
      | history[0].user.login | ZINGYRAJEEV |


