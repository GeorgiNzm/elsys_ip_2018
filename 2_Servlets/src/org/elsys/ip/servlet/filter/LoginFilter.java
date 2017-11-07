package org.elsys.ip.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	private static final String HARDCODED_USERNAME = "Admin";
	private static final String HARDCODED_PASS = "Admina";
	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();

		String username = request.getParameter("name");
		String password = request.getParameter("password");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// check username and password (can be hardcoded, can use the userService)
		// add if the person is logged in to a cookie (Google it), so that we do not check at every page

		boolean authorized = true;

		if (username != null && password != null) {
			if (username.equals(HARDCODED_USERNAME)
					&& password.equals(HARDCODED_PASS)) authorized = true;
		}

		List<Cookie> cookieList = Arrays.asList(httpRequest.getCookies());

//		for (Cookie cookie : cookieList) {
//			if (cookie.getName().equals("AccessGranted")
//					&& cookie.getValue().equals(username)) authorized = true;
//		}

		boolean match = cookieList.stream().anyMatch(cookie -> cookie.getName().equals("AccessGranted")
				&& cookie.getValue().equals(username));

		if (authorized || match) {

			if (username == null) httpResponse.addCookie(new Cookie("AccessGranted", "username"));
			else {
				httpResponse.addCookie(new Cookie("AccessGranted", username));
			}

			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "Wrong username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
