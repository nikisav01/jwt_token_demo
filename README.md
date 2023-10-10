# jwt_token_demo
A sample program for learning Spring Security.

POST localhost:8080/api/login:
{
    "username": "user", \\ or "admin"
    "password": "password"
}
Response: 
{
    "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTY5Njk3NzI4OX0.cVTrL7SfhjBqOXsSUfrz3LxYHKGj2mguGlrsM6Xbqn8",
    "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjk2OTgwMjg5fQ.v0gvKoQBH2AYejhYWVrktW4OzPncAKMHanVCImLsBWs"
}

POST localhost:8080/api/tokens/refresh:
{
    "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTY5Njk3NzI4OX0.cVTrL7SfhjBqOXsSUfrz3LxYHKGj2mguGlrsM6Xbqn8",
    "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjk2OTgwMjg5fQ.v0gvKoQBH2AYejhYWVrktW4OzPncAKMHanVCImLsBWs"
}
Response:
{
    "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTY5Njk3NzI5NX0.TiRqmGAisbCSwE58b_xPdq_w5w77qi45q91CFoM1xK0",
    "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjk2OTgwMjk1fQ.qh7wyvYsO35UN4NtLoZxe_CGadE1Q-k0FAC2nYGfwgE"
}

User's endpoint:
GET localhost:8080/api/greeting with user's accessToken (HeaderName: Authorization, content: "Bearer {accessToken"))
Response: Hello, user

Admin's endpoint:
GET localhost:8080/api/admin/greeting with admin's accessToken (HeaderName: Authorization, content: "Bearer {accessToken"))
Response: Hello, admin
