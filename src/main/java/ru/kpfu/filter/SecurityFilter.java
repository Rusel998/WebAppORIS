package ru.kpfu.filter;

import ru.kpfu.services.security.SecurityService;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter{
    protected final String[] protectedPaths = {"/profile"};
    private SecurityService security;

    @Override
    public void init() throws ServletException {
        super.init();
        security = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean prot= false;
        for(String path : protectedPaths){
            if(path.equals(req.getRequestURI().substring(req.getContextPath().length()))){
                prot = true;
                break;
            }
        }
        if(prot && !security.isSigned(req)){
            res.sendRedirect(req.getContextPath() + "/");
        }
        else{
            if(security.isSigned(req)){
                req.setAttribute("user", security.getUser(req));
            }
            chain.doFilter(req, res);
        }
    }

}