import javaDemo.classloader.A;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date 2019/1/12 16:53
 */
public class Test   {
    public static void main(String[] args) {
        String name = TypeE.TAypeE.name();
        System.out.println(name);
    }

    enum TypeE{
        TAypeE,BTypeE;

    }
}