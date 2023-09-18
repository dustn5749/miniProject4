/*
 * package com.kosa.project4.filter;
 * 
 * import java.io.IOException; import javax.servlet.Filter; import
 * javax.servlet.FilterChain; import javax.servlet.FilterConfig; import
 * javax.servlet.ServletException; import javax.servlet.ServletRequest; import
 * javax.servlet.ServletResponse; import javax.servlet.annotation.WebFilter;
 * import javax.servlet.http.HttpFilter; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * import project.model.Member;
 * 
 * 
 * @WebFilter("/*") public class LoginFilter extends HttpFilter implements
 * Filter {
 * 
 * 
 * 
 * public LoginFilter() { super(); }
 * 
 * 
 * public void destroy() { }
 * 
 * public void doFilter(ServletRequest request, ServletResponse response,
 * FilterChain chain) throws IOException, ServletException { HttpServletRequest
 * req = (HttpServletRequest) request; HttpServletResponse resp =
 * (HttpServletResponse) response; HttpSession session = req.getSession();
 * Member loginMember = (Member) session.getAttribute("loginMember"); String url
 * = req.getRequestURI();
 * 
 * if (url.endsWith("/member/joinForm.do") || url.endsWith("/member/add.do") ||
 * url.endsWith("/") || url.endsWith("/index/index.do") ||
 * url.endsWith("/member/idAndPwdSearchForm.do") ||
 * url.endsWith("/member/loginForm.do") || url.endsWith("/member/existUid.do")
 * || url.endsWith("/member/login.do") || url.endsWith("/member/searchId.do") ||
 * url.endsWith("/member/searchPwd.do") // || url.endsWith("/board/list.do") ||
 * url.contains("/css/") || url.contains("/etc/") || url.contains("/images/") ||
 * url.contains("/js/") ) { chain.doFilter(request, response); return; } else {
 * if(loginMember == null) { String redirectUrl = req.getContextPath() +
 * "/member/loginForm.do"; String script = "<script>alert('로그인 후 이용해주세요.');";
 * script += "window.location.href='" + redirectUrl + "';</script>";
 * resp.setContentType("text/html;charset=UTF-8");
 * resp.getWriter().write(script); return; } }
 * 
 * 
 * chain.doFilter(request, response); }
 * 
 * 
 * public void init(FilterConfig fConfig) throws ServletException { }
 * 
 * }
 */