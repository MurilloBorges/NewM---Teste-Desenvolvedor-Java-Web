package br.com.projeto.newM.cliente;

import java.util.Date;

public class ClienteVO {	
	
	private Long codCliente;
	private String nome;
	private Date nascimento;
	private Long cpf;
	private Long celular;
	private String email;
	private String endereco;
	private String observacao;
	
	public ClienteVO() {
		
	}
			
	public ClienteVO(Long codCliente, String nome, Date nascimento, Long cpf, Long celular, String email, String endereco, String observacao) {		
		this.codCliente = codCliente;
		this.nome = nome;
		this.nascimento = nascimento;
		this.cpf = cpf;
		this.celular = celular;
		this.email = email;
		this.endereco = endereco;
		this.observacao = observacao;
	}

	public Long getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public Long getCelular() {
		return celular;
	}
	public void setCelular(Long celular) {
		this.celular = celular;
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
