package org.sandbox.user;


public class UserDAO {

    public static User findById(long id) {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        if (id < 0) return null;
        else return new User(id, "firstName" + id, "lastName" + id, "email" + id + "@mai.ling",
                new Integer((int)(id % 120)));
    }
}
