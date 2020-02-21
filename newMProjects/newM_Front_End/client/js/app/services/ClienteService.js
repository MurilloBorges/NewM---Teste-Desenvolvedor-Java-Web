class ClienteService {
    
    constructor() {        
        this._http = new HttpService();
    }
    
    listarClientes(filtro) {               
        let param = (filtro != undefined && filtro != '' ? '?pesquisa=' + filtro : '');
        return this._http.get('http://localhost:8080/clientes' + param)
            .then(clientes => {                
                return clientes.map(objeto => 
                    new Cliente(objeto.codCliente, objeto.nome, new Date(objeto.nascimento), objeto.cpf,
                        objeto.celular, objeto.email,objeto.endereco,objeto.observacao                        
                    )
                );
            }).catch(erro => {                
                throw new Error('falhar ao listar Clientes.');
            });  
    }           

    salvarCliente(cliente) {        
        let clienteEnvio = {
            nome: cliente.nome,
            nascimento: cliente.nascimento,
            cpf: cliente.cpf.replace(/\.|\-/g, ""),
            celular: cliente.celular.replace(/[\(\)\.\s-]+/g, ""),
            email: cliente.email,
            endereco: cliente.endereco,
            observacao: cliente.observacao
        }; 
        return this._http.post('http://localhost:8080/clientes', clienteEnvio)
        .catch(erro => {                
            throw new Error('falhar ao salvar Cliente.');
        });
    }

    atualizarCliente(cliente) {              
        let clienteEnvio = {
            nome: cliente.nome,
            nascimento: cliente.nascimento,
            cpf: cliente.cpf.replace(/\.|\-/g, ""),
            celular: cliente.celular.replace(/[\(\)\.\s-]+/g, ""),
            email: cliente.email,
            endereco: cliente.endereco,
            observacao: cliente.observacao
        };
        return this._http.patch('http://localhost:8080/clientes/' + cliente.codCliente, clienteEnvio)
        .catch(erro => {                
            throw new Error('falhar ao atualizar Cliente.');
        });
    }

    removerCliente(codCliente) {
        return this._http.delete('http://localhost:8080/clientes/' + codCliente)
        .catch(erro => {                
            throw new Error('falhar ao remover Cliente.');
        });
    }
}
