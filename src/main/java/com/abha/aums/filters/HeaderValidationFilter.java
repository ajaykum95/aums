package com.abha.aums.filters;

import com.abha.sharedlibrary.shared.constants.HeaderConstant;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HeaderValidationFilter implements Filter {

    private static final List<String> REQUIRED_HEADERS = Arrays.asList(
            HeaderConstant.USER_ID, HeaderConstant.CLIENT_ID
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (validHeader(httpRequest, httpResponse)) return;

        // Continue the filter chain if header is present
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private static boolean validHeader(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        for (String header : REQUIRED_HEADERS) {
            String headerValue = httpRequest.getHeader(header);
            if (headerValue == null || headerValue.isEmpty()) {
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.getWriter().write("Missing required header: " + header);
                return true;
            }
            if (HeaderConstant.CLIENT_ID.equalsIgnoreCase(header)
                    && !HeaderConstant.CLIENT_ALLOWED.contains(headerValue)) {
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.getWriter().write("Invalid client id: " + header);
                return true;
            }
        }
        return false;
    }
}
