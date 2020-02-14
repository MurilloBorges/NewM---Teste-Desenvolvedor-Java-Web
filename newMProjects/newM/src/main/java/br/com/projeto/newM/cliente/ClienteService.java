package br.com.projeto.newM.cliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class ClienteService {

	@PersistenceContext
	private EntityManager em;
	
	public List<ClienteVO> listar(String pesquisa) throws Exception {
    	try {
    		StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("SELECT new br.com.projeto.newM.cliente.ClienteVO(o.codCliente, o.nome, o.nascimento, ");
			sbQuery.append("o.cpf, o.celular, o.email, o.endereco, o.observacao) ");
			sbQuery.append("FROM Cliente o ");
			
			if (!StringUtils.isEmpty(pesquisa)) {
				sbQuery.append("WHERE lower(o.nome) LIKE lower('%").append(pesquisa).append("%') ");
				sbQuery.append("or lower(o.email) LIKE lower('%").append(pesquisa).append("%') ");				
			}			
			sbQuery.append("ORDER BY o.codCliente DESC");
			
			return em.createQuery(sbQuery.toString(), ClienteVO.class).setMaxResults(10).getResultList();			    					    	
    	} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha ao listar clientes.");
		}
    }
	
	public ClienteVO listar(Long codCliente) throws Exception {
    	try {
    		StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("SELECT new br.com.projeto.newM.cliente.ClienteVO(o.codCliente, o.nome, o.nascimento, ");
			sbQuery.append("o.cpf, o.celular, o.email, o.endereco, o.observacao) ");
			sbQuery.append("FROM Cliente o ");
			sbQuery.append("WHERE o.codCliente = :codCliente");
			
			return em.createQuery(sbQuery.toString(), ClienteVO.class).setParameter("codCliente", codCliente).getSingleResult();
    	} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha ao listar cliente.");
		}
    }

	@Transactional
	public ClienteVO salvar(ClienteVO clienteVO) throws Exception {
		try {	
			Query query = em.createNativeQuery("SELECT nextval('public.cliente_seq')");					
			clienteVO.setCodCliente(Long.valueOf(query.getSingleResult().toString()));
			
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("INSERT INTO public.cliente ");
			sbQuery.append("(cod_cliente, nome, nascimento, cpf, celular, email, endereco, observacao) ");
			sbQuery.append("VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8) ");
			query = em.createNativeQuery(sbQuery.toString());
			query.setParameter(1, clienteVO.getCodCliente());
			query.setParameter(2, clienteVO.getNome());
			query.setParameter(3, clienteVO.getNascimento());
			query.setParameter(4, clienteVO.getCpf());
			query.setParameter(5, clienteVO.getCelular());
			query.setParameter(6, clienteVO.getEmail());
			query.setParameter(7, clienteVO.getEndereco());
			query.setParameter(8, clienteVO.getObservacao());
			query.executeUpdate();
						
			return clienteVO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha ao salvar cliente.");
		}		
	}
	
	@Transactional
	public ClienteVO atualizar(Long codCliente, ClienteVO clienteVO) throws Exception {
		try {			
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("UPDATE public.cliente SET ");
			if (clienteVO.getNome() != null) {
				sbQuery.append("nome = " + "'" + clienteVO.getNome() + "'");
				if (clienteVO.getNascimento() != null || clienteVO.getCpf() != null || clienteVO.getCelular() != null ||
					clienteVO.getEmail() != null || clienteVO.getEndereco() != null || clienteVO.getObservacao() != null) {
					sbQuery.append(", ");					
				}
			}
			if (clienteVO.getNascimento() != null) {
				sbQuery.append("nascimento = " + "'" + clienteVO.getNascimento() + "'");
				if (clienteVO.getCpf() != null || clienteVO.getCelular() != null || clienteVO.getEmail() != null || 
					clienteVO.getEndereco() != null || clienteVO.getObservacao() != null) {
					sbQuery.append(", ");					
				}
			}
			if (clienteVO.getCpf() != null) {				
				sbQuery.append("cpf = " + clienteVO.getCpf());
				if (clienteVO.getCelular() != null || clienteVO.getEmail() != null || clienteVO.getEndereco() != null || clienteVO.getObservacao() != null) {
					sbQuery.append(", ");					
				}
			}
			if (clienteVO.getCelular() != null) {
				sbQuery.append("celular = " + clienteVO.getCelular());
				if (clienteVO.getEmail() != null || clienteVO.getEndereco() != null || clienteVO.getObservacao() != null) {
					sbQuery.append(", ");					
				}
			}			
			if (clienteVO.getEmail() != null) {
				sbQuery.append("email = " + "'" + clienteVO.getEmail() + "'");
				if (clienteVO.getEndereco() != null || clienteVO.getObservacao() != null) {
					sbQuery.append(", ");					
				}
			}			
			if (clienteVO.getEndereco() != null) {
				sbQuery.append("endereco = " + "'" + clienteVO.getEndereco() + "'");
				if (clienteVO.getObservacao() != null) {
					sbQuery.append(", ");					
				}
			}
			if (clienteVO.getObservacao() != null) {
				sbQuery.append("observacao = " + "'" + clienteVO.getObservacao() + "'");
			}			
			sbQuery.append(" WHERE cod_cliente = ?1 ");
			Query query = em.createNativeQuery(sbQuery.toString());								
			query.setParameter(1, codCliente);
			query.executeUpdate();
									
			return listar(codCliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha ao atualizar cliente.");
		}		
	}
	
	@Transactional
	public Long remover(Long codCliente) throws Exception {
		try {
			Query query = em.createNativeQuery("DELETE FROM public.cliente WHERE cod_cliente = ?1");
			query.setParameter(1, codCliente);
			query.executeUpdate();
			
			return codCliente;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha ao remover cliente.");			
		}
	}
}
