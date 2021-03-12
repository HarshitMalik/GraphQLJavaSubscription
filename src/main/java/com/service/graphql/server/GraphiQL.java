package com.service.graphql.server;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/graphiql")
public class GraphiQL extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException {
          RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/graphiql.html");
          rd.include(request, response);
    }
}
