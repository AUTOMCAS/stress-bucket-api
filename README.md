# Stress Bucket API
### Backend Stress Bucket API.
The stress bucket is a model to help understand stress and wellbeing.

![stressbucket](https://user-images.githubusercontent.com/105917288/224075978-72a45f54-88f3-4738-a6b2-408dc20a43e5.jpeg)        

Management of:
- Users.
- A user's "Stress bucket", representing stress level.
- Events that alter a user's stress level.

## Technologies

- Spring Boot.
- Java.
- PostgreSQL.
- Password encryption with Bcrypt.
- JWT for payload encryption.


## Endpoints
Response body is in JSON format.
User login generates a JWT token with a 20 minute expiry.
A valid token is required for successful requests and responses to buckets and events endpoints.
Token must be provided with `Authorization` header in the form `Bearer [token]`

### USERS
#### Register a user    
`POST /api/users/register`

JSON request body:    
```
{
"username": "User",
"password": "password"
}
```

#### Login    
`POST /api/users/login`

JSON request body:
```
{
"username": "User",
"password": "password"
}
```

### BUCKETS
#### Create a Stress Bucket     
`POST /api/buckets`    

JSON request body:    
```
{
"name": "Chris' bucket 3",
"stressLevel": 50
}
```

#### Get Bucket by ID    
`GET /api/buckets/{bucketId}`    

#### Delete Bucket by ID    
`DELETE /api/buckets/{bucketId}`    

#### Update Bucket by ID    
`PUT /api/buckets/{bucketId}`  

JSON request body:    
```
{
"name": "Updated bucket",
"stressLevel": 60
}
```    

### EVENTS    
#### Create an Event for a Bucket    
`POST /api/buckets/{bucketId}/events`  

JSON request body:    
```
{
"stressType": "Release",
"description": "Pet dog",
"dateTime": "2019-02-03 10:08:02",
"stressLevelChange": 10
}
```

#### Get all Events for a Bucket    
`GET /api/buckets/{bucketId}/events`    

#### Get Events by ID for a Bucket    
`GET /api/buckets/{bucketId}/events/{eventId}`    

#### Get Events by Stress Type for a Bucket      
`GET /api/buckets/{bucketId}/events/stressType/Release`    


## Setup

### Database
Requirements: PostgreSQL setup and running.

Create the database and load the SQL seed file:
From inside the main directory:
```
createdb stressbucket_db
psql stressbucket_db < stressbucket_db.sql
```

Drop DB and re-seed:
```
dropdb -f stressbucket_db
createdb stressbucket_db
psql stressbucket_db < stressbucket_db.sql
```
