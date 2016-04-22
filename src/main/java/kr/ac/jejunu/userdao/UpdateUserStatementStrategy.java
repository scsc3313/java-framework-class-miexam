package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by HSH on 2016. 4. 22..
 */
public class UpdateUserStatementStrategy implements StatementStrategy{

    private User user;

    public UpdateUserStatementStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update userinfo set name = ?, password = ? where id = ?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setLong(3, user.getId());
        preparedStatement.executeUpdate();
        return preparedStatement;
    }
}
