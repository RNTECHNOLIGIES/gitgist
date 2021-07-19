@accessibility
Feature: rate_limit.feature

  In order to test rate limit,
  As a developer, i would like to test rate limit with below,
  mentioned feature:

  With the Rate Limit API, you can check the current rate limit status of various REST APIs.
  The REST API overview documentation describes the rate limit rules. You can check your current rate limit status at any time using the Rate Limit API described below.

  the Rate Limit API response categorizes your rate limit. Under resources, you'll see four objects:

  1. The core object provides your rate limit status for all non-search-related resources in the      REST API.
  2.  The search object provides your rate limit status for the Search API.
  3.  The graphql object provides your rate limit status for the GraphQL API.
  4.  The integration_manifest object provides your rate limit status for the GitHub App Manifest     code conversion endpoint.

  get /rate_limit

  Details : https://docs.github.com/en/rest/reference/rate-limit

  Scenario:1 Get rate limit status for the authenticated user

    Given Rate limit Header request for authenticated user
    And   Rate limit set the basepath  /rate_limit
    And   The user send a call to rate limit service request
    When  The rate limit service response code should be 200
    And    Validate the response header for ratelimit
      | X-RateLimit-Limit | 5000                                                                                                                                                                                                     |
      | X-OAuth-Scopes    | admin:enterprise, admin:gpg_key, admin:org, admin:org_hook, admin:public_key, admin:repo_hook, delete:packages, delete_repo, gist, notifications, repo, user, workflow, write:discussion, write:packages |

    Then  The response fields should match with below rate limit objects
      | rate.limit                           | 5000 |
      | resources.core.limit                 | 5000 |
      | resources.search.limit               | 30   |
      | resources.graphql.limit              | 5000 |
      | resources.integration_manifest.limit | 5000 |
      | resources.source_import.limit        | 100  |
      | resources.code_scanning_upload.limit | 500  |

  Scenario:2 Get rate limit status for the unauthenticated user

    Given Rate limit Header request for unauthenticated user
    And   Rate limit set the basepath  /rate_limit
    And   The user send a call to rate limit service request
    When  The rate limit service response code should be 200
    And    Validate the response header for ratelimit
      | X-RateLimit-Limit | 60   |
      | X-OAuth-Scopes    | null |
    Then  The response fields should match with below rate limit objects
      | rate.limit              | 60 |
      | resources.core.limit    | 60 |
      | resources.search.limit  | 10 |
      | resources.graphql.limit | 0  |

