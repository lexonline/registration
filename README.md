# regis

## Description
---

This is a very basic implementation of an authentication and authorization server using Spring Boot
and Spring Security.  Users are authenticated against user data in an in-memory H2 database.  Valid
users are granted a signed JSON web token, which must be used when accessing secured resources.

## Dependencies
---

* Java Development Kit 8
* Apache Maven

## Configuration
---

`HTTP Port:  8080`

`Application Context:  /`

`Data Source:  In-Memory H2 Database`

## Usage
---

From the command line, start the auth server:

    mvn spring-boot:run

Open a new shell to retrieve an access token:

    curl -X POST "http://android@localhost:8080/oauth/token" -H "Accept: application/json" -d "grant_type=password&username=lexrizen@hotmail.com&password=secret1&scope=mobile_rw&client_id=android"

You should receive a response similar to the following:

    {
	    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsic29tZV9zZWN1cmVfcmVzb3VyY2UiXSwidXNlcl9uYW1lIjoibGV4cml6ZW5AaG90bWFpbC5jb20iLCJzY29wZSI6WyJtb2JpbGVfcnciXSwiZXhwIjoxNTYyMjgxMDYyLCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiI4YzEyYzA0Mi0zZGQ4LTQwNjktYTI3NS01MWFlMTI1ODRiZGQiLCJjbGllbnRfaWQiOiJhbmRyb2lkIn0.Oh2YF1CAuyJsqT4Xa9P6E3k6LVkXUqrhquy2DwG4O5k",
	    "token_type": "bearer",
	    "expires_in": 57599,
	    "scope": "mobile_rw",
	    "jti": "8c12c042-3dd8-4069-a275-51ae12584bdd"
    }

The response above contains your access token (JSON field with named `access_token`) along with
some additional data.

Now, use the access token you received from the server to access the secure URL:

    curl http://localhost:8080/registeruser curl --location --request POST "http://localhost:8080/registeruser" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsic29tZV9zZWN1cmVfcmVzb3VyY2UiXSwidXNlcl9uYW1lIjoibGV4cml6ZW5AaG90bWFpbC5jb20iLCJzY29wZSI6WyJtb2JpbGVfcnciXSwiZXhwIjoxNTYyMjgxMDYyLCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiI4YzEyYzA0Mi0zZGQ4LTQwNjktYTI3NS01MWFlMTI1ODRiZGQiLCJjbGllbnRfaWQiOiJhbmRyb2lkIn0.Oh2YF1CAuyJsqT4Xa9P6E3k6LVkXUqrhquy2DwG4O5k" \
  --header "Content-Type: application/json" \
  --header "Accept: application/json" \
  --data "{
	\"firstName\" : \"John\",
	\"lastName\" : \"Smith\",
	\"emailAddress\" : \"john.smith@mail.com\",
	\"password\" : \"xxxxxx\",
	\"phone\" : \"08913138568\",
	\"address\" : \"\",
	\"salary\" : 9999
}"

You should receive the following JSON response:

    {
	    "code": 200,
	    "message": "Success"
    }

That means the server accepted your access token and authorized you to access the secure URL

You can use Postman tool for test (refer in document https://documenter.getpostman.com/view/8034411/SVSBvs1Z?version=latest)
