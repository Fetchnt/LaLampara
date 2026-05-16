package co.edu.unbosque.revista.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.revista.dto.TipoRevista;
import co.edu.unbosque.revista.entity.Articulo;

public interface ArticuloRepository extends CrudRepository<Articulo, Long> {

	Optional<List<Articulo>> findByTipo(TipoRevista tipoArticulo);
	
}
