package com.mascotas.mascotas;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.application.exceptions.ValidationError;
import com.mascotas.application.utils.StringUtils;
import com.mascotas.mascotas.dto.MascotaDTO;
import com.mascotas.mascotas.entities.Mascota;
import com.mascotas.mascotas.repository.MascotaRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;

/**
 * Servicios de acceso a las Mascotas del Usuario
 * 
 * @author nestor
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MascotaService {
	@EJB
	private UsuariosRepository usuarioRepository;

	@EJB
	private MascotaRepository mascotaRepository;

	@EJB
	private MascotaServiceValidations mascotaValidations;

	/**
	 * Busca las Mascotas del usuario por login de usuario.
	 * 
	 */
	public List<MascotaDTO> findForLogin(String login) throws BusinessException {
		List<ValidationError> errors = mascotaValidations.validarFindForLogin(login);
		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}

		List<Mascota> mascotas = mascotaRepository.getByUsuario(login);

		return MascotaDTO.Factory.get(mascotas);
	}

	/**
	 * Actualiza los datos basicos de las mascotas.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int actualizarMascota(String login, MascotaDTO mascota) throws BusinessException {
		List<ValidationError> errors = mascotaValidations.validarActualizarMascota(login, mascota);
		if (errors.size() > 0) {
			throw new BusinessException("Error al guardar mascota.", errors);
		}

		Mascota mascotaEditada = null;
		if (mascota.getId() != null) {
			mascotaEditada = mascotaRepository.get(mascota.getId());
		}

		if (mascotaEditada == null) {
			Usuario usuario = usuarioRepository.get(login);

			mascotaEditada = new Mascota();
			mascotaEditada.setUsuario(usuario);
			mascotaEditada.setNombre(mascota.getNombre());
			mascotaRepository.add(mascotaEditada);
		}

		mascotaEditada.setDescripcion(mascota.getDescripcion());
		try {
			mascotaEditada.setFechaNacimiento(StringUtils.DATE_FORMAT.parse(mascota.getFechaNacimiento()));
		} catch (Exception e) {
		}
		mascotaEditada.setNombre(mascota.getNombre());
		mascotaEditada.setImagen(mascota.getImagen());
		
		return mascotaEditada.getId();
	}

	public MascotaDTO findById(String login, Integer id) throws BusinessException {
		List<ValidationError> errors = mascotaValidations.validarFindById(login, id);

		if (errors.size() > 0) {
			throw new BusinessException("Error al recuperar la Mascota.", errors);
		}

		return MascotaDTO.Factory.get(mascotaRepository.get(id));
	}

	public void eliminarMascota(String name, Integer id) {
		List<ValidationError> errors = mascotaValidations.validarEliminarMascota(name, id);
		if (errors.size() > 0) {
			throw new BusinessException("Error al eliminar la Mascota.", errors);
		}

		
		mascotaRepository.remove(mascotaRepository.get(id));
	}
}
