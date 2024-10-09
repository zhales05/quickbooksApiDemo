# QuickBooks API Demo

This project is a demo application for integrating with the QuickBooks API using OAuth2.

## Project Setup

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/zhales05/quickbooksApiDemo.git
    cd quickbooksApiDemo
    ```

2. **Copy the `application.properties.template` file to `application.properties`**:
    ```sh
    cp src/main/resources/application.properties.template src/main/resources/application.properties
    ```

3. **Fill in the required details in `application.properties`**:
    ```properties
    spring.application.name=quickbooksApi
    OAuth2AppClientId=<Your OAuth2 App Client Id>
    OAuth2AppClientSecret=<Your OAuth2 App Client Secret>
    OAuth2AppRedirectUri=http://localhost:8080/oauth2redirect
    IntuitAccountingAPIHost=https://sandbox-quickbooks.api.intuit.com
    spring.thymeleaf.cache=false
    server.port=8080
    ```
    - Replace `<Your OAuth2 App Client Id>` and `<Your OAuth2 App Client Secret>` with the OAuth2 App Client Id and Client Secret you get from Intuit Developer.
   
4. **Build the project using Maven**:
    ```sh
    mvn clean install
    ```

5. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```

## Usage

Once the application is running, you can access it at `http://localhost:8080`.
To test the API integeration:
1. Go to http://localhost:8080/connectToQuickbooks
2. Login to Intuit and grant access
3. Go to http://localhost:8080/getCompanyInfo to get the company information
