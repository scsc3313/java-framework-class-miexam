package kr.ac.jejunu.userdao;

import kr.ac.jejunu.userdao.statement.StatementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(final Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
                preparedStatement.setLong(1, id);
                return preparedStatement;
            }
        };
        return jdbcContext.jdbcContextWithStatementForStatementStrategyForQuery(statementStrategy);
    }

    public Long add(final User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new StatementStrategy(){
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?, ?)");
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.executeUpdate();
                return preparedStatement;
            }
        };
        return jdbcContext.jdbcContextWithStatementForStatementStrategyInsert(statementStrategy);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = {user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = {id};
        jdbcContext.update(sql, params);
    }

}
