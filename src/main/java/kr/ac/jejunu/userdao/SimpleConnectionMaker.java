package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by HSH on 2016. 4. 22..
 */
public class SimpleConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        // Connection    접속정보는? localhost jeju id : jeju pw: jejupw
        return DriverManager.getConnection("jdbc:mysql://localhost/jejunu", "root", "1234");
    }
}
