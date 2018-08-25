package controller.filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            servletResponse.setContentType(EncodingConstants.CONTEX_TYPE_TEXT_HTML);
            servletRequest.setCharacterEncoding(EncodingConstants.REGUEST_ENCODING_UTF8);
            servletResponse.setCharacterEncoding(EncodingConstants.RESPONSE_ENCODING_UTF8);
            // todo replace sout
            System.out.println("Encoding filter");
            filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
