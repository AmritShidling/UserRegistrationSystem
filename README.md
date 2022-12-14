# Happeo User Management System

APIs for below features:

1. API which configure Happeo to create a new
provisioner and generate a secret

POST - /api/provisioners

Request Payload:
{
    "userName": "Amrit",
    "password" : "ABCD"
}

Response Payload:

{
    "provisionerId": "1",
    "secretKey": "THISISRONDOMSECRETFROMHAPPEO"
}

This API will create Provisioner in H2 DB & send generated provisioner ID & secret for sign JWT token
in the Response. For this API JWT token is not required to pass in Authorization Header

2. API to generate JWT token with User Details & secret shared

POST - /api/authenticate

Request Payload:
{
    "userName": "Amrit",
    "password" : "ABCD"
}

Respose - JWT Token

eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbXJpdCIsImV4cCI6MTY2MTcyODYyOSwiaWF0IjoxNjYxNjkyNjI5fQ.xpN1288lgSO8kqzVpbcE7tcggDWZ-L5kK4eYL2-D7Qs

3. API to accept Users as per
the schema provided by the external identity system

POST - /api/organisations/{orgId}/provisioner/{provisionerId}/users

Pass JWT token received by API listed point- 2(/api/authenticate)
in Authorization Header & value should prefixed with "Bearer "
Ex - Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbXJpdCIsImV4cCI6MTY2MTcyODYyOSwiaWF0IjoxNjYxNjkyNjI5fQ.xpN1288lgSO8kqzVpbcE7tcggDWZ-L5kK4eYL2-D7Qs

Request Payload

{
  "email": "amritlshidling@gmail.com",
  "name": {
    "firstName": "Amrit",
    "lastName": "shidling"
  },
  "id": "123"
}

Response Payload 

{
    "email": "amritlshidling@gmail.com",
    "name": {
        "firstName": "Amrit",
        "lastName": "shidling"
    },
    "id": "1",
    "applicationId": "4"
}

This API will retrieve JWT from Authorization header & Validates it. If token is valid
& is not expired then User data will stored in H2 database USER_TBL
generated applicationId returned in response along with other details.

JWT Logic Details:

Before reaching controller additional filter (JWTFilter) class added to validate token
this filter will extract userName from Token using shared secret. Loads Provisioner data from DB using extracted userName & verifies is provisioner is valid.
I am setting expiration time for token as 1 day.
& using HS256 signature alogorithm.

Once validation completed user data will saved in DB in below DB schema

id - Happeo User???s internal autogenerated id
email - User???s email
organisation_id - Organisation ID of the user
first_name
last_name
external_id - ID of the user in the external system (null for
internal user of Happeo)
password - Hashed password of the user (null for externally
provisioned users)
is_active - User activation flag (true means user is active)

4. API list all inactive users and activate
them.

GET - /api/users/activate

Response 

Number Users activated :3 Successfully..

This API will retrieve all user will flag is_active as false in DB.
set it true & save it back.
Return count of users activated.

Tools used:

Spring boot, H2 in memory DB, spring security, JWT utility
Maven for buid
