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

## Endpoints

### 1. Home Endpoint

- **URL**: `GET /`

- **Description**: A public endpoint that serves as the entry point to the API. It does not require authentication and is accessible to all users.

- **Response**:

    - **Status**: `200 OK`

    - **Body**:

      ```json
      "Welcome to the public API!"
      ```

- **Use Case**: Provides a welcome message to verify the API is running.

### 2. Public Endpoint

- **URL**: `GET /public`

- **Description**: A public endpoint that does not require authentication. It is accessible to anyone without an `Authorization` header or login.

- **Response**:

    - **Status**: `200 OK`

    - **Body**:

      ```json
      "This is a public endpoint."
      ```

- **Use Case**: Demonstrates a publicly accessible resource, useful for unauthenticated users or testing.

### 3. Private Endpoint

- **URL**: `GET /private`

- **Description**: A protected endpoint that requires the user to be authenticated via GitHub OAuth2 login. After successful login, Spring Security populates the `Principal` object with the user’s GitHub username (from the `login` attribute of the user info response).

- **Authentication**:

    - Users are redirected to GitHub’s authorization page (`https://github.com/login/oauth/authorize`) if not authenticated.
    - After approving the app, GitHub redirects back to the application’s `redirect-uri` with an authorization code.
    - Spring exchanges the code for an access token at `https://github.com/login/oauth/access_token` and fetches user details from `https://api.github.com/user`.
    - The `Principal` object (an `OAuth2User`) contains attributes like `login` (GitHub username).

- **Response**:

    - **Status**: `200 OK`

    - **Body**:

      ```json
      "Welcome <username>, this is a protected endpoint."
      ```

      Example:

      ```json
      "Welcome johndoe, this is a protected endpoint."
      ```

    - **Error Cases**:

        - **Unauthenticated User**: Redirects to GitHub’s login page (returns `302 Found` with a `Location` header).
        - **Invalid Session**: If the session is invalid or expired, redirects to the login page.

- **Use Case**: Provides access to protected resources for authenticated users, demonstrating secure access control.

### 4. User Endpoint

- **URL**: `GET /user`

- **Description**: A protected endpoint that returns the `Principal` object representing the authenticated user. It requires the user to be authenticated via GitHub OAuth2 login.

- **Authentication**:

    - Same as the `/private` endpoint: users must log in via GitHub, and the `Principal` is populated with user details.
    - The `Principal` is an `OAuth2User` containing attributes from GitHub’s user info endpoint (e.g., `login`, `id`, `name`).

- **Response**:

    - **Status**: `200 OK`

    - **Body**: The `Principal` object, serialized as JSON with user details.

    - **Error Cases**:

        - **Unauthenticated User**: Redirects to GitHub’s login page (`302 Found`).
        - **Invalid Session**: Redirects to the login page.

- **Use Case**: Allows clients to retrieve detailed information about the authenticated user, such as their GitHub profile data.

## Authentication Flow

- **Public Endpoints (**`/` **and** `/public`**)**:

    - Configured in the `SecurityFilterChain` to permit all requests (`.requestMatchers("/", "/public").permitAll()`).
    - No authentication or `Authorization` header is required.

- **Protected Endpoints (**`/private` **and** `/user`**)**:

    - Require authentication via GitHub OAuth2 login.
    - If the user is not authenticated, Spring Security redirects them to GitHub’s authorization page.
    - After successful login, Spring Security maintains a session, and the `Principal` is available to the controller methods.
    - The `Principal` (an `OAuth2User`) contains user attributes fetched from `https://api.github.com/user`.

## Example Requests

1. **Home Endpoint**:

   ```bash
   curl http://localhost:8055/
   ```

   Response: `"Welcome to the public API!"`

2. **Public Endpoint**:

   ```bash
   curl http://localhost:8055/public
   ```

   Response: `"This is a public endpoint."`

3. **Private Endpoint** (requires authentication):

    - First, access via a browser or client: `http://localhost:8055/private`

    - If unauthenticated, you’re redirected to GitHub’s login page.

    - After logging in and approving the app, you’re redirected back, and the response is:

      ```json
      "Welcome johndoe, this is a protected endpoint."
      ```

    - With `curl` (using a session cookie or testing after login):

      ```bash
      curl --cookie "SESSION=<session-id>" http://localhost:8055/private
      ```

4. **User Endpoint** (requires authentication):

    - Access via a browser or client: `http://localhost:8055/user`

    - With `curl` (after login):

      ```bash
      curl --cookie "SESSION=<session-id>" http://localhost:8055/user
      ```

This documentation provides a clear understanding of how each endpoint functions and how authentication is handled in the API.