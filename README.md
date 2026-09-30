# ANTX URL Shortener

A simple URL Shortener web application built using **Java, Spring Boot, MySQL, HTML, and CSS**.
## Features
* Convert long URLs into short URLs
* Redirect short URLs to the original URL
* Store URL mappings in MySQL
* Track click count
* Generate unique short codes
* Simple web interface

## Technologies Used
* Java 17
* Spring Boot
* MySQL
* HTML
* CSS
* Maven

## Project Structure
```text
antx-url-shortener/
├── src/
├── pom.xml
├── frontend/
└── README.md
```

## How to Run
1. Clone the repository.
2. Configure your MySQL database.
3. Update the database configuration in the application.
4. Run the Spring Boot application.
5. Open the application in your browser.

## API

### Shorten URL
```http
POST /api/shorten
```

Example request:

```json
{
  "originalUrl": "https://www.google.com"
}
```

### Redirect

```http
GET /api/{shortCode}
```

## Author

**Guruz Antony Merson S.**

GitHub:
https://github.com/GURUZ-ANTONY-MERSON-S
