package co.edu.unbosque.revista.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.edu.unbosque.revista.dto.ArticuloDTO;
import co.edu.unbosque.revista.dto.UsuarioDTO;
import co.edu.unbosque.revista.entity.Articulo;
import co.edu.unbosque.revista.entity.Usuario;
import co.edu.unbosque.revista.repository.UsuarioRepository;
import co.edu.unbosque.revista.security.JwtUtil;

@Service
public class UsuarioService implements CRUDOPERATION<UsuarioDTO> {

	@Autowired
	private UsuarioRepository uRep;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Usuario usuarioLogueado;

	@Override
	public int create(UsuarioDTO data) {
		Optional<Usuario> encontrado = uRep.findByUsuario(data.getUsuario());
		if (encontrado.isPresent())
			return 2;

		Usuario entity = mapper.map(data, Usuario.class);
		entity.setContrasenia(passwordEncoder.encode(data.getContrasenia()));
		uRep.save(entity);
		return 0;
	}

	@Override
	public String getAll() {
		Gson gson = new Gson();
		List<Usuario> entityList = (List<Usuario>) uRep.findAll();
		List<UsuarioDTO> dtoList = new ArrayList<>();
		entityList.forEach(entity -> dtoList.add(mapper.map(entity, UsuarioDTO.class)));
		return gson.toJson(dtoList);
	}

	@Override
	public int deleteById(Long id) {
		if (uRep.existsById(id)) {
			uRep.deleteById(id);
			return 0;
		} else {
			return 1;
		}

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
		Optional<Usuario> encontrado = uRep.findById(id);
		if (encontrado.isPresent()) {
			if (data.getRol().toString() == "ADMIN") {
				return 1;
			} else {
				UsuarioDTO temp = mapper.map(encontrado.get(), UsuarioDTO.class);
				temp.setUsuario(data.getUsuario());
				temp.setContrasenia(data.getContrasenia());
				temp.setRol(data.getRol());
				Usuario entity = mapper.map(temp, Usuario.class);
				entity.setId(id);
				uRep.save(entity);
				return 0;
			}
		} else {
			return 2;
		}
	}

	public String login(String usuario, String contrasenia) {
		Optional<Usuario> encontrado = uRep.findByUsuario(usuario);

		if (!encontrado.isPresent()) {
			return "USER_NOT_FOUND";
		}
		if (!passwordEncoder.matches(contrasenia, encontrado.get().getContrasenia())) {
			return "WRONG_PASSWORD";
		}
		return jwtUtil.generateToken((UserDetails) encontrado.get());
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
