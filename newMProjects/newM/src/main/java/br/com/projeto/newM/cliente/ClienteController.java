package br.com.projeto.newM.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;
		
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity listar(@RequestParam(required=false) String pesquisa) {
		try {
			return ResponseEntity.ok().body(service.listar(pesquisa));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}		
	}
	
	@GetMapping(value = "/{codCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity listar(@PathVariable Long codCliente) {
		try {			
			return ResponseEntity.ok().body(service.listar(codCliente));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}				
	}				
		
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity salvar(@RequestBody ClienteVO clienteVO) {
		try {
			//criar location (para direcionar para o get) para usar status created
			return ResponseEntity.ok().body(service.salvar(clienteVO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}						
	}	
		
	@PatchMapping(value = "/{codCliente}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity atualizar(@PathVariable Long codCliente, @RequestBody ClienteVO clienteVO) {
		try {
			return ResponseEntity.ok().body(service.atualizar(codCliente, clienteVO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}			
	}	
	
	@DeleteMapping(value = "/{codCliente}")
	public ResponseEntity remover(@PathVariable Long codCliente) {
		try {			
			return ResponseEntity.ok().body(service.remover(codCliente));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
}
