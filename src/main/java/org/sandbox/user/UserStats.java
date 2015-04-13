package org.sandbox.user;

import java.util.Collection;

public class UserStats {

    public static Double avgAge(Collection<User> users) {
//        if (users.isEmpty()) return null;
        int sumAge = sumAge(users);
        return sumAge / new Double(users.size());
    }

    private static int sumAge(Collection<User> users) {
        int sum = 0;
        for(User user: users) {
            sum += user.getAge();
        }
        return sum;
    }
}
