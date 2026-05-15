package co.edu.unbosque.revista.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.revista.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {

	Optional<Admin> findByUsuario(String usuario);

}
