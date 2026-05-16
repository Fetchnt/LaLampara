package co.edu.unbosque.revista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.revista.dto.Rol;
import co.edu.unbosque.revista.dto.UsuarioDTO;
import co.edu.unbosque.revista.entity.Usuario;
import co.edu.unbosque.revista.service.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "*" })
public class UsuarioController {

	@Autowired
	private UsuarioService uService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody String usuario, @RequestBody String password) {
		UsuarioDTO user = new UsuarioDTO(usuario, password, Rol.USUARIO);
		int status = uService.create(user);
		if(status == 2) {
			return new ResponseEntity<>("El Usuario ya existe", HttpStatus.CONFLICT);
		}
		else if(status == 0) {
			return new ResponseEntity<>("Registro Exitoso!", HttpStatus.OK);

		}
		else {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
