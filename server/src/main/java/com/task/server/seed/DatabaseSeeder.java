package com.task.server.seed;

import jakarta.persistence.Query;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;


@Component
public class DatabaseSeeder implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;
// ,"channel"
    @Override
    public void run(String... args) {  
        // Define your table names
        String[] tables = new String[]{"users", "organizations","teams","projects", "user_boards", "project_boards", "categories", "status", 
        "tasks","task_comment","task_media","favourite_boards", "tags"};

        // Check if the tables are empty
        for (int i=0; i<tables.length; i++ ){
            if (isTableEmpty(tables[i])) { 
                    // Execute SQL scripts to seed the database
                    System.out.println("SEEDING....");
                    // executeSeedSQLScripts(tables[i]+".db.sql");
                }
        }
        
    }

    private boolean isTableEmpty(String tableName) {
        System.out.printf("\n TABLE IS %s EMPTY ... \n", tableName);
        String sql = "SELECT COUNT(*) FROM " + tableName;

        Query query = entityManager.createNativeQuery(sql);

        Object result = query.getSingleResult();
        int count;
        if (result != null) {
            count = ((Number) result).intValue();
        } else {
            count = 0;
        }
        return count == 0;
    }

    private void executeSeedSQLScripts(String seedFile) {
        Path filePath = Paths.get(seedFile);

        try {
            // Read the content of the file into a byte array
            byte[] fileBytes = Files.readAllBytes(filePath);

            // Convert the byte array to a string using the appropriate character encoding (e.g., UTF-8)
            String sql = new String(fileBytes, "UTF-8");
            Query query = entityManager.createNativeQuery(sql);

            Object result = query.getSingleResult();

            System.out.printf("\n QUERY SUCCESSFULLY EXECUTED WITH RESULT: ", result);

        } catch (IOException e) {
            // Handle any exceptions that occur during file reading
            e.printStackTrace();
        }
    }
}
