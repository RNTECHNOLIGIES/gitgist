@happypath
Feature: list_gists_for_the_authenticated_user_happypath

  In order to List_gists_for_the_authenticated_user,
  As a developer build a List_gists_for_the_authenticated_user,
  Should be able to provide below feature:

  1)Lists the authenticated user's gists or if called anonymously,
  this endpoint returns all public gist

  get /gists

  Parameters :
  Setting to application/vnd.github.v3+json is recommended.
  since
  per_page
  page

  Details : https://docs.github.com/en/rest/reference/gists#list-gists-for-the-authenticated-user

  Background: Header setting and prerequiste steps for list gist
    Given GistList Request Header authentication
    And   GistList set the basepath /gists

  Scenario:1 List gists call through API and verify the response

    Given The user send a call to List gist service request
    When  The List gist service response code should be 200
    Then  The response fields should match with below List gist file objects
      | owner.login[0] | ZINGYRAJEEV |
      | owner.id[0]    | 39244132    |

  Scenario:2 List gists call through API and verify the response with different parameter combination

    Given The user send a call to List gist service request with different parameter
      | per_page | 1 |
      | page     | 1 |
    And   The user send a call to List gist service request
    When  The List gist service response code should be 200
    Then  The response fields should match with below List gist file objects
      | owner.login[0]   | ZINGYRAJEEV |
      | owner.id[0]      | 39244132    |
      | [0].files.size() | 2           |

  Scenario Outline:3 List gists call through API and verify the response with different parameter combination

    Given The user send a call to List gist service request with <per_page_param> page parameter<per_pageno>
    And   The user send a call to List gist service request with <page_param> perpage parameter<page_no>
    And   The user send a call to List gist service request
    When  The List gist service response code should be 200
    Then  The response fields should match with below List gist file objects
      | owner.login[0] | ZINGYRAJEEV  |
      | owner.id[0]    | 39244132     |
      | x.size()       | <per_pageno> |

    Examples:
      | per_page_param | per_pageno | page_param | page_no |
      | per_page       | 1          | page       | 1       |
      | per_page       | 2          | page       | 2       |
      | per_page       | 1          | page       | 3       |
      | per_page       | 3          | page       | 3       |

  Scenario Outline:4 List gists call through API and verify the response with different parameter combination

    Given The user send a call to List gist service request with <param> page parameter<pageno>
    And   The user send a call to List gist service request
    When  The List gist service response code should be 200
    Then  The response fields should match with below List gist file objects
      | owner.login[0] | ZINGYRAJEEV |
      | owner.id[0]    | 39244132    |
      | x.size()       | 30          |

    Examples:
      | param    | pageno               |
      | per_page | 30                   |
      | page     | 1                    |
      | since    | 2021-01-01T12:12:12Z |

  Scenario Outline:5 List gists call through API and verify the response with different parameter combination

    Given The user send a call to List gist service request with <per_page_param> page parameter<per_pageno>
    And   The user send a call to List gist service request with <page_param> perpage parameter<page_no>
    And   The user send a call to List gist service request with <since_param> perpage parameter<since_date>
    And   The user send a call to List gist service request
    When  The List gist service response code should be 200
    Then  The response fields should match with below List gist file objects
      | owner.login[0] | ZINGYRAJEEV  |
      | owner.id[0]    | 39244132     |
      | x.size()       | <per_pageno> |

    Examples:
      | per_page_param | per_pageno | page_param | page_no | since_param | since_date           |
      | per_page       | 1          | page       | 1       | since       | 2021-01-01T12:12:12Z |
      | per_page       | 2          | page       | 2       | since       | 2021-01-01T12:12:12Z |
      | per_page       | 1          | page       | 3       | since       | 2021-01-01T12:12:12Z |
      | per_page       | 3          | page       | 0       | since       | 2021-01-01T12:12:12Z |


