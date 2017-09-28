[![Build Status](https://travis-ci.org/kaminski-tomasz/repo-details.svg?branch=master)](https://github.com/kaminski-tomasz/repo-details)
[![Coverage Status](https://coveralls.io/repos/github/kaminski-tomasz/repo-details/badge.svg?branch=master)](https://coveralls.io/github/kaminski-tomasz/repo-details?branch=master)

# Description #

This is simple Spring Boot REST service which returns details of given Github repository. 

Details includes:

* full name of repository
* description of repository
* git clone url
* number of stargazers
* date of creation formatted in requesters local date format

# API #

The API of the service looks as follows:

Request:
```
GET /repositories/{owner}/{repository-name}
```

Response:
```
{
   "fullName": "...", 
   "description": "...", 
   "cloneUrl": "...", 
   "stars": 0, 
   "createdAt": "..."
}
```

# Example #

Request:
```
GET /repositories/octokit/octokit.rb
```

Response:
```
{
  "fullName" : "octokit/octokit.rb",
  "description" : "Ruby toolkit for the GitHub API",
  "cloneUrl" : "https://github.com/octokit/octokit.rb.git",
  "stars" : 2514,
  "createdAt" : "10 grudnia 2009, 21:41:49 GMT"
}
```

# Running #

```
$ mvn clean spring-boot:run
```

# Running tests #

```
$ mvn clean verify
```

# GitHub API #

GitHub API reference can be found at: https://developer.github.com/v3/