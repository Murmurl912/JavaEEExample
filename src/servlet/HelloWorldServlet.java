package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.function.Consumer;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

@WebServlet(
        urlPatterns = {"/HelloWorld"}, // path of a servlet
        initParams = { // initialize parameters, in key-value format
                @WebInitParam(name = "language", value = "english")
        }
)
public class HelloWorldServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(HelloWorldServlet.class.getSimpleName());

    static {
        logger.addHandler(new ConsoleHandler());
    }

    public HelloWorldServlet() {
        super();
        logger.log(Level.INFO, "HelloWorldServlet() Invoked");
    }

    // only called at the first time the servlet is created

    /**
     * in the lifecycle of a servlet this method is executed only once after create instance of this servlet
     * Servlet engine will invoke init() right before it put in services after instantiation.
     * init() must exit before service() can be called.
     * if init() throw a ServletException then this servlet will not be put in services
     * if init() cannot finish executing after a period of time, then it als cannot be put in services
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        logger.log(Level.INFO, "init() Invoked");
    }

    // service is called when a request come in

    /**
     * service() is the core of servlet. when a client send a request, servlet container will invoke this method and pass a
     * ServletRequest instance and a ServletResponse instance, to provide services.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        logger.log(Level.INFO, "service(req, resp) Invoked");
    }

    // called when server is restart or shutdown

    /**
     * destroy() executed when servlet container determine servlet is no longer needed then call destroy() to free resources
     * when a server unload a servlet, it will execute after all service method call finished.
     */
    @Override
    public void destroy() {
        super.destroy();
        logger.log(Level.INFO, "destory() Invoked");
    }

    /**
     * return a ServletConfig object, this object is used to return initialization parameters and ServletContext
     * ServletContext provides interface about Servlet environment
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        logger.log(Level.INFO, "getServletConfig() Invoked");
        return super.getServletConfig();
    }

    /**
     * an optional method provides info anout servlet
     * such as author, version, copyright and so no
     * @return
     */
    @Override
    public String getServletInfo() {
        return super.getServletInfo();
    }

    // handle get request
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.log(Level.INFO, "doGet(request, response) Invoked");

        // get initialize parameters
        String language = this.getInitParameter("language");

        response.setContentType("text/html; charset=utf-8"); // set response type and charset
        PrintWriter writer = response.getWriter(); // get output stream for response
        String path = this.getServletContext().getRealPath("live.html");
        File file = new File(path);

        if(file.exists()) {
            FileInputStream in = new FileInputStream(file);
            BufferedReader reader =  new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String line = "";
            Stream<String> lines = reader.lines();
            lines.forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    builder.append(s).append("\n");
                }
            });
            writer.println(builder.toString());
            reader.close();
        } else {
            writer.println("File Not Found <br/>");
        }
        writer.flush();
        /*
        writer.println("Hello World. It is a HelloWorldServlet<br/>"); // send response
        if(language != null) {
            writer.println("Language; " + language);
        }

         */
    }

    // handle post request
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        // handle as doGet
        doGet(req, resp);
    }
}

