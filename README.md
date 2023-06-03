# URL Shortener REST API

We are excited to introduce our collaborative project, the URL Shortener Web Application and API. This project was developed by two individuals, combining their expertise in Angular for the frontend and Spring Boot with PostgreSQL for the backend.

The URL Shortener REST API is a service that allows users to shorten long URLs and redirect to the original long URLs using shortened URLs. It provides the following functionalities:

- Shorten a long URL and generate a shortened URL.
- Redirect to the original long URL using a shortened URL.
- Retrieve the original long URL associated with a shortened URL.
- Get a list of all URLs stored in the system.
- Delete a URL by its shortened code.
- Update the shortened code of a URL.

## Technologies Used

- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- Jackson JSON library

## Prerequisites

- Java 8 or higher
- Maven
- Spring Boot

## Setup and Installation

1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Build the project using Gradle.
4. Run the application.

## API Endpoints:

### Shorten URL
    POST /shorten
    Shortens a long URL and generates a shortened URL.

#### Request
- Method: POST
- URL: /shorten
- Body:
  - Content-Type: application/json
  - Example Request Body:
      {
        "longUrl": "https://example.com/very/long/url"
      }

#### Response
- Status Code: 200 OK
- Body:
  - Content-Type: application/json
  - Example Response Body:
      {
        "longUrl": "https://example.com/very/long/url",
        "shortUrl": "abcd1234"
      }
    
    
### Redirect to Long URL
    GET /{shortUrl}
    Redirects to the original long URL associated with the provided shortened URL.

#### Request
- Method: GET
- URL: /{shortUrl}

#### Response
- Status Code: 302 Found
- Redirects to the original long URL.


### Get Long URL
    GET /get/{shortUrl}
    Retrieves the original long URL associated with the provided shortened URL.

#### Request
- Method: GET
- URL: /get/{shortUrl}
- 
#### Response
- Status Code: 200 OK
- Body:
  - Content-Type: application/json
  - Example Response Body:
      {
        "longUrl": "https://example.com/very/long/url",
        "shortUrl": "abcd1234"
      } 
    
  
### Get All URLs
    GET  /getall
    Retrieves a list of all URLs stored in the system.

#### Request
- Method: GET
- URL: /getall


#### Response
- Status Code: 200 OK
- Body:
  - Content-Type: application/json
  - Example Response Body:
    [
      {
        "longUrl": "https://example.com/url1",
        "shortUrl": "abcd1234"
      },
      {
        "longUrl": "https://example.com/url2",
        "shortUrl": "efgh5678"
      }
    ]


### Delete URL
    DELETE /delete/{shortUrl}
    Deletes a URL by its shortened code.

#### Request
- Method: DELETE
- URL: /delete/{shortUrl}

#### Response
- Status Code:
- 200 OK if the URL is successfully deleted.
- 404 Not Found if the URL with the provided shortened code does not exist.


### Update URL
    PUT update?oldCode=value1&newCode=value2
    Updates the shortened code of a URL.

#### Request
- Method: PUT
- URL: update?oldCode=value1&newCode=value2

#### Response
- Status Code:
- 200 OK if the URL is successfully updated.
- 404 Not Found if the URL with the provided old code does not exist.

## Error Handling
The API handles errors in the following ways:

- If a shortened URL is not found or invalid, a 404 Not Found response is returned.
- If there is an internal server error, a 500 Internal Server Error response is returned.
