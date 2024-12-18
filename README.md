# Quiz Application

## Project Overview

A very basic quiz - the backend API provides three main endpoints for quiz related operations:

- **`/quiz/start`**: Starts a new quiz and assigns random questions to a specific quiz.
- **`/quiz/get/{id}`**: Fetches the quiz questions for a specific quiz by its ID.
- **`/quiz/submit/{id}`**: Submits the answers for a specific quiz, calculating the score and providing the results.

The application uses an **H2 database** for storing quiz data, including questions and quiz submissions.


## Note

- The application uses Java 17  LTS version, make sure its installed on your machine
- The default port for the application is 8080. You can access the application at http://localhost:8080.
- The H2 database console is available at http://localhost:8080/h2-console and it is using the default username (sa) and no password.

## Running the Project



### 1. **Running the JAR file**

If you have already built the project and generated the JAR file, you can run the application by following these steps:

1. **Open Command Prompt** (`cmd`).
2. **Navigate to the directory** where the JAR file is located and run it
   
   Example:
   ```bash
   cd C:\path\to\your\jarfile

   java -jar <nameOfJAR>
    ```
### 2. **Running from source code**


1. Clone the repository or download the project files

2. Make sure you have **Java** 17 installed on your machine

3. Open a terminal in the project directory and run the following command to build and start the application:

   ```bash
   ./mvnw spring-boot:run
   ```

### 3. **Test from Postman**

You can test the quiz app using Postman by sending requests to the following endpoints:

1. Start a Quiz
 - Method - **GET**
 - url - /quiz/start

2. Get Quiz Questions
 - Method - **GET**
 - url - /quiz/get/1
 - Response : 

 ```JSON
 [
  {
    "id": 1,
    "questionTitle": "What is the capital of France?",
    "option1": "Rome",
    "option2": "Paris",
    "option3": "Berlin",
    "option4": "Madrid",
    "category":"Easy"

  },
  ...
]
```
3. Submit Quiz Answers
 - Method - **POST**
 - url - /quiz/submit/{id}




### H2 Database

The application uses an H2 in-memory database for storing quiz data. It is automatically configured and initialized when the application starts. No external database configuration is required and data resets when the application is restarted.
    

