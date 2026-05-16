package co.edu.unbosque.revista.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.edu.unbosque.revista.dto.ArticuloDTO;
import co.edu.unbosque.revista.dto.TipoRevista;
import co.edu.unbosque.revista.entity.Articulo;
import co.edu.unbosque.revista.repository.ArticuloRepository;

@Service
public class ArticuloService implements CRUDOPERATION<ArticuloDTO>{

	@Autowired
	private ArticuloRepository arRep;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UsuarioService uService;
	@Autowired
	private Gson gson;
	@Override
	public int create(ArticuloDTO data) {
		if(uService.getRolUsuario() == "EDITOR") {
		Articulo entity = mapper.map(data, Articulo.class);
		arRep.save(entity);
		ArticuloDTO dto = mapper.map(entity, ArticuloDTO.class);
		gson.toJson(dto);		
		return 0;
		}
		else {
		return 1;
		}
	}

	@Override
	public String getAll() {
		if (!(uService.getRolUsuario() == "EDITOR")) {
			return "[]";
		}

		List<Articulo> entityList = (List<Articulo>) arRep.findAll();
		List<ArticuloDTO> dtoList = new ArrayList<>();

		entityList.forEach(entity -> dtoList.add(mapper.map(entity, ArticuloDTO.class)));
		return gson.toJson(dtoList);
	}

	@Override
	public int deleteById(Long id) {
		Optional<Articulo> encontrado = arRep.findById(id);

		if (encontrado.isPresent()) {
			arRep.delete(encontrado.get());
			return 0;
		}

		return 1;
	}

	@Override
	public long count() {
		return arRep.count();

	}

	@Override
	public boolean exist(Long id) {
		return arRep.existsById(id);

	}

	@Override
	public int updateById(Long id, ArticuloDTO data) {		
		Optional<Articulo> encontrado = arRep.findById(id);
		if(encontrado.isPresent()) {
			ArticuloDTO temp = mapper.map(encontrado.get(), ArticuloDTO.class);
			temp.setNombreArticulo(data.getNombreArticulo());
			temp.setContenido(data.getContenido());
			temp.setGenero(data.getGenero());
			temp.setTipo(data.getTipo());
			temp.setAutor(data.getAutor()); // cuadrar con el usuarioLogueado
			
			Articulo entity = mapper.map(temp, Articulo.class);
			entity.setId(id);
			arRep.save(entity);
			return 0;
		}
		return 2;
	}
	public List<ArticuloDTO> findByTipoArtiulo(TipoRevista tipoArticulo) {

		Optional<List<Articulo>> encontrados = arRep.findByTipo(tipoArticulo);
		List<ArticuloDTO> dtoList = new ArrayList<>();

		if (encontrados.isPresent() && !encontrados.get().isEmpty()) {
			encontrados.get().forEach(entity -> dtoList.add(mapper.map(entity, ArticuloDTO.class)));
		}

		return dtoList;
	}

}
