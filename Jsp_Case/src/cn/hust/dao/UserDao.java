package cn.hust.dao;

import cn.hust.domain.User;

import java.util.List;
import java.util.Map;

/**
 *
 * 用户操作的DAO
 *
 */

public interface UserDao {
    /**
     * 查找所有用户
     * @return
     */
    public List<User>findAll();

    /**
     *
     * 添加用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 通过用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    public User findUserByUsernameAndPassword(String username,String password);

    /**
     * 删除用户
     * @param id
     */
    public  void delUser(int id);

    /**
     * 通过ID查找用户
     * @param id
     * @return
     */
    public  User findUserById(int id);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     *查询总记录数
     * @return
     * @param conditon
     */
    int findTotalCount(Map<String, String[]> conditon);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param conditon
     * @return
     */
    List<User> FindByPage(int start, int rows, Map<String, String[]> conditon);
}
