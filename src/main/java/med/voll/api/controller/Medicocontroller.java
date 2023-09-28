package med.voll.api.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.DatosActualizarMedico;
import med.voll.api.domain.medico.DatosDeRegistroMedico;
import med.voll.api.domain.medico.DatosListadoMedico;
import med.voll.api.domain.medico.DatosRespuestaMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("/api/medicos")
public class Medicocontroller {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosDeRegistroMedico datosRegistroMedico,
			UriComponentsBuilder uriCompnentsBuilter) {
		Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
		DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),
				medico.getEspecialidad().toString(), medico.getDocumento(), new DatosDireccion(medico.getDireccion().getCalle(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getNumero()));
		URI url = uriCompnentsBuilter.path("/api/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(url).body(datosRespuestaMedico);
		
		
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size= 2) Pageable paginacion){
		//return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
		return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
		Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
		medico.actualizarDatos(datosActualizarMedico);
		return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),
				medico.getEspecialidad().toString(), medico.getDocumento(), new DatosDireccion(medico.getDireccion().getCalle(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getNumero())));
		
	}
	
	//DELETE LOGICO
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity borrarMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.desactivarMedico();
		return ResponseEntity.noContent().build();
		
	}
	
	//DELETE EN BASE DE DATOS
	//public void borrerMedico(@PathVariable Long id) {
		//Medico medico = medicoRepository.getReferenceById(id);
		//medicoRepository.delete(medico);
	//}
	
	@GetMapping("/{id}")
	
	public ResponseEntity RetornaUnMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		var datosMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),
				medico.getEspecialidad().toString(), medico.getDocumento(), new DatosDireccion(medico.getDireccion().getCalle(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getNumero()));
		return ResponseEntity.ok(datosMedico);
		
	}

}
