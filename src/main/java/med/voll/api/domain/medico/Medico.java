package med.voll.api.domain.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "Medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data



public class Medico {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String telefono;
	private String documento;
	private Boolean activo;
	
	@Enumerated(EnumType.STRING)
	private Especialidad especialidad;
	@Embedded
	private Direccion direccion;
	
	public Medico(DatosDeRegistroMedico datosRegistroMedico) {
		this.activo = true;
		this.nombre = datosRegistroMedico.nombre();
		this.email = datosRegistroMedico.email();
		this.telefono = datosRegistroMedico.telefono();
		this.documento = datosRegistroMedico.documento();
		this.especialidad = datosRegistroMedico.especialidad();
		this.direccion = new Direccion(datosRegistroMedico.direccion());


	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Long getId() {
		return id;
	}



	public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
		if(datosActualizarMedico.nombre() != null) {
			this.nombre = datosActualizarMedico.nombre();
		}
		if(datosActualizarMedico.documento() != null) {
			this.documento = datosActualizarMedico.documento();
		}
		if(datosActualizarMedico.direccion() != null) {
			this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
		}

		
	}



	public void desactivarMedico() {
		this.activo = false;
		
	}
	
}
