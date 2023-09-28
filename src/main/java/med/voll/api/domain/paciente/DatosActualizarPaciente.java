package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.direccion.DatosDireccionPaciente;

public record DatosActualizarPaciente(@NotNull Long id, String nombre, String documento, @Valid DatosDireccionPaciente direccion) {

}
