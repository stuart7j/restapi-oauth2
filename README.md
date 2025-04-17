# Spring Boot OAuth2 Demo

## Project Setup

### Step 1: Clone the Repository

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/stuart7j/restapi-oauth2.git
   cd restapi-oauth2

### Step 2: Create a GitHub OAuth App

1. **Create a GitHub OAuth App**:
    - Go to GitHub > Settings > Developer settings > OAuth Apps > New OAuth App.
    - Set:
        - Application name: e.g., "Spring Boot Local Dev".
        - Homepage URL: `http://localhost:8055`.
        - Authorization callback URL: `http://localhost:8055/login/oauth2/code/github`.
    - Copy the `Client ID` and `Client Secret`.

2. **Set Environment Variables**:
    - Create a `.env` file in the project root with:
    - GITHUB_CLIENT_ID=your_actual_client_id
    - GITHUB_CLIENT_SECRET=your_actual_client_secret

### Step 3: Build and Run the Spring Boot Application

The project includes a Maven Wrapper (`./mvnw`), so you don't need to install Maven.

1. **Run the Application**: From the project root, execute:

   ```bash
   ./mvnw spring-boot:run
   ```

   This command:

   - Downloads dependencies (if not already cached).
   - Compiles the code.
   - Starts the Spring Boot application.


2. **Verify the Application is Running**: Once started, the application is available at `http://localhost:8059`. Test the API using a tool like Postman or cURL:

   ```bash
   curl http://localhost:8055/
   ```

   This returns a message: Welcome to the public API!