package co.edu.unbosque.revista.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.edu.unbosque.revista.dto.ArticuloDTO;
import co.edu.unbosque.revista.entity.Articulo;
import co.edu.unbosque.revista.repository.ArticuloRepository;

public class ArticuloService implements CRUDOPERATION<ArticuloDTO>{

	@Autowired
	private ArticuloRepository arRep;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AdminService aService;
	private Gson gson;
	@Override
	public int create(ArticuloDTO data) {
		
		
		Articulo entity = mapper.map(data, Articulo.class);
		arRep.save(entity);
		ArticuloDTO dto = mapper.map(entity, ArticuloDTO.class);
		String json = gson.toJson(dto);		
		return 0;
	}

	@Override
	public String getAll() {
		if (!aService.isLoggedadmin()) {
			return null;
		}

		List<Articulo> entityList = (List<Articulo>) arRep.findAll();
		List<ArticuloDTO> dtoList = new ArrayList<>();

		entityList.forEach(entity -> dtoList.add(mapper.map(entity, ArticuloDTO.class)));
		return gson.toJson(dtoList);
	}

	@Override
	public int deleteById(Long id) {
		if (!aService.isLoggedadmin()) {
			return 2;
		}
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
		// TODO Auto-generated method stub
		return 0;
	}

}
