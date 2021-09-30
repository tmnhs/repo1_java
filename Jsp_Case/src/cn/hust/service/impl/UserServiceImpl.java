package cn.hust.service.impl;

import cn.hust.dao.UserDao;
import cn.hust.dao.impl.UserDaoImpl;
import cn.hust.domain.PageBean;
import cn.hust.domain.User;
import cn.hust.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService{
    private UserDao dao=new UserDaoImpl();

    /**
     *查找所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    /**
     *添加用户
     * @return
     */
    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void delUser(String id) {
        dao.delUser(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt((id)));
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids  !=null&&ids.length>0){
            for(String id:ids){
                dao.delUser(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> conditon) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //创建一个空的pageBean对象
        PageBean<User> pb=new PageBean<User>();

        //调用dao查询记录数
        int totalCount=dao.findTotalCount(conditon);
        //计算总页码
        int totalPage=(totalCount%rows==0)?(totalCount/rows):(totalCount/rows+1);

        if(currentPage>totalPage)
            currentPage=totalPage;
        if(currentPage<=0)
            currentPage=1;
        //查询dao查询list集合
        //记录开始的记录的索引
        int start=(currentPage-1)*rows;
        List<User> list= dao.FindByPage(start,rows,conditon);

        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        pb.setList(list );
        return pb;
    }
}
