package co.edu.unbosque.revista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.revista.dto.ArticuloDTO;
import co.edu.unbosque.revista.dto.TipoRevista;
import co.edu.unbosque.revista.service.ArticuloService;
import co.edu.unbosque.revista.service.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/articulo")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "*" })
public class ArticuloController {

	@Autowired
	private UsuarioService uService;
	@Autowired
	private ArticuloService arService;

	@PostMapping("/creararticulo")
	public ResponseEntity<String> crearArticulo(@RequestBody String nombreArticulo, @RequestBody String contenido,
			@RequestBody String genero, @RequestBody TipoRevista tipo) {
		String autor = uService.getNombreUsuario();
		ArticuloDTO nuevo = new ArticuloDTO(nombreArticulo, contenido, genero, tipo, autor, "");
		int status = arService.create(nuevo);
		if (status == 1) {
			return new ResponseEntity<>("Tiene que ingresar un editor", HttpStatus.UNAUTHORIZED);
		} else if (status == 0) {
			return new ResponseEntity<>("Articulo Creado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
		}
	}
	@PostMapping("/darcomentarioaarticulo")
	public ResponseEntity<String> comentararticulo(@RequestBody Long id,@RequestBody String comentario) {
		int status = arService.comentario(id, comentario);
		if(status == 0) {
			return new ResponseEntity<>("Comentario realizado con exito!", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
		}
		
		
	}
	@GetMapping("/mostrararticulo")
	public ResponseEntity<String> mostrarListaArticulos() {
		return new ResponseEntity<>(arService.getAll(), HttpStatus.OK);
	}

	@DeleteMapping("/borrararticulo")
	public ResponseEntity<String> borrarArticulo(@RequestBody Long id) {

		int status = arService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Articulo Borrado con exito!", HttpStatus.OK);

		} else if (status == 1) {
			return new ResponseEntity<>("Articulo no encontrado", HttpStatus.CONFLICT);

		} else {
			return new ResponseEntity<>("Error", HttpStatus.CONFLICT);

		}
	}
	
}
