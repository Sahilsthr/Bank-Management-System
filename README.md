# 🏦 Bank Management System

A console-based **Bank Management System** built with **Java, JDBC, and MySQL**. It supports core banking operations — account creation, deposits, withdrawals, transfers, and transaction history — with all data persisted in a MySQL database

---

## 📋 Table of Contents

- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [Project Structure](#-project-structure)
- [Database Setup](#-database-setup)
- [Configuration](#-configuration)
- [How to Run](#-how-to-run)
- [Application Menu](#-application-menu)
- [How the Transfer System Works](#-how-the-transfer-system-works)
- [What I Practiced](#-what-i-practiced)
- [Future Improvements](#-future-improvements)
- [Author](#-author)

---

## ✨ Features

| Feature | Description |
|---|---|
| Create Account | Add a new account with duplicate account number validation |
| View All Accounts | List every account stored in the database |
| Search Account | Look up a specific account by account number |
| Deposit Money | Add funds to an existing account |
| Withdraw Money | Remove funds, with balance validation |
| Transfer Money | Move funds between two accounts safely |
| Delete Account | Remove an account from the system |
| Check Balance | View the current balance of an account |
| Transaction History | View a full log of past transactions |
| Input Validation | Prevents invalid or malformed data entry |
| Transaction Handling | Commit/rollback ensures data consistency |

---

## 🛠 Technologies Used

- **Java** – core application logic
- **JDBC** – database connectivity
- **MySQL** – data storage
- **Git & GitHub** – version control

---

## 📁 Project Structure

```
Bank-Management-System/
│
├── Account.java
├── AccountDAO.java
├── Bank.java
├── DatabaseConnection.java
├── Main.java
├── Transaction.java
├── TransactionDAO.java
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── .gitignore
└── README.md
```

---

## 🗄 Database Setup

**1. Create the database:**

```sql
CREATE DATABASE bms;
```

**2. Create the `accounts` table:**

```sql
CREATE TABLE accounts (
    account_no INT PRIMARY KEY,
    account_name VARCHAR(100),
    account_type VARCHAR(50),
    balance DOUBLE
);
```

**3. Create the `transactions` table:**

```sql
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50),
    amount DOUBLE,
    sender_account INT,
    receiver_account INT,
    date_time VARCHAR(50)
);
```

---

## ⚙ Configuration

Open `DatabaseConnection.java` and update the MySQL credentials to match your local setup:

```java
private static final String URL = "jdbc:mysql://localhost:3306/bms";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

> ⚠️ **Never commit your actual MySQL password to GitHub.** Consider using environment variables or a config file excluded via `.gitignore`.

---

## 🚀 How to Run

**Prerequisites:** Java and MySQL installed, with the MySQL server running. The MySQL Connector/J `.jar` is already included in `lib/`.

**1. Compile:**

```bash
javac -cp "lib\mysql-connector-j-26.7.0.jar" *.java
```

**2. Run:**

```bash
java -cp ".;lib\mysql-connector-j-26.7.0.jar" Main
```

> On macOS/Linux, replace `;` with `:` in the classpath (e.g. `java -cp ".:lib/mysql-connector-j-26.7.0.jar" Main`).

---

## 📟 Application Menu

```
=== Bank Management System ===

1. Create Account
2. View Account
3. Search Account
4. Deposit Money
5. Withdraw Money
6. Delete Account
7. Check Balance
8. Transfer Money
9. View Transaction History
10. Exit
```

---

## 🔄 How the Transfer System Works

The money transfer feature uses a **database transaction** to guarantee consistency:

1. Verify the sender account exists.
2. Verify the receiver account exists.
3. Check that the sender has sufficient balance.
4. Deduct the amount from the sender's account.
5. Add the amount to the receiver's account.
6. **Commit** the transaction if both updates succeed.
7. **Rollback** the transaction if anything fails.

This prevents partial transfers where money is deducted from one account without being credited to the other.

---

## 📚 What I Practiced

- Java OOP — classes, objects, constructors, encapsulation
- Exception handling
- JDBC — `PreparedStatement`, `ResultSet`
- MySQL CRUD operations
- Database transactions (commit & rollback)
- Git and GitHub workflow

---

## 🔮 Future Improvements

- [ ] User login system
- [ ] PIN protection
- [ ] Account statements
- [ ] Improved transaction reports
- [ ] Admin functionality
- [ ] GUI version
- [ ] Enhanced security (hashing, encryption)

---

## 👤 Author

**Sahil Suthar**
B.Tech Student — Computer Science Engineering
