# Description #

This is simple REST service which returns details of given Github repository. Details includes:

* full name of repository
* description of repository
* git clone url
* number of stargazers
* date of creation formatted in requesters local date format

# API usage #

The API of the service looks as follows:

Request:
```
#!text
GET /repositories/{owner}/{repository-name}
```

Response:
```
#!json
{
   "fullName": "...", 
   "description": "...", 
   "cloneUrl": "...", 
   "stars": 0, 
   "createdAt": "..."
}
```

# GitHub API #

GitHub API reference can be found at: https://developer.github.com/v3/