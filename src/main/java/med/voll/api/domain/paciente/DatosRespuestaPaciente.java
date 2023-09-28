package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.DatosDireccionPaciente;

public record DatosRespuestaPaciente(Long id, String nombre, String email, String documentoIdentidad, String telefono, DatosDireccionPaciente direccion) {

	
}
