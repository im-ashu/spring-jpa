package com.learning.security.jpa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping(path = "/")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("<h1>Hello</h1>");
  }

  @GetMapping(path = "/admin")
  public ResponseEntity<String> helloAdmin() {
    return ResponseEntity.ok("<h1>Hello Admin</h1>");
  }

  @GetMapping(path = "/user")
  public ResponseEntity<String> helloUser() {
    return ResponseEntity.ok("<h1>Hello User</h1>");
  }
}
