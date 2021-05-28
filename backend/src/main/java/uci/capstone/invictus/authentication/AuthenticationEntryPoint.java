package uci.capstone.invictus.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import uci.capstone.invictus.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());

        if(authEx.getMessage().contains("No user found for the query")){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        PrintWriter writer = response.getWriter();
        writer.println(authEx.getMessage());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("DeveloperStack");
        super.afterPropertiesSet();
    }
}
