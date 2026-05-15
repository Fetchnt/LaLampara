package co.edu.unbosque.revista.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.revista.entity.Admin;
import co.edu.unbosque.revista.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
