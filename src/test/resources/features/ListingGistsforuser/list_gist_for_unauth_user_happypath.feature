@unhappypath
Feature: List_gists_for_the_unauthenticated_user_happypath

  In order to List_gists_for_the_unauthenticated_user,
  As a developer build a List_gists_for_the_unauthenticated_user,
  Should be able to provide below feature:

  1)Lists public gists for the specified user:

  get /users/{username}/gists

  Parameters :
  Setting to application/vnd.github.v3+json is recommended.
  username 	string 	path
  since 	string 	query
  per_page 	integer 	query
  page 	integer 	query

  Page number of the results to fetch.
  Default: 1

  Details : https://docs.github.com/en/rest/reference/gists#list-gists-for-a-user

  Background: Header setting and prerequiste steps for list gist
    Given GistList for unauth user Request Header authentication


  Scenario Outline:1 List gists for unauth user call through API and verify the response
    Given   The user set the list gist unauth user and request the service /users/<userid>/gists
    When  The List gist user unauth service response code should be 200
    Then  The List gist unauth user response fields should match with below objects
      | owner.login[0] | ZINGYRAJEEV |
      | owner.id[0]    | 39244132    |

    Examples:
      | userid      |
      | zingyrajeev |


  Scenario Outline:2 List gists for unauth user call through API and verify the response
    Given The user set the query parameter <since_param>with<since_date> and <perpage_param>with<per_page> and <page_param>with<page>
    Given The user set the list gist unauth user and request the service /users/<userid>/gists
    When  The List gist user unauth service response code should be 200
    Then  The List gist unauth user response fields should match with below objects
      | owner.login[0] | ZINGYRAJEEV |
      | owner.id[0]    | 39244132    |
      | x.size()       | <per_page>  |

    Examples:
      | userid      | since_param | since_date           | perpage_param | per_page | page_param | page |
      | zingyrajeev | since       | 2021-01-01T12:12:12Z | per_page      | 1        | page       | 1    |
      | zingyrajeev | since       | 2021-01-01T12:12:12Z | per_page      | 5        | page       | 1    |
      | zingyrajeev | since       | 2021-01-01T12:12:12Z | per_page      | 31       | page       | 1    |

