package co.edu.unbosque.revista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.revista.dto.ArticuloDTO;
import co.edu.unbosque.revista.service.ArticuloService;
import co.edu.unbosque.revista.entity.Usuario;

@RestController
@RequestMapping("/articulo")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "*" })
public class ArticuloController {

	@Autowired
	private ArticuloService arService;

	@PostMapping("/creararticulo")
	public ResponseEntity<String> crearArticulo(@RequestBody ArticuloDTO datos, // ✅ import de Spring
			Authentication auth) {

		Usuario usuario = (Usuario) auth.getPrincipal();
		String autor = usuario.getUsuario();

		
		ArticuloDTO nuevo = new ArticuloDTO(datos.getNombreArticulo(), datos.getContenido(), datos.getGenero(),
				datos.getTipo(), autor, "");

		int status = arService.create(nuevo);
		if (status == 1)
			return new ResponseEntity<>("Tiene que ingresar un editor", HttpStatus.UNAUTHORIZED);
		if (status == 0)
			return new ResponseEntity<>("Articulo Creado con exito!", HttpStatus.OK);
		return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
	}

	@PostMapping("/darcomentarioaarticulo")
	public ResponseEntity<String> comentarArticulo(@RequestParam Long id, @RequestParam String comentario) {
		int status = arService.comentario(id, comentario);
		if (status == 0) {
			return new ResponseEntity<>("Comentario realizado con exito!", HttpStatus.OK);			
		}
		return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
	}

	@GetMapping("/mostrararticulo")
	public ResponseEntity<String> mostrarListaArticulos() {
		return new ResponseEntity<>(arService.getAll(), HttpStatus.OK);
	}

	@DeleteMapping("/borrararticulo")
	public ResponseEntity<String> borrarArticulo(@RequestParam Long id) {
		int status = arService.deleteById(id);
		if (status == 0)
			return new ResponseEntity<>("Articulo Borrado con exito!", HttpStatus.OK);
		if (status == 1)
			return new ResponseEntity<>("Articulo no encontrado", HttpStatus.CONFLICT);
		return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
	}
}