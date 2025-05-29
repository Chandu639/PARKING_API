# 🚗 Parking Management API

A robust Spring Boot-based RESTful API for managing parking spot reservations. This system supports user authentication via JWT, user management, parking spot allocation, and booking operations.

## 📁 Project Structure
final-parking-api/
├── src/
│ └── main/
│ ├── java/com/parking/
│ │ ├── controller/ # REST Controllers (Auth, Booking, User, ParkingSpot)
│ │ ├── dto/ # Data Transfer Objects
│ │ ├── model/ # Entity classes (User, Booking, ParkingSpot)
│ │ ├── repository/ # Spring Data JPA repositories
│ │ ├── security/ # JWT & Spring Security configs
│ │ ├── service/ # Service layer logic
│ │ └── ParkingApplication.java
│ └── resources/ # Configuration files
├── pom.xml # Maven configuration


## 🚀 Features

- 🔐 JWT Authentication
- 👥 User Registration & Login
- 🅿️ Parking Spot Management
- 📅 Booking Management
- 📦 Layered architecture (Controller-Service-Repository)
- 🌐 RESTful API endpoints

## 🛠 Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 / MySQL (configurable)
- Maven

## 🔧 Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/final-parking-api.git
   cd final-parking-api

2. **Build the Project**
   mvn clean install

3. **Run the Application**
   mvn spring-boot:run

4. **Access the API**
    URL: http://localhost:8080

**📬 Sample API Endpoints**
| Method | Endpoint                  | Description            |
| ------ | ------------------------- | ---------------------- |
| POST   | `/auth/register`          | Register a new user    |
| POST   | `/auth/login`             | Authenticate user      |
| GET    | `/parking-spots`          | List all parking spots |
| POST   | `/bookings`               | Create a new booking   |
| GET    | `/bookings/user/{userId}` | Get user bookings      |
**⚠️ Some endpoints require authentication using a JWT token in the Authorization header.**


**🧪 Testing**
Use Postman or any API client to test the endpoints. For protected routes, include:
Authorization: Bearer <your_token_here>


**🤝 Contributing**
Contributions are welcome! Fork the repository, create a new branch, make your changes, and submit a pull request.

**📄 License**
This project is licensed under the MIT License.

Made with ❤️ using Spring Boot.

---




