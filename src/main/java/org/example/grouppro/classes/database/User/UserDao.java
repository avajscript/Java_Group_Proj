package org.example.grouppro.classes.database.User;

import org.example.grouppro.classes.users.Charity;
import org.example.grouppro.classes.users.Consumer;
import org.example.grouppro.classes.users.Retailer;
import org.example.grouppro.classes.users.User;

import java.util.List;

public abstract class UserDao {
    abstract List<User> getAllUsers();

    abstract void createUser(User user);

    abstract void updateUser(User user);

    abstract User getUser(User user);

    abstract void deleteUser(User user);

    User getUserByType(String userType) {
        User user = null;
        switch (userType) {
            case "CONSUMER":
                user = new Consumer();
                user.setUserType("" + User.USER_TYPES.CONSUMER);
                break;
            case "RETAILER":
                user = new Retailer();
                user.setUserType("" + User.USER_TYPES.RETAILER);
            case "CHARITY":
                user = new Charity();
                user.setUserType("" + User.USER_TYPES.CHARITY);
            default:
                throw new UnsupportedOperationException();
        }
        return user;
    }


}
