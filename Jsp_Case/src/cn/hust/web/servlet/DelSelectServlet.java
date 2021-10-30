package cn.hust.web.servlet;

import cn.hust.service.UserService;
import cn.hust.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectServlet")
public class DelSelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //获取所有ID
        String[] ids  = request.getParameterValues("uid");
        //调用service删除
        UserService service=new UserServiceImpl();
        service.delSelectedUser(ids);
        //跳转到查询所有servlet
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
