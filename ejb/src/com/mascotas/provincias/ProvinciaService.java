package com.mascotas.provincias;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.provincias.dto.ProvinciaDTO;
import com.mascotas.provincias.repository.ProvinciasRepository;

/**
 * Servicio del negocio de Provincias.
 * 
 * @author Nestor
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProvinciaService {

	@EJB
	private ProvinciasRepository provinciaRepository;

	/**
	 * Busca una provincia por su id.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ProvinciaDTO findById(Integer id) {
		return ProvinciaDTO.Factory.get(provinciaRepository.get(id));
	}

	/**
	 * Retorna todas las provincias del sistema.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<ProvinciaDTO> findAll() {
		return ProvinciaDTO.Factory.get(provinciaRepository.getAll());
	}

}
