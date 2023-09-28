package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccionpaciente {
	

	private String numero;
	private String complemento;
	private String distrito;
	private String ciudad;
	private String urbanizacion;
	private String codigopostal;
	private String provincia;
	
	
	
	
	
	public Direccionpaciente(DatosDireccionPaciente direccion) {
		this.numero = direccion.numero();
		this.complemento = direccion.complemento();
		this.distrito= direccion.distrito();
		this.ciudad= direccion.ciudad();
		this.urbanizacion = direccion.urbanizacion();
		this.codigopostal = direccion.codigoPostal();
		this.provincia = direccion.provincia();
		}
	
	public Direccionpaciente actualizarDatosPaciente(DatosDireccionPaciente direccion) {
		this.numero = direccion.numero();
		this.complemento = direccion.complemento();
		this.distrito= direccion.distrito();
		this.ciudad= direccion.ciudad();
		this.urbanizacion = direccion.urbanizacion();
		this.codigopostal = direccion.codigoPostal();
		this.provincia = direccion.provincia();
		return this;
	}
}