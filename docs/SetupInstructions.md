1. Clone repository - gitclone https://github.com/mcveigh66/Advanced-Programming-Final-Sprint.git
cd advanced-programming-final-sprint

2. Set up databse 
Open PostgreSQL and create the database: CREATE DATABASE gym_db; 
Run the repository cript schema.sql inside gym_db 

3. Update src/main/java/gym/config/DatabaseConnection.java with your local PostgreSQL user and password.

4. Build with maven 

Bash: mvn clean compile 

5. Run application 

Bash: mvn exec:java -Dexec.mainClass="gym.Main"

Dependencies: 

org.postgresql:postgresql:42.7.2: Official PostgreSQL JDBC driver for database connectivity.

org.mindrot:jbcrypt:0.4: One-way salted password hashing library for secure credential storage.