@accessibility
Feature: gists_accessibility

  In order to test gists_accessibility,
  As a developer, i would like to test gists_accessibility with below,
  mentioned feature:

  The Gist API provides up to one megabyte of content for each file in the gist. Each file returned for  a gist through the API has a key called truncated. If truncated is true, the file is too large and only a portion of the contents were returned in content.

  If you need the full contents of the file, you can make a GET request to the URL specified by raw_url. Be aware that for files larger than ten megabytes, you'll need to clone the gist via the URL provided by git_pull_url.

  get /gists

  Parameters for media type :
  application/vnd.github.VERSION.raw
  application/vnd.github.VERSION.base64

  Parameter:
  since
  per_page
  page

  Details : https://docs.github.com/en/rest/reference/gists#authentication

  Scenario Outline:1 Gists accessibility testing call through API and verify the response
  for supported media type

    Given Header request for authentication <Header>
    And   GistList set the basepath /gists
    And   The user send a call to List gist service request
    When  The List gist service response code should be 200
    And    Validate the response header for Gists accessibility test
      | X-OAuth-Scopes | admin:enterprise, admin:gpg_key, admin:org, admin:org_hook, admin:public_key, admin:repo_hook, delete:packages, delete_repo, gist, notifications, repo, user, workflow, write:discussion, write:packages |
    Then  The response fields should match with below List gist file objects
      | owner.login[0] | ZINGYRAJEEV |
      | owner.id[0]    | 39244132    |

    Examples:
      | Header                                |
      | application/vnd.github.VERSION.raw    |
      | application/vnd.github.VERSION.base64 |
      | application/vnd.github.v3+json        |

  Scenario Outline:2 Gists accessibility testing call through API and verify the response
  for supported media type with different parameter

    Given Header request for authentication <Header>
    And  GistList set the basepath /gists
    And The user send a call to List gist service request with different parameter
      | per_page | 1 |
      | page     | 1 |
    And   The user send a call to List gist service request
    When  The List gist service response code should be 200
    And   Validate the response header for Gists accessibility test
      | X-OAuth-Scopes | admin:enterprise, admin:gpg_key, admin:org, admin:org_hook, admin:public_key, admin:repo_hook, delete:packages, delete_repo, gist, notifications, repo, user, workflow, write:discussion, write:packages |
    Then  The response fields should match with below List gist file objects
      | owner.login[0] | ZINGYRAJEEV |
      | owner.id[0]    | 39244132    |

    Examples:
      | Header                                |
      | application/vnd.github.VERSION.raw    |
      | application/vnd.github.VERSION.base64 |
      | application/vnd.github.v3+json        |

  Scenario Outline:3 Gists accessibility testing call through API and verify the response
  for supported media type with different parameter combination

    Given Header request for authentication <Header>
    And GistList set the basepath /gists
    And The user send a call to List gist service request with <per_page_param> page parameter<per_pageno>
    And The user send a call to List gist service request with <page_param> perpage parameter<page_no>
    And The user send a call to List gist service request
    When The List gist service response code should be 200
    And  Validate the response header for Gists accessibility test
      | X-OAuth-Scopes | admin:enterprise, admin:gpg_key, admin:org, admin:org_hook, admin:public_key, admin:repo_hook, delete:packages, delete_repo, gist, notifications, repo, user, workflow, write:discussion, write:packages |
    Then  The response fields should match with below List gist file objects
      | owner.login[0] | ZINGYRAJEEV  |
      | owner.id[0]    | 39244132     |
      | x.size()       | <per_pageno> |

    Examples:
      | per_page_param | per_pageno | page_param | page_no | Header                                |
      | per_page       | 1          | page       | 1       | application/vnd.github.VERSION.raw    |
      | per_page       | 2          | page       | 2       | application/vnd.github.VERSION.base64 |
      | per_page       | 1          | page       | 3       | application/vnd.github.VERSION.base64 |
      | per_page       | 3          | page       | 3       | application/vnd.github.VERSION.raw    |

  Scenario Outline:4 List gists call through API and verify the response with different parameter combination

    Given  Header request for authentication <Header>
    And    GistList set the basepath /gists
    And    The user send a call to List gist service request with <per_page_param> page parameter<per_pageno>
    And    The user send a call to List gist service request with <page_param> perpage parameter<page_no>
    And    The user send a call to List gist service request with <since_param> perpage parameter<since_date>
    And    The user send a call to List gist service request
    When   The List gist service response code should be 200
    And    Validate the response header for Gists accessibility test
      | X-OAuth-Scopes | admin:enterprise, admin:gpg_key, admin:org, admin:org_hook, admin:public_key, admin:repo_hook, delete:packages, delete_repo, gist, notifications, repo, user, workflow, write:discussion, write:packages |
    Then   The response fields should match with below List gist file objects
      | owner.login[0] | ZINGYRAJEEV  |
      | owner.id[0]    | 39244132     |
      | x.size()       | <per_pageno> |

    Examples:
      | per_page_param | per_pageno | page_param | page_no | since_param | since_date           | Header                                |
      | per_page       | 1          | page       | 1       | since       | 2021-01-01T12:12:12Z | application/vnd.github.VERSION.raw    |
      | per_page       | 2          | page       | 2       | since       | 2021-01-01T12:12:12Z | application/vnd.github.VERSION.base64 |
      | per_page       | 1          | page       | 3       | since       | 2021-01-01T12:12:12Z | application/vnd.github.VERSION.base64 |


  Scenario Outline:5 List gists call through API and verify the response with different parameter combination
  without token authorization. Expectation is that api will send response of unauthinticated users with file details
    Given  header request for with out authentication token <Header>
    And    GistList set the basepath /gists
    And    The user send a call to List gist service request with <per_page_param> page parameter<per_pageno>
    And    The user send a call to List gist service request with <page_param> perpage parameter<page_no>
    And    The user send a call to List gist service request with <since_param> perpage parameter<since_date>
    And    The user send a call to List gist service request
    When   The List gist service response code should be 200
    And    Validate the response header for Gists accessibility test
      | X-OAuth-Scopes | null |
    Then   The response fields should match with below List gist file objects
      | x.size() | 100 |

    Examples:
      | per_page_param | per_pageno | page_param | page_no | since_param | since_date           | Header                                |
      | per_page       | 1000       | page       | 1       | since       | 2021-01-01T12:12:12Z | application/vnd.github.VERSION.raw    |
      | per_page       | 2000       | page       | 2       | since       | 2021-01-01T12:12:12Z | application/vnd.github.VERSION.base64 |
      | per_page       | 3000       | page       | 30      | since       | 2000-01-01T12:12:12Z | application/vnd.github.VERSION.raw    |