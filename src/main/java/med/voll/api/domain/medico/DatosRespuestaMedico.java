package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id, String nombre, String especialidad, String documento, String email, String telefono, DatosDireccion direccion) {

}
