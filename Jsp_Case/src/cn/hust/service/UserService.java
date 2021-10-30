package cn.hust.service;

import cn.hust.domain.PageBean;
import cn.hust.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */

public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 用户删除
     * @param id
     */
    public void  delUser(String id);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public User findUserById(String id );

    /**
     * 修改数据
     * @param user
     */
    void updateUser(User user);

    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     * 分页查询,分页条件查询
     * @param currentPage
     * @param rows
     * @param conditon
     * @return
     */
    PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> conditon);
}
