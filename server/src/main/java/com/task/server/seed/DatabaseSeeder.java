package com.task.server.seed;

import jakarta.persistence.Query;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;


@Component
public class DatabaseSeeder implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) {  
        // Define your table names
        String[] tables = new String[]{"users", "organizations","teams","projects", "user_boards", "project_boards", "categories", "status", 
        "tasks","task_comment","task_media","channels","favourite_boards", "tags",  "chat"};

        // Check if the tables are empty
        for (int i=0; i<tables.length; i++ ){
            if (isTableEmpty(tables[i])) { 
                    // Execute SQL scripts to seed the database
                    System.out.println("SEEDING....");
                    // executeSeedSQLScripts();
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

    // private void executeSeedSQLScripts() {
    //     // Replace the content of seedScript with your actual SQL script to insert data
    //     String seedScript = "INSERT INTO your_table_name_1 (column1, column2) VALUES (value1, value2);"
    //             + "INSERT INTO your_table_name_2 (column3, column4) VALUES (value3, value4);";

    //     jdbcTemplate.execute(seedScript);
    // }
}
