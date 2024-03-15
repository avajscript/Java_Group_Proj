package org.example.grouppro.classes.database.User;

import com.mysql.cj.protocol.Resultset;
import org.example.grouppro.classes.database.DBConnection;
import org.example.grouppro.classes.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends UserDao {

    /**
     * Returns a list of all users in the database
     *
     * @return all users
     */
    @Override
    public List<User> getAllUsers() {
        // declare variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<User> users = null;
        try {
            // create and exectute query to get all users
            conn = DBConnection.getConnection();
            String query = "SELECT * FROM users";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            users = new ArrayList<User>();
            // iterate over each row adding a user to the user list
            while (rs.next()) {
                User user;
                user = getUserByType(rs.getString("studentType"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        } finally {
            // close resources
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return users;
    }

    @Override
    public void createUser(User user) {
        // declare variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // create connection and query
            conn = DBConnection.getConnection();
            String query = "INSERT INTO USERS (firstName, lastName, email, password, userType) " +
                    "VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getUserType());

            // execute insert
            int rowsAffected = pstmt.executeUpdate();

            // check to see if the query worked without errors
            if (rowsAffected > 0) {
                System.out.println("User added successfully");
            } else {
                System.out.println("Failed to add user");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            // close resources
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void updateUser(User user) {
        // declare variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // create connection and query
            conn = DBConnection.getConnection();
            String query = "UPDATE USERS SET firstName = ?, lastName = ?, email = ?, password = ?, userType = ?" +
                    "WHERE email = ? AND passsword = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getUserType());
            pstmt.setString(6, user.getPassword());
            // execute insert
            int rowsAffected = pstmt.executeUpdate();

            // check to see if the query worked without errors
            if (rowsAffected > 0) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("Failed to update user");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            // close resources
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public User getUser(User user) {
        // declare variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User userFetch = null;
        try {
            // create connection and query
            conn = DBConnection.getConnection();
            String query = "SELECT * FROM USERS WHERE email = ? AND password = ? LIMIT 1";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());

            // execute insert
            rs = pstmt.executeQuery();

            // populate user and return user
            if (rs.next()) {
                user = getUserByType(rs.getString("userType"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            // close resources
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        // user not found
        return null;
    }

    @Override
    public void deleteUser(User user) {
        // declare variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // create connection and query
            conn = DBConnection.getConnection();
            String query = "DELETE FROM users WHERE email = ? AND password = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());

            // execute insert
            int rowsAffected = pstmt.executeUpdate();

            // verify that a user was deleted
            if (rowsAffected > 0) {
                System.out.println("User was successfully deleted");
            } else {
                System.out.println("User was not deleted");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            // close resources
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
