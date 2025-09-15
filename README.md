# Pets API Software Engineering Challenge

This is a REST application that uses Spring Boot for managing pet information.
The application exposes endpoints for creating, updating, deleting, and retrieving pet
information.

## Technologies Used

- Java 17
- SpringBoot
- H2 Database
- Mapstruct
- Maven
- Docker

## Requirements

Only Docker is required.

## How to Run the Project

From the root of the project (where the `Dockerfile` is located), run:

#### For Build
```bash 
docker build -t petsapi:latest . 
```

#### For Execute
```bash
docker run -p 8080:8080 petsapi:latest
```

#### URL
Now the service is running on: http://localhost:8080

## Endpoints

| Method | Path         | Description                   | Query Params     | Request Body    | Response     |
|--------|--------------|-------------------------------|------------------|-----------------|--------------|
| GET    | `/pets`      | Gets all pets (paginated)     | `page`, `size`   | -               | 200 OK       |
| GET    | `/pets/{id}` | Gets a pet by ID              | -                | -               | 200 OK / 404 |
| POST   | `/pets`      | Creates a pet in the DB       | -                | `PetRequestDto` | 201 OK       |
| PATCH  | `/pets/{id}` | Updates a pet by ID in the DB | -                | `PetRequestDto` | 200 OK / 404 |
| DELETE | `/pets/{id}` | Deletes a pet by ID           | -                | -               | 204 OK / 404 |

#### Request Body Example
```json 
{
  "name": "Luna",
  "species": "Dog",
  "age": 3,
  "ownerName": "Mary Johnson"
}
```

#### Response Body Example
```json 
{
  "id": 1,
  "name": "Luna",
  "species": "Dog",
  "age": 3,
  "ownerName": "Mary Johnson"
}
```

## Author

This project was developed by `Germán Furfori` as part of a technical evaluation.