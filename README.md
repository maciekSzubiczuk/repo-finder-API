# GitHub Repository Finder API

This API allows you to list the GitHub repositories of a user, excluding forks. It provides information such as the repository name, owner login, each branches name and it's last commit sha.

## API Endpoint
GET /repo-finder/{username}

### Request Headers

- Accept: application/json

### Response Format

The API response will be in JSON format and include the following information for each repository:

- Repository Name
- Owner Login
- Branches (including name and last commit SHA)

## Getting Started

To get started with this project, follow the instructions below.

### Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven
- Fine-grained personal access token, if you don't have one, follow instructions on this site:
https://github.blog/2022-10-18-introducing-fine-grained-personal-access-tokens-for-github/#creating-personal-access-tokens

### Installation

1. Clone the repository:
```
git clone https://github.com/maciekSzubiczuk/EmployeeManagmentAppJavaFX.git
```
2. Navigate to the project directory for e.g:
```
cd repo-finder
```
3. Build the project:
```
mvn clean install
```
4. In application.properties file in this line:
```
token=YOUR_TOKEN
```
change YOUR_TOKEN to your own fine-grained personal access token that You generated earlier

4. Run the application:
```
mvn spring-boot:run
```

### Usage

Once the application is running, you can make a GET request to the following endpoint:
GET http://localhost:80/repo-finder/{username}

For example You can do it using cURL command:
```
curl http://localhost:80/repo-finder/{username} -H "Accept: application/json"
```

Replace `{username}` with the GitHub username for which you want to retrieve the repositories.
Make sure to include the following request header:
Accept: application/json


The API will respond with the list of repositories in JSON format.

### Error Handling

The API handles the following error scenarios:

- If the request includes the header "Accept: application/xml", a 406 Not Acceptable response will be returned, indicating that XML response format is not supported.
- If the request does not include the header "Accept: application/json", a 415 Unsupported Media Type response will be returned, indicating an invalid media type.
- If the requested user does not exist or has no repositories, a 404 Not Found response will be returned.











