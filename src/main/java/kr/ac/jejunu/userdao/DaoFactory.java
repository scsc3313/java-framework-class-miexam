package kr.ac.jejunu.userdao;

/**
 * Created by HSH on 2016. 4. 22..
 */
public class DaoFactory {
    public UserDao getUserDao(){
        return new UserDao(new SimpleConnectionMaker());
    }
}
