
<div align="center">
  <a href="https://github.com/charakamihiranga/cropmonitor-api">
   <img src="https://github.com/user-attachments/assets/733feffd-fbfb-4efe-9de3-03cff55e6e7f" alt="Logo" width="80" height="80">
  </a>
  <h3>CropMonitor API</h3>
  <p>
    A Spring Boot-based backend system for managing agricultural operations at Green Shadow (Pvt) Ltd.
    <br />
    <br />
    <br />
    
  </p>
</div>


## About The Project

**CropMonitor API** is a Spring Boot-based backend system designed for **Green Shadow (Pvt) Ltd**, a mid-scale farm specializing in root crops and cereals. The system facilitates the management of fields, crops, staff, vehicles, equipment, and monitoring logs, providing a comprehensive solution for agricultural operations.

### Key Features:

- **Field Management:**  
  Manage fields allocated for cultivation, including field codes, GPS coordinates, and crop assignments.  
  **CRUD Operations:** Create, Read, Update, Delete fields.  

- **Crop Management:**  
  Track crop types, scientific names, seasons, and associated fields.  
  **CRUD Operations:** Create, Read, Update, Delete crops.  

- **Staff Management:**  
  Handle human resources, including staff assignments to fields and vehicles.
  **CRUD Operations:** Create, Read, Update, Delete staff records.  

- **Vehicle Management:**  
  Oversee vehicle allocations, fuel types, statuses, and driver assignments.  
  **CRUD Operations:** Create, Read, Update, Delete vehicle details.  

- **Equipment Management:**  
  Manage agricultural equipment assignments to fields/staff and track availability.  
  **CRUD Operations:** Create, Read, Update, Delete equipment records.  

- **Monitoring Logs:**  
  Record crop observations, images, and staff/field associations.  
  **CRUD Operations:** Create, Read, Update, Delete logs.  

- **User Authentication & Authorization:**  
  - **Role-Based Access Control (RBAC):**  
    - **MANAGER:** Full CRUD access to all entities.  
    - **ADMINISTRATIVE:** Restricted from editing crop data, field data, and monitoring logs.  
    - **SCIENTIST:** Restricted from modifying staff, vehicle, or equipment data.  
  - **Security:** OAuth 2.0 with JWT for stateless authentication.  
  - **Password Encryption:** BCrypt hashing to prevent plaintext storage.  

### Built With
- [![Spring Boot][Spring.io]][Spring-url]
- [![MySQL][MySQL.com]][MySQL-url]
- [![Gradle][Gradle.org]][Gradle-url]
- [![JWT][JWT.io]][JWT-url]

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites
- **Java JDK 17** or higher
- **MySQL 8.0** or higher
- **Gradle 7.4** or higher
- **Git** (for version control)

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/cropmonitor-api.git
   cd cropmonitor-api
   ```

2. **Set Up MySQL Database**:
   - Create a new database in MySQL named `cropmonitor`
   - Update the `application.properties` file with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/cropmonitor
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     ```

3. **Build the Project**:
   ```bash
   gradle clean build
   ```

4. **Run the Application**:
   ```bash
   gradle bootRun
   ```

5. **Access the API**:
   - The API will be available at `http://localhost:8080`


## API Documentation

For detailed API documentation and examples, please visit our [API Documentation](https://documenter.getpostman.com/view/35384500/2sAYX3r3zz).

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<!-- MARKDOWN LINKS & IMAGES -->
[product-screenshot]: images/screenshot.png
[Spring.io]: https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot
[Spring-url]: https://spring.io/
[MySQL.com]: https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white
[MySQL-url]: https://www.mysql.com/
[Gradle.org]: https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white
[Gradle-url]: https://gradle.org/
[JWT.io]: https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens
[JWT-url]: https://jwt.io/
