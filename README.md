# Spring Boot OAuth2 Demo

## Setup Instructions

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