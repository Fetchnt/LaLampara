package co.edu.unbosque.revista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.revista.service.AdminService;
import co.edu.unbosque.revista.service.ArticuloService;
import co.edu.unbosque.revista.service.UsuarioService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "*" })
public class AdminController {

	@Autowired
	private AdminService aService;
	@Autowired
	private UsuarioService uService;
	@Autowired
	private ArticuloService arService;
	
	@GetMapping("/mostrarusuarios")
	public ResponseEntity<String> mostrarUsuarios() {
		return new ResponseEntity<>(uService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/mostrararticulos")
	public ResponseEntity<String> mostrarArticulos() {
		return new ResponseEntity<>(arService.getAll(), HttpStatus.OK);
	}	
	
	@DeleteMapping("/borrarusuarios")
	public ResponseEntity<String> eliminarUsuarios(@RequestParam Long id) {
		int status = uService.deleteById(id);
		if(status == 0) {
			return new ResponseEntity<>("Usuario Borrado",HttpStatus.OK);
		}
		else if(status == 1) {
			return new ResponseEntity<>("No se ha encontrado el usuario", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<> ("Error", HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/borrarpaquetes")
	public ResponseEntity<String> eliminarArticulos(@RequestParam Long id) {
		int status = arService.deleteById(id);
		if(status == 0) {
			return new ResponseEntity<>("Articulop Borrado",HttpStatus.OK);
		}
		else if(status == 1) {
			return new ResponseEntity<>("No se ha encontrado el articulo", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<> ("Error", HttpStatus.CONFLICT);
		}
	}
	
	
	
	
	
}
