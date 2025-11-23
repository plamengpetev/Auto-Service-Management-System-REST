package com.example.mechanicservice.web;

import com.example.mechanicservice.exception.NoAvailableMechanicException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestErrorController {

    @GetMapping("/test/notfound")
    public void notFound() {
        throw new NoAvailableMechanicException("nope");
    }

    @GetMapping("/test/illegal")
    public void illegal() {
        throw new IllegalArgumentException("bad");
    }

    @GetMapping("/test/error")
    public void error() {
        throw new RuntimeException("fail");
    }

    @GetMapping("/admin/test/notfound")
    public void adminError() {
        throw new NoAvailableMechanicException("admin error");
    }
}
