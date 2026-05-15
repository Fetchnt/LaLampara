package co.edu.unbosque.revista.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.edu.unbosque.revista.dto.AdminDTO;
import co.edu.unbosque.revista.entity.Admin;
import co.edu.unbosque.revista.repository.AdminRepository;

@Service
public class AdminService implements CRUDOPERATION<AdminDTO> {

	@Autowired
	private AdminRepository aRep;

	@Autowired
	private ModelMapper mapper;

	private Admin adminLogueado;

	@Override
	public int create(AdminDTO data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAll() {
		if (adminLogueado == null) {
			return null;
		}
		Gson gson = new Gson();
		List<Admin> entityList = (List<Admin>) aRep.findAll();
		List<AdminDTO> dtoList = new ArrayList<>();

		entityList.forEach(entity -> {
			AdminDTO dto = mapper.map(entity, AdminDTO.class);
			dtoList.add(dto);
		});
		return gson.toJson(dtoList);
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count() {
		return aRep.count();
	}

	@Override
	public boolean exist(Long id) {
		return aRep.existsById(id);
	}

	@Override
	public int updateById(Long id, AdminDTO data) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int loginadmin(String usuario, String contrasenia, String codigoadmin) {

		Optional<Admin> encontrado = aRep.findByUsuario(usuario);

		if (encontrado.isPresent() && encontrado.get().getContrasenia().equals(contrasenia)
				&& encontrado.get().getCodigoAdmin().equals("admin123")) {

			adminLogueado = encontrado.get();
			return 0;
		}
		return 1;
	}

	public void logoutadmin() {
		adminLogueado = null;
	}

	public boolean isLoggedadmin() {
		return adminLogueado != null;
	}

}
