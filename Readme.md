# G6 Utilities [![Build Status](https://travis-ci.org/nardost/se452-g6.svg?branch=master)](https://travis-ci.org/nardost/se452-g6)

## A Utility Services Management & Billing System

### SE452: Object-Oriented Enterprise Computing

#### GROUP 6
1. Matthew James (matthewmorelandjames@gmail.com)
2. Christian Kleinvehn (ckleinvehn@gmail.com)
3. Pedro Rebollar (prebolla15@gmail.com)
4. Nardos Tessema (nardos.tessema@gmail.com)

### Project Modules

```
g6-utilities
    ├── accounts
    ├── billing
    ├── facilities
    ├── on-premise-monitor-simulator
    ├── payment
    ├── payment-processor-simulator
    ├── service-delivery-and-monitoring
    └── ui
```

### Running Instructions (Command Line)

1. Go to the project root directory

2. Set environment variables

    ## List of Environment Variables
    ```
     1. SPRING_PROFILES_ACTIVE
     2. MONGODB_AUTHENTICATION_DB_LOCAL
     3. MONGODB_HOST_LOCAL
     4. MONGODB_PORT_LOCAL
     5. MONGODB_DATABASE_LOCAL
     6. MONGODB_USERNAME_LOCAL
     7. MONGODB_PASSWORD_LOCAL
     8. POSTGRES_URL_AWS
     9. POSTGRES_PORT_AWS
    10. POSTGRES_DB_AWS
    11. POSTGRES_USERNAME_AWS
    12. POSTGRES_PASSWORD_AWS
    13. MONGODB_USERNAME_ATLAS
    14. MONGODB_PASSWORD_ATLAS
    15. MONGODB_CLUSTER_URI_ATLAS
    ```

   Examples:
   
   #### Setting them individually
   
   ```$ export SPRING_PROFILES_ACTIVE=dev``` 
   
   In Windows, open the command line tool and run:
    
   ```
   C:\Path\To\Project\Directory> SET SPRING_PROFILES_ACTIVE=dev
   ```
   
   #### Using a configuration script (not added to source code repository)
   
   Create script file & export the env variables
   
   ```$ vim .config/config.sh```
   
   In Windows, create batch file & set the env variables: 
   
   ```C:\Path\To\Project\Directory>notepad .config\config.bat```
   
   Run script
   
   ```
   $ source ./.config/config.sh
   ```
   
   In Windows, run batch file
   
   ```
   C:\Path\To\Project\Directory>.config\config.bat
   ```

3. Build

   ```$ mvn clean package```

4. Execute the jar file

   ```$ java -jar ui/target/ui-1.0-SNAPSHOT.jar```

5. Run the application

   Browse to ```http://localhost:8080``` with Chrome/Firefox 
