package cn.hust.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制转化
        HttpServletRequest request=(HttpServletRequest)req;
        //判断是否是登录相关的资源,要注意排除调css/js/图片/验证码
        String uri = request.getRequestURI();
        if(uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/checkCodeServlet")){
            //包含，证明用户就是想登录，放行
            chain.doFilter(req,resp);
        }else{
            //不包含，需要验证用户是否登录
            //从session中获取
            Object user = request.getSession().getAttribute("user");
            if(user!=null){
                //登录，放行
                chain.doFilter(req,resp);
            }
            else {
                //没有登录，跳转登录页面
                request.setAttribute("login_msg","您尚未登录，请先登录！");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
        //获取资源的请求路径

        //        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
