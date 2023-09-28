package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

public record DatosAgendarConsulta(Long Id, Long idPaciente, Long idMedico, @NotNull @Future LocalDateTime fecha, Especialidad especialidad ) {

}
