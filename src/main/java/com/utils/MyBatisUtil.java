package com.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
    //静�?�代码块�?,factory只会被执行一�?
   static {
   	try {
			InputStream is =Resources.getResourceAsStream("mybatis-config.xml");
			factory =new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   public  static SqlSession createSqlSession() {
   	 return factory.openSession(false);//true为自动提交事�?
   }
   public static void closeSqlSession(SqlSession sqlSession) {
   	if(sqlSession!=null) {
   		 sqlSession.close();
   	}
   }
} 
