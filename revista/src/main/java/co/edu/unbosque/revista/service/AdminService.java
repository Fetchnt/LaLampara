package co.edu.unbosque.revista.service;

import org.springframework.stereotype.Service;

import co.edu.unbosque.revista.dto.AdminDTO;

@Service
public class AdminService implements CRUDOPERATION<AdminDTO> {

	@Override
	public int create(AdminDTO data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int updateById(Long id, AdminDTO data) {
		// TODO Auto-generated method stub
		return 0;
	}

}
