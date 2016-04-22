package kr.ac.jejunu.userdao;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        UserDao userDao = new DaoFactory().getUserDao();
        Long id = 1L;
        String name = "현승호";
        String password = "1234";

        User user = userDao.get(id);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        UserDao userDao = new DaoFactory().getUserDao();

        User user = new User();

        String name = "김철수";
        String password = "1111";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        User resultUser = userDao.get(id);

        assertEquals(id, resultUser.getId());
        assertEquals(name, resultUser.getName());
        assertEquals(password, resultUser.getPassword());

    }
}
