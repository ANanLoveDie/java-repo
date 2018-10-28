package com.itheima;


import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private  InputStream in;
    private SqlSessionFactoryBuilder sessionFactoryBuilder;
    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    private UserDao userDao;
    @Before
    public void init() throws IOException {
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        sessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sessionFactory = sessionFactoryBuilder.build(in);
        session = sessionFactory.openSession();
        userDao = session.getMapper(UserDao.class);
    }
    @After
    public void destroy() throws IOException {
        session.close();
        in.close();
    }
    @Test
    public void testFind() {


        List<User> users=userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }
    @Test
    public void testAdd(){
        User user=new User();
        user.setUsername("张杰");
        user.setAddress("上海");
        user.setBirthday(new Date());
        user.setSex("男");
        userDao.addUser(user);
        session.commit();

    }



}
