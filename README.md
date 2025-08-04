# GitHub Repository Lister API

A simple Spring Boot application that provides a REST API to fetch a GitHub user's public non-fork repositories.  
For each repository, the API lists the repository name, owner's login, and for each branch, its name and the last commit
SHA.
The application gracefully handles both successful data retrieval and the case where the specified GitHub user does not
exist.

## Features

- Lists all public, **non-fork** repositories of a given GitHub user.
- For each repository, returns:
    - Repository Name
    - Owner Login
- For each branch in a repository, returns:
    - Branch Name
    - Last Commit SHA
- Returns a clear `404 Not Found` error with a descriptive JSON body if the GitHub user does not exist.
- Uses the official GitHub API v3 as the data source.

## Tech Stack

- Java 21
- Spring Boot 3
- Maven
- Lombok (for reducing boilerplate code)
- RestTemplate (for REST API communication)
- JUnit 5 (for integration testing)

## Getting Started

### Prerequisites

- JDK 21 or higher
- Maven 3.8.x or higher

### Installation & Running

1. Clone the repository:

```bash
git clone https://github.com/BartoszJatczyszyn/github-repositories-api
cd github-repositories-api
```

2. Build the project

```bash
mvn clean install
```

3. Run the application

```bash
java -jar target/github-repositories-api-0.0.1-SNAPSHOT.jar
```

The application will start on http://localhost:8080.

## API USAGE

### Endpoint

```bash
GET /repositories/{username}
```

- {username}: GitHub username to query.

### Example: Successful Request

Request:

```bash
curl http://localhost:8080/repositories/BartoszJatczyszyn
```

Response (200 OK):

```json
{
  "name": "CarControl",
  "ownerLogin": "BartoszJatczyszyn",
  "branches": [
    {
      "name": "Home_screen",
      "lastCommitSha": "89e01fe112af2d674bb3063ea59f2d418bf8ad1a"
    },
    ...
  ]
}
```

### Example: User Not Found

Request:

```bash
curl http://localhost:8080/repositories/non-existent-user-123456789
```

Response (404 Not Found):

```json
{
  "status": 404,
  "message": "User not found"
}
```

### Running Tests

To run the integration tests, execute:

```bash
mvn test
```

### Notes

- This application uses the official GitHub REST API v3.
- Only public repositories that are not forks are returned.
- The API response follows best practices with clear error messages.