# 🏦 Bank Management System

A console-based **Bank Management System** built using **Core Java**. The application allows users to create and manage bank accounts, perform transactions, transfer money, and store account data using file handling.

---

## 🚀 Features

- 🏦 Create Account
- 👀 View All Accounts
- 🔍 Search Account
- 💰 Deposit Money
- 💸 Withdraw Money
- 🔄 Transfer Money
- 🗑️ Delete Account
- 💳 Check Account Balance
- ⚠️ Exception Handling
- 🔢 Duplicate Account Number Validation
- 💾 File-Based Data Persistence
- 🔄 Automatically Save Account Changes
- 📂 Load Account Data When Program Starts
- 💰 Insufficient Balance Validation
- ✅ Transaction Amount Validation

---

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList
- File Handling
- Exception Handling
- Git
- GitHub

---

## 📚 Concepts Used

- Classes and Objects
- Constructors
- Encapsulation
- Getters and Setters
- ArrayList
- Methods
- Loops
- Conditional Statements
- Exception Handling
- File Reading
- File Writing
- Data Persistence
- CRUD Operations

---

## 📂 Project Structure

```text
Bank-Management-System/
│
├── Main.java
├── Bank.java
├── Account.java
├── README.md
└── .gitignore
```

---

## 💾 Data Persistence

The application uses **File Handling** to store account information.

Account data is saved in a file and loaded automatically when the application starts.

### Example stored data:

```text
101,Sahil,Savings,20000.0
102,Rahul,Current,15000.0
```

This allows account data to remain available even after the program is closed.

---

## ⚙️ How It Works

### Create Account

The user provides:

- Account Number
- Account Holder Name
- Account Type
- Initial Balance

The system checks whether the account number already exists before creating the account.

### Deposit Money

The specified amount is added to the account balance and the updated data is automatically saved.

### Withdraw Money

The system checks whether the account has sufficient balance before withdrawing the requested amount.

### Transfer Money

Money can be transferred between two different accounts.

The system checks:

- Sender account exists
- Receiver account exists
- Transfer amount is valid
- Sender has sufficient balance
- Sender and receiver are different accounts

### Delete Account

The selected account is removed from the system and the updated account data is saved.

---

## ⚠️ Exception Handling

The application handles invalid user input using Java's `InputMismatchException`.

Examples:

- Entering text instead of a number
- Invalid account number input
- Invalid transaction amount

This prevents the program from crashing due to incorrect input.

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Sahilsthr/Bank-Management-System.git
```

### 2. Navigate to the project directory

```bash
cd Bank-Management-System
```

### 3. Compile the Java files

```bash
javac Main.java Bank.java Account.java
```

### 4. Run the application

```bash
java Main
```

---

## 🖥️ Menu

```text
=== Bank Management System ===

1. Create Account
2. View Account
3. Search Account
4. Deposit Money
5. Withdraw Money
6. Delete Account
7. Check Balance
8. Transfer Money
9. Exit
```

---

## 🔮 Future Improvements

- 🗄️ MySQL Database Integration using JDBC
- 📜 Transaction History
- 🔐 User Authentication
- 👤 Customer Login
- 🏧 ATM-like Interface
- 📊 Account Statements
- 🌐 REST API using Spring Boot
- 🔑 JWT Authentication
- 🗃️ Hibernate/JPA Integration

---

## 👨‍💻 Author

**Sahil Suthar**

GitHub: https://github.com/Sahilsthr

---

⭐ If you found this project useful, consider giving the repository a star!
