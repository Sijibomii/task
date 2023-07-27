package com.task.server.seed;

import jakarta.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) { 
        // Define your table names
        String[] tables = new String[]{"users", "organizations","teams","projects","categories",
        "tasks","task_comment","teams_boards","task_media","channels", "permissions","favourite_boards", "tags", "project_boards"};

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
