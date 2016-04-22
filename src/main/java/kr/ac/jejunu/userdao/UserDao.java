package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            //데이터는어디에?   Mysql
            //Driver Class Load
            connection = connectionMaker.getConnection();
            // 쿼리만들고
            preparedStatement = new GetUserStatementStrategy(id).makeStatement(connection);
            // 실행
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // 결과매핑
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            //자원을 해지한다.
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long id = null;
        try {
            connection = connectionMaker.getConnection();

            preparedStatement = new AddUserStatementStrategy(user).makeStatement(connection);

            id = getLastInsertId(connection);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return id;
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStategy = new UpdateUserStatementStrategy(user);
        jdbcContextWithStatementForStatementStrategy(statementStategy);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStategy = new DeleteUserStatementStrategy(id);
        jdbcContextWithStatementForStatementStrategy(statementStategy);
    }

    private void jdbcContextWithStatementForStatementStrategy(StatementStrategy statementStategy) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionMaker.getConnection();
            preparedStatement = statementStategy.makeStatement(connection);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private Long getLastInsertId(Connection conection) throws SQLException {
        PreparedStatement lastInsertIdStatement = conection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = lastInsertIdStatement.executeQuery();
        resultSet.next();
        Long id = resultSet.getLong(1);
        resultSet.close();
        lastInsertIdStatement.close();
        return id;
    }
}
