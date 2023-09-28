package med.voll.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;

@Controller
@ResponseBody
@RequestMapping("/api/consultas")
public class ConsultaController {

	private AgendaDeConsultaService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
		
		service.agendar(datos);
		
		return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));
	}
}