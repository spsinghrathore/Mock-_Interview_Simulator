# 🎯 Mock Interview Simulator

A simple, professional Java-based **Mock Interview Simulator** built with **JDBC** and **SQL**. Designed to help students or job seekers simulate technical interview questions in a console-based interface.

---

## 🚀 Features

- 🧠 Randomized Question Set (Technical & Behavioral)
- 📊 Score Calculation & Feedback
- 💾 MySQL Database Integration using JDBC
- 📁 Modular Java Codebase (Easy to extend)

---

## 🛠 Tech Stack

- Java (JDK 17+)
- JDBC (Java Database Connectivity)
- MySQL or any SQL-based DB
- IntelliJ IDEA (or any Java IDE)

---

## ⚙️ Setup Instructions

1. **Clone this repo**

```bash
git clone https://github.com/spsinghrathore/Mock-_Interview_Simulator.git
cd mock-interview-simulator
````

2. **Import in IntelliJ IDEA**

* Open IntelliJ → `Open Project` → Select the cloned folder.
* Ensure JDK is set in Project Structure.

3. **Setup Database**

* Create a MySQL database: `interview_simulator`
* Run `schema.sql` to create required tables and insert sample data.

4. **Update DB Credentials**

In `DBConnection.java`:

```java
String url = "jdbc:mysql://localhost:3306/interview_simulator";
String user = "your-username";
String password = "your-password";
```

5. **Run the Project**

* Right-click `Main.java` → Run.
* Follow on-screen prompts to start the mock interview!

**Thank You <3**
