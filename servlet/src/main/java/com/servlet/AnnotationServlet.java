package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用注解描述Servlet
 *
 * @author Luxh
 */

/**
 * 注解WebServlet用来描述一个Servlet
 * 属性name描述Servlet的名字,可选
 * 属性urlPatterns定义访问的URL,或者使用属性value定义访问的URL.(定义访问的URL是必选属性)
 */
@WebServlet(name = "AnnotationServlet", urlPatterns = "/AnnotationServlet")
public class AnnotationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameter("");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML>");
        out.println("<HTML>");
        out.println("      <HEAD>");
        out.println("    　　<TITLE>A Servlet</TITLE>");
        out.println("    　　<meta http-equiv=\"content-type\" " + "content=\"text/html; charset=utf-8\">");
        out.println("　　 </HEAD>");
        out.println("       <BODY>");
        out.println("             Hello AnnotationServlet.");
        out.println("     </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

}