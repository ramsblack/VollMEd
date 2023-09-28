package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccionPaciente(
		@NotBlank
		String distrito,
		@NotBlank
		String ciudad,
		@NotBlank
		String numero,
		@NotBlank
		String complemento,
		@NotBlank
		String urbanizacion,
		@NotBlank
		String codigoPostal,
		@NotBlank
		String provincia
		) {

}
