package med.voll.api.domain.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.direccion.Direccionpaciente;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "paciente")
@Table(name = "pacientes")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String email;
	private String documentoidentidad;
	private String telefono;
	private Boolean activo;
	@Embedded
	private Direccionpaciente direccion;
	
	public Paciente(datosDeRegistroPaciente datosderegistroPaciente) {
		this.activo = true;
		this.nombre = datosderegistroPaciente.nombre();
		this.email = datosderegistroPaciente.email();
		this.documentoidentidad = datosderegistroPaciente.documentoIdentidad();
		this.telefono = datosderegistroPaciente.telefono();
		this.direccion = new Direccionpaciente(datosderegistroPaciente.direccion());
		
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDocumentoidentidad(String documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void ActualizarDatos(DatosActualizarPaciente datosActualizarpaciente) {
		if(datosActualizarpaciente.nombre()!= null) {
			this.nombre = datosActualizarpaciente.nombre();
		}
		if(datosActualizarpaciente.documento() != null) {
			this.documentoidentidad = datosActualizarpaciente.documento();
		}
		if(datosActualizarpaciente.direccion() != null) {
			this.direccion = direccion.actualizarDatosPaciente(datosActualizarpaciente.direccion());
		}
		
	}

	public void desactivarPaciente() {
		this.activo = false;
		
	}

	
	

	

}
