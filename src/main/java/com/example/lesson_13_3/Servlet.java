package com.example.lesson_13_3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Servlet", value = "/servlet")
public class Servlet extends HttpServlet {
    private String message;
    private Double a;
    private Double b;
    private String op;
    private Double res = 0.0;

    public void init() {
        message = "Welcome!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String aString = request.getParameter("a");
        String bString = request.getParameter("b");
        op = request.getParameter("op");

        if (aString == null || notNumber(aString) || bString == null || notNumber(bString) || op == null) message = "Wrong format";
        else {
                message = "";

                a = Double.valueOf(aString);
                b = Double.valueOf(bString);

                switch (op) {
                    case "add":
                        res = a + b;
                        break;
                    case "sub":
                        res = a - b;
                        break;
                    case "mul":
                        res = a * b;
                        break;
                    case "div":
                        if (b == 0.0) message = "Division by zero!";
                        else res = a / b;
                        break;
                }
        }


        out.println("<html><body>");
        out.println("<h2>" + message + "</h2>");
        out.println("<p>1. В адресной строке после <b>servlet</b> введите a и b, например: <b>?a=890&b=56</b></p>");
        out.println("<p>2. Добавьте <b>&op=</b> и действие (add, sub, mul, div), например: <b>&op=plus</b></p>");
        out.println("<p>3. Нажмите <b>Enter</b></p>");

        out.println("<p>a : " + a + "</p>");
        out.println("<p>b : " + b + "</p>");
        out.println("<p>op : " + op + "</p>");
        out.println("<p>res : " + res + "</p>");

        out.println("</body></html>");
    }

    public boolean notNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return true;
        }
        return false;
    }

    public void destroy() {
    }
}