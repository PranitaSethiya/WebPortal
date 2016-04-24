package com.se.pranita.termproject.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Pranita on 23/4/16.
 */
public class ParamsHelper {

    public static void printAllParameters(HttpServletRequest request) {
        Enumeration<String> var = request.getParameterNames();
        while(var.hasMoreElements()) {
            String key = var.nextElement();
            System.out.println(key + ": " + request.getParameter(key));
        }
    }

}
