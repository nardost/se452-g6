package edu.depaul.g6.ui.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class G6ErrorController {

    private final static String ERROR_PATH = "/error";

    public String handleError() {
        return "error";
    }

    public String getErrorPath() {
        return ERROR_PATH;
    }
}
