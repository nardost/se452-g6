# G6 Utilities

## A Utility Services Management & Billing System

### SE452: Object-Oriented Enterprise Computing

#### GROUP 6
1. Matthew James (matthewmorelandjames@gmail.com)
2. Christian Kleinvehn (ckleinvehn@gmail.com)
3. Abhishek Ram Muthukrishnan (ram.abishek@gmail.com)
4. Pedro Rebollar (prebolla15@gmail.com)
5. Nardos Tessema (nardos.tessema@gmail.com)

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

2. Set the active profile via an environment variable

   ```$ export SPRING_PROFILES_ACTIVE=dev``` 
   
   On Windows, open the command line tool and run:
    
   ```
   C:\> CD C:\Path\To\Project\Directory
   C:\Path\To\Project\Directory> SET SPRING_PROFILES_ACTIVE=dev
   ```

3. Build

   ```$ mvn clean package```

4. Execute thee jar file

   ```$ java -jar ui/target/ui-1.0-SNAPSHOT.jar```

5. Run the application

   Browse to ```http://localhost:8080``` with Chrome/Firefox 