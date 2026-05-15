package co.edu.unbosque.revista.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.revista.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByUsuario(String usuario);

}
