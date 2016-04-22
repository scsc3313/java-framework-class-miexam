package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by HSH on 2016. 4. 22..
 */
public class AddUserStatementStrategy implements StatementStrategy{

    private User user;

    public AddUserStatementStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();
        return preparedStatement;
    }
}
