package com.org.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CheckLogin implements Filter {
	
	private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public CheckLogin() {
    }
    
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }
    
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    // After process filter 
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        boolean accept = false;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession();
            if(session != null) {
                if(session.getAttribute("userNow") != null) {
                    accept = true;
                }
            }
        }
        if(!accept){
            request.setAttribute("message", " Login required to access the page");
            filterConfig.getServletContext().getRequestDispatcher("/login.jsp").
                    forward(request, response);
            return;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        doBeforeProcessing(request, response);
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable f) {
            problem = f;
            f.printStackTrace();
        }
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream printStream = new PrintStream(response.getOutputStream());
                PrintWriter printWriter = new PrintWriter(printStream);
                printWriter.print("<html>\n"
                                + "<head>\n"
                                + "<title>Error</title>\n"
                                + "</head>\n"
                            + "<body>\n");

                printWriter.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                printWriter.print(stackTrace);
                printWriter.print("</pre></body>\n</html>");
                printWriter.close();
                printStream.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable throwable) {
        String stackTrace = null;
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWrite = new PrintWriter(stringWriter);
            throwable.printStackTrace(printWrite);
            printWrite.close();
            stringWriter.close();
            stackTrace = stringWriter.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("CheckLogin()");
        }
        StringBuffer sb = new StringBuffer("CheckLogin(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
