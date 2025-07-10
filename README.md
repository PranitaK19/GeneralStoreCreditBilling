
# 🧾 General Store Credit Billing System (gs-billing)

A simple Java console-based application to manage a general store's customer credit and payment system using PostgreSQL.

## 📌 Features

- Add new customers
- Record credit (उधारी) entries
- Record payment entries
- View all pending dues
- View complete customer transaction history

## ⚙️ Technologies Used

- Core Java (OOP Concepts)
- JDBC (Java Database Connectivity)
- PostgreSQL
- Console-based UI

## 🧩 PostgreSQL JDBC Dependency

If you are using Maven, add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```

Or download the `.jar` manually from [Maven Repository](https://mvnrepository.com/artifact/org/postgresql/postgresql).

## 📂 Project Structure

```
gs-billing/
├── generalstore/
│   ├── Main.java              - Main menu and user interaction
│   ├── customerDAO.java       - Add customer, get ID by name
│   ├── CreditDAO.java         - Insert credit entries into database
│   ├── PaymentDAO.java        - Insert payment entries into database
│   ├── ReportService.java     - Show pending dues and customer history
│   └── DBConnection.java      - JDBC connection to PostgreSQL
│
└── README.md                  - Project documentation and instructions
```

## 🗃️ Database Tables (PostgreSQL)

```sql
-- customer table
CREATE TABLE customer (
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(100) UNIQUE NOT NULL
);

-- credit_entry table
CREATE TABLE credit_entry (
    id           SERIAL PRIMARY KEY,
    customer_id  INT REFERENCES customer(id),
    item         VARCHAR(100),
    amount       DOUBLE PRECISION,
    date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- payment_entry table
CREATE TABLE payment_entry (
    id           SERIAL PRIMARY KEY,
    customer_id  INT REFERENCES customer(id),
    paid_amount  DOUBLE PRECISION,
    date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## ▶️ How to Run

1. Create the PostgreSQL database and tables using the above SQL.
2. Update DBConnection.java with your PostgreSQL credentials.
3. Compile the project:

```bash
javac generalstore/*.java
```

4. Run the project:

```bash
java generalstore.Main
```

---

## 👩‍💻 Console Output  
<img width="407" height="236" alt="image" src="https://github.com/user-attachments/assets/6f66e1c0-819a-4be7-b555-370a4784bee2" />



## 👩‍💻 Author Contact

**Name:** Pranita Pandurang Khendkar  
**Email:** khendkarpranita@gmail.com  
**GitHub:** https://github.com/PranitaK19  

---

🎉 Done! You now have a functional credit billing system for a general store.
