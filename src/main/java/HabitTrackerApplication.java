/*
TO RUN PROGRAM:

- Verify Maven is installed
- to run spring boot backend server, run 'mvn spring-boot:run'
- runs on localhost:8080

*/

//main entry point

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HabitTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HabitTrackerApplication.class, args);
    }
}
