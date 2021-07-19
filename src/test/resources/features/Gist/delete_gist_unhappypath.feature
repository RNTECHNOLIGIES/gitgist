@unhappypath
Feature: deleting_a_gist_unhappypath

  In order to delete a gist,
  As a developer build a delete gist service,
  Should be able to provide below feature:
  1)Allows you to delete a gists files using gist_id.

  Note: Don't name your files "gistfile" with a numerical suffix.
  This is the format of the automatic naming scheme that Gist uses internally.

  delete /gists/{gist_id}

  Parameters :
  Setting to application/vnd.github.v3+json is recommended.
  gist_id 	string 	path

  Details : https://docs.github.com/en/rest/reference/gists#delete-a-gist

  Background: Header setting and prerequiste steps for Get gist
    Given The user send a request to fetch different gist_id

  Scenario:1 Get gists call through API and verify the response with invalid gist_id
    Given    Gistdelete Request Header authentication
    And      The user set the gistId and basepath for delete  gists/XXX
    Then    The Get gist delete service response code should be 404
    And     The response fields should match with below delete gist file objects
      | message           | Not Found                                                  |
      | documentation_url | https://docs.github.com/rest/reference/gists#delete-a-gist |


  Scenario:2 Get gists call through API and verify the response with invalid gist_id

    Given   Gistdelete Request Header authentication
    And     The user set the gistId and basepath for delete  gists/123
    Then    The Get gist delete service response code should be 404
    And     The response fields should match with below delete gist file objects
      | message           | Not Found                                                  |
      | documentation_url | https://docs.github.com/rest/reference/gists#delete-a-gist |


  Scenario:3 Get gists call through API and verify the response without auth header

    Given  The user call for  delete gist service with no authorization
    And    The user send a call delete gist service request with fetched gist_id
    When   The user set the gistId and basepath for delete  gists/{id}
    Then   The Get gist delete service response code should be 401
    And    The response fields should match with below delete gist file objects
      | message           | Not Found                                                  |
      | documentation_url | https://docs.github.com/rest/reference/gists#delete-a-gist |





