# eSignet SingPass Plugin

This plugin integrates eSignet with SingPass for authentication and KYC (Know Your Customer) processes. It implements the `Authenticator` interface provided by eSignet and uses SingPass APIs for user authentication, OTP, and KYC data exchange.

---

## **Prerequisites**

1. **Java Development Kit (JDK)**:
   - Install JDK 11 or later.
   - Verify installation:
     ```powershell
     java -version
     javac -version
     ```

2. **Apache Maven**:
   - Install Maven for building the project.
   - Verify installation:
     ```powershell
     mvn -v
     ```

3. **SingPass API Credentials**:
   - Obtain API credentials (e.g., client ID, client secret) from SingPass.
   - Ensure you have access to SingPass sandbox or production endpoints.

4. **VSCode**:
   - Install Visual Studio Code.
   - Install the following extensions:
     - **Java Extension Pack** (for Java development).
     - **Maven for Java** (for Maven support).

---

## **Project Structure**
```
esignet-singpass-plugin/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/example/esignet/audit/
│ │ │ │ └──  CustomAuditPlugin.java
│ │ │ └── com/example/esignet/config/
│ │ │ │ └──  PluginConfig.java
│ │ │ └── com/example/esignet/singpass/
│ │ │ │ ├── SingPassAuthenticator.java
│ │ │ │ ├── SingPassClient.java
│ │ │ │ └──  SingPassResponse.java
│ │ └── resources/
│ └── test/
│ └── java/
│ └── com/example/esignet/singpass/
│ └── SingPassAuthenticatorTest.java
├── pom.xml
└── README.md
```

## **Building the Project**

1. **Clone the Repository**:
   ```powershell
   git clone https://github.com/your-repo/esignet-singpass-plugin.git
   cd esignet-singpass-plugin
### Build the Project:

Use Maven to compile and package the project into a JAR file:

```
mvn clean package
```
The JAR file will be generated in the target/ directory.

## Running Tests
### Unit Tests:

Run the unit tests using Maven:

```
mvn test
```
### Test File:

The test file (`SingPassAuthenticatorTest.java`) is located in the `src/test/java/com/example/esignet/singpass/` directory.

It uses JUnit to test the SingPassAuthenticator class.

## Deploying the Plugin
### Add the JAR to eSignet:

Copy the generated JAR file (target/esignet-singpass-plugin-1.0-SNAPSHOT.jar) to the eSignet plugins directory.

### Configure eSignet:

Update eSignet configuration to use the SingPassAuthenticator class for authentication.

## Testing the Plugin
### Run eSignet:

Start the eSignet server and verify that the plugin is loaded.

### Test Authentication:

Use eSignet's authentication endpoint to test the SingPass integration.

## Dependencies
- Apache HttpClient: For making HTTP requests to SingPass APIs.
- Jackson: For JSON serialization and deserialization.
- JUnit: For unit testing.