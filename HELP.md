# Project manager

### How to run

1) Choose "dev" run configuration   
2) Run selected configuration

### Api
Check openapi.yaml

### Default users
1) Login: admin; Password: admin; Role: ADMIN
2) Login: user; Password: user; Role: USER

### How to login
1) Send POST request to /api/login with credentials
2) Get token from response
3) Use authorization: "Bearer {token}"

### Tests
There are must be tests, but I don't have enough time right now because of work and diploma

### Info
* java 17
* h2
* spring, spring security
* bearer authorization
* flyway
* maven
* jwt

### Further development
1) Move Router and Dtos to another repo and create js analog to be able to use it as lib in frontend
2) Create unit, integration and mvc tests
3) Create frontend with https://www.vue-tailwind.com/