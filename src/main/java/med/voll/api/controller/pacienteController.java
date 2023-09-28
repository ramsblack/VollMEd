package med.voll.api.controller;

import java.net.URI;

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
import med.voll.api.domain.direccion.DatosDireccionPaciente;
import med.voll.api.domain.direccion.Direccionpaciente;
import med.voll.api.domain.paciente.DatosActualizarPaciente;
import med.voll.api.domain.paciente.DatosListadoPaciente;
import med.voll.api.domain.paciente.DatosRespuestaPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.datosDeRegistroPaciente;
import med.voll.api.domain.paciente.pacienteRepository;

@RestController
@RequestMapping("/api/pacientes")
public class pacienteController {

	@Autowired
	private pacienteRepository repository;
	
	@PostMapping
	public ResponseEntity<DatosRespuestaPaciente> registrarPaciente (@RequestBody @Valid datosDeRegistroPaciente datosDeregistroPaciente,
			UriComponentsBuilder uriCompnentsBuilter) {
		
	Paciente paciente =	repository.save(new Paciente(datosDeregistroPaciente));
	
	DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoidentidad(),
				paciente.getTelefono(), new DatosDireccionPaciente(paciente.getDireccion().getCiudad(), paciente.getDireccion().getCodigopostal(),
						paciente.getDireccion().getComplemento(), paciente.getDireccion().getDistrito(), paciente.getDireccion().getNumero(),
						paciente.getDireccion().getProvincia(),paciente.getDireccion().getUrbanizacion()));
	URI url = uriCompnentsBuilter.path("/api/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
	return ResponseEntity.created(url).body(datosRespuestaPaciente);
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosListadoPaciente>> listadoPacientes(@PageableDefault(size = 2) Pageable paginacion){
		return ResponseEntity.ok(repository.findByActivoTrue(paginacion).map(DatosListadoPaciente::new));
		//return repository.findAll(paginacion).map(DatosListadoPaciente::new);
		}
	
	@PutMapping
	@Transactional
	public ResponseEntity actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarpaciente) {
		Paciente paciente = repository.getReferenceById(datosActualizarpaciente.id());
		paciente.ActualizarDatos(datosActualizarpaciente);
		return ResponseEntity.ok(new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoidentidad(),
				paciente.getTelefono(), new DatosDireccionPaciente(paciente.getDireccion().getCiudad(), paciente.getDireccion().getCodigopostal(),
						paciente.getDireccion().getComplemento(), paciente.getDireccion().getDistrito(), paciente.getDireccion().getNumero(),
						paciente.getDireccion().getProvincia(),paciente.getDireccion().getUrbanizacion())));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity BorrarPaciente(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		paciente.desactivarPaciente();
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity rPaciente(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		var datosPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoidentidad(),
				paciente.getTelefono(), new DatosDireccionPaciente(paciente.getDireccion().getCiudad(), paciente.getDireccion().getCodigopostal(),
						paciente.getDireccion().getComplemento(), paciente.getDireccion().getDistrito(), paciente.getDireccion().getNumero(),
						paciente.getDireccion().getProvincia(),paciente.getDireccion().getUrbanizacion()));
		return ResponseEntity.ok(datosPaciente);
		
	}
	
	
}
