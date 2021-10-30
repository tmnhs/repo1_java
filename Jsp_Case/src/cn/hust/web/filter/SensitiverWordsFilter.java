package cn.hust.web.filter;
/**
 * 敏感词汇过滤器
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiverWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //创建代理对象，增强getParameter方法，
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               //增强getParameter方法，
                if (method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String) method.invoke(req, args);
                    if(value!=null){
                        for(String str:list){
                            value   =value.replaceAll(str,"***");
                            return value;
                        }
                    }
                }
                return method.invoke(req,args);
            }
        });
        //判断getParameterMap和getParameterValue

        chain.doFilter(req, resp);
    }
    private List<String>list;

    public void init(FilterConfig config) throws ServletException {

        list=new ArrayList<String>();
        try {
            //加载配置文件,获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //读取文件
            BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream(realPath),"utf-8"));

            String line=null;
            while ((line=br.readLine())!=null){
                list.add(line);
            }
            br.close();
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }

        //见文件的每一行数添加到List中
    }

}
