package co.edu.unbosque.revista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.revista.dto.Rol;
import co.edu.unbosque.revista.dto.UsuarioDTO;
import co.edu.unbosque.revista.service.ArticuloService;
import co.edu.unbosque.revista.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "*" })
public class UsuarioController {

	@Autowired
	private UsuarioService uService;
	@Autowired
	private ArticuloService arService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UsuarioDTO datos) {
		UsuarioDTO user = new UsuarioDTO(datos.getUsuario(), datos.getContrasenia(), Rol.USUARIO);
		int status = uService.create(user);
		if (status == 2) {
			return new ResponseEntity<>("El Usuario ya existe", HttpStatus.CONFLICT);
		} else if (status == 0) {
			return new ResponseEntity<>("Registro Exitoso!", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UsuarioDTO datos) {
		String resultado = uService.login(datos.getUsuario(), datos.getContrasenia());

		if (resultado.equals("USER_NOT_FOUND"))
			return new ResponseEntity<>("Usuario no registrado", HttpStatus.NOT_FOUND);
		if (resultado.equals("WRONG_PASSWORD"))
			return new ResponseEntity<>("Contrasena incorrecta", HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@GetMapping("/mostrarusuarios")
	public ResponseEntity<String> mostrarUsuarios() {
		return new ResponseEntity<>(uService.getAll(), HttpStatus.OK);
	}
	


}
