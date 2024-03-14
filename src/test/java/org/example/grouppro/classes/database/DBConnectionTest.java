package org.example.grouppro.classes.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    public void testConnection() {
        Connection connection = DBConnection.getConnection();
        assertNotNull(connection, "Connection shouldn't be null");
    }

}