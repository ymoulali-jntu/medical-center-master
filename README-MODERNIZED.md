# Medical Center Management System - Modernized

A modernized web-based Online Medical Center Management System for SUST Medical Center, upgraded to use the latest technologies and best practices.

## Modernization Summary

This project has been upgraded from its original 2011 codebase to modern standards:

### Technology Upgrades
- **Java**: Java 6 → Java 21
- **Build System**: NetBeans → Maven
- **Servlet API**: javax.servlet 2.5 → jakarta.servlet 6.0 (Jakarta EE 10)
- **Database**: MySQL JDBC Driver → MySQL Connector/J 8.0.33 with HikariCP connection pooling
- **Frontend Framework**: jQuery 1.4.2 → jQuery 3.7.1
- **UI Framework**: Added Bootstrap 5.3.2
- **Logging**: Added SLF4J 2.0.9
- **HTML**: HTML4 → HTML5
- **Project Structure**: NetBeans structure → Maven standard layout

### Key Improvements
- Modern connection pooling with HikariCP for better performance
- Jakarta EE 10 compliance for future-proofing
- Responsive design with Bootstrap 5
- CDN-based jQuery and Bootstrap for faster loading
- Proper logging with SLF4J
- Maven-based dependency management
- Modern CSS with responsive design

## Requirements

### Software Requirements
- **Java**: JDK 21 or higher
- **Maven**: 3.8+ (for building)
- **MySQL**: MySQL 8.0+ or MariaDB 10.5+
- **Servlet Container**: Tomcat 10+ or Jetty 11+ (supports Jakarta EE 10)

### Database Setup
1. Install MySQL 8.0+ or MariaDB 10.5+
2. Create database: `central_db`
3. Import the schema from `databse/central_db.sql`
4. Update database credentials in `src/main/java/medicalcenter/database.java`:
   ```java
   private String userName = "root";  // Your MySQL username
   private String password = "admin";  // Your MySQL password
   ```

## Project Structure

```
medical-center-master/
├── pom.xml                          # Maven configuration
├── src/
│   └── main/
│       ├── java/
│       │   ├── medicalcenter/        # Database utilities
│       │   │   ├── database.java     # Modernized with HikariCP
│       │   │   ├── ClientDate.java
│       │   │   └── StockLedgerEntry.java
│       │   └── servlet/             # Jakarta EE servlets
│       │       ├── LoginVerify.java
│       │       ├── AddNewMed.java
│       │       ├── MedicineDelivered.java
│       │       └── ... (14 servlets total)
│       └── webapp/
│           ├── CSS/
│           │   ├── default.css       # Enhanced with responsive design
│           │   └── style.css
│           ├── javascript/
│           ├── include/
│           │   ├── header.jsp        # Modernized with CDN links
│           │   ├── footer.jsp        # Added Bootstrap JS
│           │   └── ...
│           ├── WEB-INF/
│           │   └── web.xml           # Updated to Servlet 6.0
│           └── *.jsp                 # JSP pages
└── databse/
    ├── central_db.sql                # Database schema
    └── DBQuery.txt                   # Database queries
```

## Building and Running

### Using Maven
```bash
# Build the project
mvn clean package

# The WAR file will be created in: target/medical-center.war
```

### Using Maven Jetty Plugin (Development)
```bash
# Run the application
mvn jetty:run

# Access at: http://localhost:8080/medical-center
```

### Deploy to Tomcat
1. Build the WAR file: `mvn clean package`
2. Copy `target/medical-center.war` to Tomcat's `webapps` directory
3. Start Tomcat
4. Access at: `http://localhost:8080/medical-center`

## Default Users

The system includes test users for different roles:

| Username | Password | User Type |
|----------|----------|-----------|
| doctor   | d        | Doctor    |
| doctor2  | d        | Doctor    |
| pharmacist | p      | Pharmacist|
| md       | md1      | Medicine Distributor |
| a-cse    | p        | Patient (Employee) |
| b-cse    | p        | Patient (Employee) |
| 2007331039 | mokarrom | Patient (Student) |
| 2007331023 | p      | Patient (Student) |

## Features

### User Roles
- **General Users (Patients)**: Students and Staff
- **Administrative Users**: Doctors, Pharmacists, Store Officers, Medicine Distributors

### Core Functionality
- Computerized patient management system
- Patient diagnosis and test tracking
- Prescription management with medication instructions
- Medicine stock management (central-store and sub-store)
- Stock ledger entry and transfer
- Medicine delivery tracking
- Billing reports for employees
- Report generation
- Password recovery via hint questions

## Configuration

### Database Configuration
Edit `src/main/java/medicalcenter/database.java`:
```java
private String dbName = "central_db";
private String userName = "root";      // Your MySQL username
private String password = "admin";      // Your MySQL password
private String host = "localhost";
```

### Connection Pool Settings
The HikariCP connection pool is configured with:
- Maximum pool size: 10 connections
- Minimum idle: 5 connections
- Connection timeout: 30 seconds
- Idle timeout: 10 minutes
- Max lifetime: 30 minutes

Adjust these in the `database.java` static initializer if needed.

## Modernization Details

### Jakarta EE Migration
All servlets have been migrated from `javax.servlet` to `jakarta.servlet`:
- Updated imports from `javax.servlet.*` to `jakarta.servlet.*`
- Added SLF4J logging to all servlets
- Maintained backward compatibility with existing functionality

### Database Layer Modernization
- Replaced direct `DriverManager` with HikariCP connection pool
- Updated MySQL JDBC driver from `com.mysql.jdbc.Driver` to `com.mysql.cj.jdbc.Driver`
- Added connection pooling for better performance
- Added proper resource management and logging

### Frontend Modernization
- Updated jQuery from 1.4.2 to 3.7.1 via CDN
- Added Bootstrap 5.3.2 for responsive design
- Updated jQuery UI to 1.13.2
- Enhanced CSS with responsive design and modern styling
- Updated login page with modern UX patterns

### Build System Migration
- Converted from NetBeans project to Maven
- Added dependency management via pom.xml
- Configured Maven Compiler Plugin for Java 21
- Added Maven WAR Plugin for deployment
- Included Jetty plugin for development

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running
- Verify database credentials in `database.java`
- Check if `central_db` database exists
- Verify MySQL port (default: 3306)

### Jakarta EE Compatibility
- Ensure your servlet container supports Jakarta EE 10 (Tomcat 10+, Jetty 11+)
- Older containers (Tomcat 9 and below) use `javax.servlet` and won't work

### Build Issues
- Ensure Java 21 is installed: `java -version`
- Ensure Maven is installed: `mvn -version`
- Clean and rebuild: `mvn clean install`

## Original Project

This modernized version is based on the original Medical Center Management System developed for Shahjalal University of Science & Technology (SUST) Medical Center. The original project was developed in 2011 using Java 6, NetBeans, and traditional servlet technologies.

## License

This project was developed for SUST Medical Center. Please refer to the original documentation for licensing information.

## Support

For issues related to:
- **Modernization**: Check this README and modernization notes
- **Original Functionality**: Refer to `docs/SEonMedicalCenter.pdf` for original technical documentation
- **Database Issues**: Refer to `databse/DBQuery.sql` for database procedures and triggers
