package cn.hust.web.servlet;

import cn.hust.domain.User;
import cn.hust.service.UserService;
import cn.hust.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //调用Service查询
        UserService service=new UserServiceImpl();
        User user= service.findUserById(id);
        //将User存入request中
        request.setAttribute("user",user);
        //转发到update.jsp中
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
