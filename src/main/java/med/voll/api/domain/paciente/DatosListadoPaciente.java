package med.voll.api.domain.paciente;

public record DatosListadoPaciente(Long id, String nombre, String email, String documentoIdentidad, String telefono ) {
	
	public DatosListadoPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoidentidad(), paciente.getTelefono());
	}

}
