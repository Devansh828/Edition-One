# Edition One - Newspaper & Magazine Distribution Management System

**Edition One** is a JavaFX-based desktop application designed to manage the operations of a newspaper and magazine distribution business. It provides a complete suite of tools for managing hawkers (delivery personnel), customers, delivery areas, paper catalogs, billing, and payment collection — all backed by a MySQL database.

---

## Features

### Authentication
- **Admin Login** with password stored in a local `password.txt` file
- Secure credential validation before accessing the dashboard

### Dashboard
- Centralized admin dashboard with quick-access buttons to all modules
- Modular window-based navigation for each feature

### Paper Master
- Manage the catalog of newspapers and magazines
- Add, update, and view paper details (name, language, price)

### Areas Management
- Define and manage delivery areas/zones
- Assign areas to hawkers and customers

### Hawkers Management
- Register and manage hawkers (delivery personnel)
- Track hawker assignments to specific areas
- Maintain hawker contact and personal details

### Customers Management
- Register new customers with subscription details
- Assign customers to hawkers and delivery areas
- Manage customer profiles and subscription preferences

### Billing
- Generate monthly bills for customers based on their subscriptions
- Calculate amounts using paper prices and subscription duration
- Automatic date difference calculation for billing periods

### Bill Board
- View all generated bills in a consolidated table
- Track bill statuses and payment history

### Bill Collector
- Record and track bill payments from customers
- Monitor pending and collected amounts
- Payment status management

### Show Papers
- Display all available newspapers and magazines
- Quick reference for pricing and language details

### Customers Board
- Comprehensive overview of all registered customers
- Filter and search customer records
- View customer subscription and billing summaries

### Utilities
- **Date Difference Calculator** — utility to compute days between two dates for billing calculations
- **Error Message Handler** — centralized error display dialogs

---

## Tech Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 24 | Core programming language |
| JavaFX | 22-ea+11 | Desktop GUI framework |
| FXML | — | Declarative UI layout |
| ControlsFX | 11.2.1 | Enhanced UI controls |
| MySQL Connector/J | Latest | Database connectivity |
| JDBC | — | Database operations |
| Maven | 3.x | Build automation |
| JUnit | 5.10.2 | Unit testing |

---

## Project Structure

```
Edition-One/
├── src/
│   └── main/
│       ├── java/com/example/paperproject/
│       │   ├── HelloApplication.java          # Application entry point
│       │   ├── HelloController.java           # Default controller
│       │   ├── JDBC/
│       │   │   └── MySQLconnector.java        # Database connection handler
│       │   ├── adminlogin/
│       │   │   └── AdminLoginController.java  # Login screen controller
│       │   ├── admindashboard/
│       │   │   ├── AdminDashboardController.java   # Main dashboard
│       │   │   └── AdminDashboardController1.java  # Alternate dashboard
│       │   ├── areas/
│       │   │   └── AreasController.java       # Area management
│       │   ├── billboard/
│       │   │   ├── BillBoardController.java   # Bill viewing
│       │   │   └── BillsBean.java             # Bill data model
│       │   ├── billcollector/
│       │   │   ├── BillCollectorController.java # Payment collection
│       │   │   └── UserBillBean.java          # User bill data model
│       │   ├── billing/
│       │   │   └── BillingController.java     # Bill generation
│       │   ├── customers/
│       │   │   └── CustomersController.java   # Customer management
│       │   ├── customersboard/
│       │   │   ├── CustomersboardController.java # Customer overview
│       │   │   └── CustomersBean.java         # Customer data model
│       │   ├── datedifference/
│       │   │   └── DateDifference.java        # Date calculation utility
│       │   ├── hawkers/
│       │   │   └── HawkersController.java     # Hawker management
│       │   ├── papermaster/
│       │   │   └── PaperMasterController.java # Paper catalog management
│       │   ├── showerrormsg/
│       │   │   └── ShowErrorMsg.java          # Error dialog utility
│       │   └── showpapers/
│       │       ├── ShowPapersController.java  # Paper listing
│       │       └── PapersBean.java            # Paper data model
│       └── resources/com/example/paperproject/
│           ├── hello-view.fxml
│           ├── adminlogin/AdminLoginView.fxml
│           ├── admindashboard/AdminDashboard.fxml
│           ├── areas/AreasView.fxml
│           ├── billboard/BillBoardView.fxml
│           ├── billcollector/BillCollectorView.fxml
│           ├── billing/BillingView.fxml
│           ├── customers/CustomersView.fxml
│           ├── customersboard/CustomersboardView.fxml
│           ├── hawkers/HawkersView.fxml
│           ├── papermaster/PaperMasterView.fxml
│           └── showpapers/ShowPapersView.fxml
├── pom.xml                          # Maven configuration
├── password.txt                     # Admin password file
└── README.md
```

---

## Database Setup

### MySQL Configuration

The application connects to a local MySQL database named `javaProject`.

**Connection Details** (from `JDBC/MySQLconnector.java`):
- **URL**: `jdbc:mysql://localhost/javaProject`
- **Username**: `root`
- **Password**: `Devansh@2006`

> Update these credentials in `MySQLconnector.java` to match your local MySQL setup.

### Required Tables

Based on the application modules, the following tables are expected in the database:

| Table | Purpose |
|-------|---------|
| `papers` | Newspaper/magazine catalog |
| `areas` | Delivery areas/zones |
| `hawkers` | Hawker/delivery personnel records |
| `customers` | Customer subscription records |
| `bills` | Generated bills |
| `bill_collections` | Payment collection records |

> Note: SQL schema scripts are not included in the repository. You may need to create these tables manually based on the application queries.

---

## Prerequisites

- **JDK 24** or compatible version
- **Maven 3.8+**
- **MySQL Server** (running locally)
- **MySQL Connector/J** (handled by Maven)

---

## Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/Devansh828/Edition-One.git
cd Edition-One
```

### 2. Configure Database
1. Start your MySQL server
2. Create the database:
   ```sql
   CREATE DATABASE javaProject;
   ```
3. Update database credentials in:
   ```
   src/main/java/com/example/paperproject/JDBC/MySQLconnector.java
   ```

### 3. Update Admin Password
Edit `password.txt` in the project root to set your admin login password.

### 4. Build & Run
```bash
# Compile and run using Maven
mvn clean javafx:run
```

Or using the Maven wrapper:
```bash
./mvnw clean javafx:run      # Linux/Mac
mvnw.cmd clean javafx:run    # Windows
```

---

## Application Flow

```
┌─────────────────┐
│  Admin Login    │  ← password.txt authentication
│   (600×400)     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Admin Dashboard │  ← Central hub with module buttons
│   (690×700)     │
└────────┬────────┘
         │
    ┌────┴────┬────────┬────────┬────────┬────────┬────────┬────────┬────────┐
    ▼         ▼        ▼        ▼        ▼        ▼        ▼        ▼
┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐
│ Paper │ │ Areas │ │Hawkers│ │Customers│ │Billing│ │Bill   │ │Bill   │ │Show   │
│Master │ │       │ │       │ │        │ │       │ │Board  │ │Collector│ │Papers │
└───────┘ └───────┘ └───────┘ └───────┘ └───────┘ └───────┘ └───────┘ └───────┘
```

---

## Module Screens

| Module | Dimensions | Description |
|--------|-----------|-------------|
| Admin Login | 600 × 400 | Username/password authentication |
| Admin Dashboard | 690 × 700 | Navigation hub for all modules |
| Paper Master | 600 × 400 | Manage newspaper/magazine catalog |
| Areas | Auto | Define delivery zones |
| Hawkers | 673 × 570 | Manage delivery personnel |
| Customers | Auto | Customer registration & management |
| Billing | Auto | Generate customer bills |
| Bill Board | Auto | View all bills |
| Bill Collector | Auto | Record payments |
| Show Papers | Auto | Display paper catalog |
| Customers Board | Auto | Customer overview panel |

---

## Data Models

### PapersBean
```java
String paper;    // Paper name
String price;    // Price
String language; // Language
```

### BillsBean
```java
// Bill record with customer, amount, date, and status fields
```

### UserBillBean
```java
// User-specific bill tracking for collection
```

### CustomersBean
```java
// Customer profile with subscription and contact details
```

---

## Key Utilities

### DateDifference
Calculates the number of days between two `LocalDate` objects using `ChronoUnit.DAYS.between()` — used for computing subscription billing periods.

### ShowErrorMsg
Centralized error dialog display for consistent user feedback across all modules.

### MySQLconnector
Singleton-style database connection provider using JDBC.

---

## Future Improvements

- [ ] Add SQL schema export to the repository
- [ ] Implement password encryption instead of plain text
- [ ] Add user roles (admin vs. operator)
- [ ] Export bills to PDF
- [ ] Add data validation and input sanitization
- [ ] Implement backup/restore functionality
- [ ] Add reporting and analytics dashboard
- [ ] Support for multiple pricing plans
- [ ] SMS/email notifications for bill reminders
- [ ] Docker containerization for easy deployment

---

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## License

This project is open-source and available under the [MIT License](LICENSE).

---

## Author

**Devansh** — [GitHub](https://github.com/Devansh828)

---

## Acknowledgments

- JavaFX Documentation
- ControlsFX Library
- MySQL Documentation
