package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by HSH on 2016. 4. 22..
 */
public interface StatementStrategy {
    PreparedStatement makeStatement(Connection connection) throws SQLException;
}
