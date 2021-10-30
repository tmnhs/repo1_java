package cn.hust.dao.impl;

import cn.hust.dao.UserDao;
import cn.hust.domain.User;
import cn.hust.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void addUser(User user) {
        //定义sql
        String sql="insert into user(id ,name,gender,age,address,qq,email,username,password) values(null,?,?,?,?,?,?,NULL ,NULL )";
      //执行
        System.out.println(user);
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        String sql="select *from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
//        for (User user:users){
//            System.out.println(user);
//        }
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
      try {
//          System.out.println(username);
//          System.out.println(password);
          String sql = "select *from USER where username= ? and password = ?";
          User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
//          System.out.println(user);
          return user;
      }catch (Exception e){
          e.printStackTrace();;
          return null;
      }
    }

    @Override
    public void delUser(int id) {
        //定义sql
        String sql="delete from user where id= ?";
        template.update(sql,id);
    }

    @Override
    public User findUserById(int id) {
        String sql="select *from user where id =?";
        return  template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void updateUser(User user) {
        String sql="update user set name =?,gender=?,age=?,address=? ,qq=?,email=? where id =?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId()) ;
    }

    @Override
    public int findTotalCount(Map<String, String[]> conditon) {
        //定义一个模板初始化sql
        String sql="select count(*) from user where 1 = 1 ";
        StringBuilder sb=new StringBuilder(sql);
        //遍历map集合
        Set<String> keySet = conditon.keySet();
        //定义一个参数的集合
        List<Object> params= new ArrayList<Object>();
        for(String key:keySet){
            //判断操作
            if("currentPage".equals(key)||"rows".equals(key))
                continue;
            //获取value
            String value = conditon.get(key)[0];
            //判断value是否有值
            if(value!=null&&!"".equals(value)){
                sb.append(" and " +key+" like ? ");
                params.add("%"+value+"%");//加？条件的值
            }
        }
//
//        System.out.println(sb.toString());
//        System.out.println(params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> FindByPage(int start, int rows, Map<String, String[]> conditon) {
        String sql="select *from user WHERE 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        //定义一个参数的集合
        List<Object> params= new ArrayList<Object>();
        Set<String> keySet = conditon.keySet();
        for(String key:keySet){
            //判断操作
            if("currentPage".equals(key)||"rows".equals(key))
                continue;
            //获取value
            String value = conditon.get(key)[0];
            //判断value是否有值
            if(value!=null&&!"".equals(value)){
                sb.append(" and " +key+" like ? ");
                params.add("%"+value+"%");//加？条件的值
            }
        }
        //添加分页查询
        sb.append(" limit ? ,? ");
        //添加参数值

        params.add(start);
        params.add(rows);
        System.out.println(sb.toString());
        System.out.println(params);
        List<User> users = template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
        return users;
    }
}
