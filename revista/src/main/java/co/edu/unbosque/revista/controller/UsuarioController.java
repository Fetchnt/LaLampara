package co.edu.unbosque.revista.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "*" })
public class UsuarioController {

	
}
