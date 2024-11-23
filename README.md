# branch-hiring-exercise
Spring Boot application that integrates with the GitHub API to fetch user details and repositories, transform the data, and expose it through RESTful endpoints.

# Setup and Installation
Prerequisites
- Java
- Maven

# Steps

Build the project:
```bash
mvn clean install
```

Run the application:
```bash
mvn spring-boot:run
```
Access the API at:
```bash
http://localhost:8080/api/users/{username}
```


# Test Frameworks
JUnit 5: For unit tests.
Mockito: For mocking dependencies and isolating the service layer.

# Potential Future Improvements
- Add authentication for GitHub API requests to handle rate limits.
- Cache user data to reduce redundant API calls.
- Implement pagination for large repository lists.

# References
GitHub REST API Documentation: https://docs.github.com/en/rest/using-the-rest-api/getting-started-with-the-rest-api?apiVersion=2022-11-28
