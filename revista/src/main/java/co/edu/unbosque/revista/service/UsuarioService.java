package co.edu.unbosque.revista.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import co.edu.unbosque.revista.dto.UsuarioDTO;
import co.edu.unbosque.revista.entity.Usuario;
import co.edu.unbosque.revista.repository.UsuarioRepository;

public class UsuarioService implements CRUDOPERATION<UsuarioDTO> {

	@Autowired
	private UsuarioRepository uRep;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AdminService aService;

	private Usuario usuarioLogueado;

	@Override
	public int create(UsuarioDTO data) {
		Optional<Usuario> encontrado = uRep.findByUsuario(data.getUsuario());
		if (encontrado.isPresent()) {
			return 2;
		}
		/*
		 * try { LanzadorException.verificarCedulaValida(data.getCedula()); } catch
		 * (CedulaException e) { return 1; }
		 */ Usuario entity = mapper.map(data, Usuario.class);
		uRep.save(entity);
		return 0;
	}

	@Override
	public String getAll() {
		if (!aService.isLoggedadmin()) {
			return null;
		}
		Gson gson = new Gson();
		List<Usuario> entityList = (List<Usuario>) uRep.findAll();
		List<UsuarioDTO> dtoList = new ArrayList<>();
		entityList.forEach(entity -> dtoList.add(mapper.map(entity, UsuarioDTO.class)));
		return gson.toJson(dtoList);
	}

	@Override
	public int deleteById(Long id) {
		if (!aService.isLoggedadmin()) { // verificar esto con el JWT
			return 2;
		}
		if (uRep.existsById(id)) {
			uRep.deleteById(id);
			return 0;
		}
		return 1;
	}

	@Override
	public long count() {
		return uRep.count();
	}

	@Override
	public boolean exist(Long id) {
		return uRep.existsById(id);
	}

	@Override
	public int updateById(Long id, UsuarioDTO data) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int login(String usuario, String contrasenia) {
		Optional<Usuario> encontrado = uRep.findByUsuario(usuario);
		if (encontrado.isPresent() && encontrado.get().getContrasenia().equals(contrasenia)) {
			usuarioLogueado = encontrado.get();
			return 0;
		}
		return 1;
	}

	public boolean isLogged() {
		return usuarioLogueado != null;
	}

	public void logout() {
		usuarioLogueado = null;
	}
	
	public String getRolUsuario() {
		return "" + usuarioLogueado.getRol();
	}
	public String getNombreUsuario() {
		return usuarioLogueado.getUsuario();
	}
  
}
