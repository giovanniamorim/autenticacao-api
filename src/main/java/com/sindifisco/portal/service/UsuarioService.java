package com.sindifisco.portal.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sindifisco.portal.model.Usuario;
import com.sindifisco.portal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listarTodos(){
		return usuarioRepository.findAll();
	}

	public Usuario buscaPorId(Long id) {
		return usuarioRepository.findOne(id);
	}

	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioAtualizado = buscaPorId(id);
		BeanUtils.copyProperties(usuario, usuarioAtualizado, "id");
		return usuarioRepository.save(usuarioAtualizado);
	}

	public void delete(Long id) {
		usuarioRepository.delete(id);
		
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Usuario usuarioAtualizado = buscarUsuarioAtualizadoPorCodigo(id);
		usuarioAtualizado.setAtivo(ativo);
		usuarioRepository.save(usuarioAtualizado);
	}
	
	
	private Usuario buscarUsuarioAtualizadoPorCodigo (Long id) {
		Usuario usuarioAtualizado = usuarioRepository.findOne(id);
		if(usuarioAtualizado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioAtualizado;
	}
	
	
	

	
}
