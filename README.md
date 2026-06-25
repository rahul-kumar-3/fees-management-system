# Fees Management System

A robust, production-grade Spring Boot REST API backend designed to manage college academic domains, automate student enrollment tracking, and handle multi-installment fee transactions with real-time balance calculations.

🚀 Key Features

* Core CRUD Management: Full lifecycle controls over three foundational relational modules: Courses, Students, and Fees.
* Smart Ledger Auto-Calculation: Advanced transactional logic that maps a student's payment history against their course total fee to auto-generate exact remaining balances without data duplication.
* Custom Financial Reports (DTO Engine): Streamlined endpoints leveraging Java Streams to return isolated lists of outstanding student dues with explicit custom formatting.
* Path-Based Date Range Filtering: High-performance database queries using Spring Data JPA derived query methods to isolate fee receipts between two user-defined dates (`dd-MM-yyyy`).
* Unified API Response Handling: A global, uniform custom `ApiResponse<T>` wrapper ensuring consistent JSON structures for all successful data payloads.
* Centralized Global Exception Architecture: Application-wide error tracking using `@RestControllerAdvice` to gracefully intercept failures (e.g., `StudentNotFoundException`) and return safe HTTP states.

---

🛠️ Tech Stack & Toolkit

* Backend Framework: Spring Boot 3.x (Spring Web, Spring Data JPA)
* Language: Java 17+
* Database: Oracle SQL / SQL*Plus Relational Ecosystem
* Data Boilerplate Reduction: Lombok (`@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`)
* Serialization Management: Jackson Framework JSON Annotations (`@JsonIgnoreProperties`)

---

📐 System Architecture & Data Model

The database maintains strict referential integrity across a clear transactional hierarchy:

[Course] (totalFees)
│
└──► 1-to-Many ──► [Student]
[Student]
│
└──► 1-to-Many ──► [Fees] (paidAmount, remainingAmount)

1. Course Module
Tracks academic structures, core identifiers, enrollment parameters, and specific base price ceilings.
    Fields: `courseId` (PK), `courseCode`, `courseName`, `totalFees`, `durationInMonths`.

2. Student Module
Maintains the profile identity ledger for enrolled individuals, tightly bound to their selected academic course.
    Fields: `studentId` (PK), `admissionNumber`, `firstName`, `lastName`, `email`, `phone`, `dateOfBirth`, `gender`, `address`, `admissionDate`.

3. Fees Module
Acts as the financial receipt ledger tracking independent sequential installments.
    Fields: `feesId` (PK), `receiptNumber`, `paymentDate`, `paidAmount`, `remainingAmount`, `paymentMode`, `remarks`.

---

🔌 API Endpoints Profile

All base requests are versioned and mapped behind the root context prefix: `/api/v1`

📚 Course Operations
| HTTP Method | URI Path | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/courses` | Create and register a new course structure |
| `GET` | `/api/v1/courses` | Fetch all available courses |
| `GET` | `/api/v1/courses/{id}` | Fetch specific course details by ID |
| `PUT` | `/api/v1/courses/{id}` | Modify existing course parameters |
| `DELETE`| `/api/v1/courses/{id}` | Remove a course from the repository |

🎓 Student Operations
| HTTP Method | URI Path | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/{cid}/students` | Register a student under a specific Course ID |
| `GET` | `/api/v1/students` | Retrieve all registered students |
| `GET` | `/api/v1/students/{id}` | Fetch a student profile by their ID |
| `GET` | `/api/v1/students/pending-list` | Get a structured DTO list of students with outstanding balances |
| `PUT` | `/api/v1/students/{id}` | Update student profile metadata details |
| `DELETE`| `/api/v1/students/{id}` | Erase a student profile from the records |

💰 Fee & Transaction Operations
| HTTP Method | URI Path | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/{sid}/fees` | Post a new fee payment installment for a Student ID |
| `GET` | `/api/v1/fees` | Fetch all transactional receipts |
| `GET` | `/api/v1/fees/{id}` | Retrieve individual receipt details by ID |
| `GET` | `/api/v1/fees/{startDate}/{endDate}` | Filter payments within a date range (`dd-MM-yyyy`) |

---

📋 JSON Payload Samples

Successful DTO Response (`GET /api/v1/students/pending-list`)
```json
{
    "data": [
        {
            "studentId": 1,
            "admissionNumber": "ADM-2026-001",
            "firstName": "John",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "phone": "+1234567890",
            "dateOfBirth": "2005-05-15",
            "gender": "Male",
            "address": "123 University Ave, Cityville",
            "admissionDate": "2026-06-01",
            "course": {
                "courseId": 1,
                "courseName": "Computer Science and Engineering",
                "courseCode": "CSE-2026",
                "durationInMonths": 48,
                "totalFees": 400000.0
            },
            "fees": [
                {
                    "feesId": 1,
                    "receiptNumber": "REC-2026-9876",
                    "paymentDate": "2026-06-23",
                    "paidAmount": 50000.0,
                    "remainingAmount": 350000.0,
                    "paymentMode": "Credit Card",
                    "remarks": "First installment paid successfully"
                },
                {
                    "feesId": 2,
                    "receiptNumber": "REC-2026-9876",
                    "paymentDate": "2026-06-23",
                    "paidAmount": 100000.0,
                    "remainingAmount": 300000.0,
                    "paymentMode": "Credit Card",
                    "remarks": "First installment paid successfully"
                },
                {
                    "feesId": 3,
                    "receiptNumber": "REC-2026-9876",
                    "paymentDate": "2026-06-23",
                    "paidAmount": 250000.0,
                    "remainingAmount": 150000.0,
                    "paymentMode": "Credit Card",
                    "remarks": "Third installment paid successfully"
                }
            ]
        },
        {
            "studentId": 2,
            "admissionNumber": "ADM-2026-002",
            "firstName": "Rahul",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "phone": "+1234567890",
            "dateOfBirth": "2005-05-12",
            "gender": "Male",
            "address": "123 University Ave, Cityville",
            "admissionDate": "2026-06-02",
            "course": {
                "courseId": 2,
                "courseName": "Electronics & Communication Engineering",
                "courseCode": "ECE-2026",
                "durationInMonths": 48,
                "totalFees": 800000.0
            },
            "fees": [
                {
                    "feesId": 4,
                    "receiptNumber": "REC-2026-9880",
                    "paymentDate": "2026-06-24",
                    "paidAmount": 100000.0,
                    "remainingAmount": 700000.0,
                    "paymentMode": "Credit Card",
                    "remarks": "First installment paid successfully"
                }
            ]
        }
    ],
    "message": "List of pending payment students.",
    "success": true,
    "timestamp": "2026-06-25T11:37:20.6454336"
}

Handled Error Response (GET /api/v1/students/99)
{
    "success": false,
    "status": 404,
    "error": "Not Found",
    "message": "student with id 99 doesn't exist.",
    "exception": {
        "message": "student with id 99 doesn't exist.",
        "cause": null,
        "localizedMessage": "student with id 99 doesn't exist."
           }
    "timestamp": "2026-06-25T11:40:45.4973094"
}

