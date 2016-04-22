package kr.ac.jejunu.userdao;

import kr.ac.jejunu.userdao.statement.*;

import java.sql.*;

public class UserDao {

    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.jdbcContextWithStatementForStatementStrategyForQuery(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        return jdbcContext.jdbcContextWithStatementForStatementStrategyInsert(statementStrategy);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStategy = new UpdateUserStatementStrategy(user);
        jdbcContext.jdbcContextWithStatementForStatementStrategy(statementStategy);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStategy = new DeleteUserStatementStrategy(id);
        jdbcContext.jdbcContextWithStatementForStatementStrategy(statementStategy);
    }
}
