/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig config)
            throws ServletException {
        // Get init parameter 
        String testParam = config.getInitParameter("test-param");

        //Print the init parameter 
        System.out.println("Test Param: " + testParam);
    }

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws java.io.IOException, ServletException {

        // Get the IP address of client machine.   
        String ipAddress = request.getRemoteAddr();

        // Pass request back down the filter chain
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRep = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        Boolean is_login = (Boolean) session.getAttribute("is_login");
        if (is_login == null || is_login != true) {
            httpRep.sendRedirect(httpReq.getContextPath() + "/login");
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        /* Called before the Filter instance is removed 
         from service by the web container*/
    }
}
