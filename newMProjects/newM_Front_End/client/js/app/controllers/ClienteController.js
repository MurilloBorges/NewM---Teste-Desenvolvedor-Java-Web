class ClienteController {
    
    constructor() {
        
        let $ = document.querySelector.bind(document);         

        this._filtro = $('#filtro');
        this.codCliente = 0;
        this._inputNome = $('#nome');
        this._inputNascimento = $('#nascimento');
        this._inputCpf = $('#cpf');
        this._inputCelular = $('#celular');
        this._inputEmail = $('#email');
        this._inputEndereco = $('#endereco');
        this._inputObservacao = $('#observacao');
         
        this._listaClientes = new Bind(
            new ListaClientes(), new ClientesView($('#clientesView')), 'adiciona', 'esvazia' , 'ordena', 'inverteOrdem'
        );       
        this._mensagem = new Bind(new Mensagem(), new MensagemView($('#mensagemView')), 'texto');                
        this._ordemAtual = ''               
    }    

    validaCampo(event) {       
        event.preventDefault();
        try {
            if (this._inputNome.value == '' || this._inputNascimento.value == '' || this._inputCpf.value == '' ||
                this._inputCelular.value == '' || this._inputEmail.value == '' || this._inputEndereco.value == '') {
                throw new Error('Preenchar os campo corretamente.');
            }
            else {
                if (this.codCliente != 0) {
                    this.atualizarCliente();                    
                } else {
                    this.salvarCliente();
                }                
            }
        } catch(erro) {
            this._mensagem.texto = erro;
        }
    }
    
    buscarClientes() {        
        let service = new ClienteService();        
        this._limpaFormulario();
        this._listaClientes.esvazia();        
        service.listarClientes(this._filtro.value)
            .then(clientes => clientes.forEach(cliente => {
                this._listaClientes.adiciona(cliente);                                 
            }))
            .catch(erro => this._mensagem.texto = 'Nenhum cliente encontrato.');                              
    }
    
    salvarCliente() {
        let service = new ClienteService();
        service.salvarCliente(this._criaCliente())                            
        this._mensagem.texto = 'Cliente salvo com sucesso.';
        this.buscarClientes();
    }

    atualizarCliente() {
        let service = new ClienteService();
        service.atualizarCliente(this._criaCliente())            
        this._mensagem.texto = 'Cliente atualizado com sucesso.';
        this.buscarClientes();
    }

    apagarCliente() {        
        let service = new ClienteService();
        service.removerCliente(this.codCliente)
        this._mensagem.texto = 'Cliente removido com sucesso.';
        this.buscarClientes();
    }
    
    _criaCliente() {        
        return new Cliente(
            this.codCliente,
            this._inputNome.value,
            DateHelper.textoParaData(this._inputNascimento.value),
            this._inputCpf.value,
            this._inputCelular.value,
            this._inputEmail.value,
            this._inputEndereco.value,
            this._inputObservacao.value
        );    
    }
    
    _limpaFormulario() { 
        this.codCliente = 0;   
        this._inputNome.value = '';
        this._inputNascimento.value = '';
        this._inputCpf.value = '';
        this._inputCelular.value = '';
        this._inputEmail.value = '';
        this._inputEndereco.value = '';
        this._inputObservacao.value = '';
        this._inputNome.focus;        
    }
    
    ordena(coluna) {        
        if(this._ordemAtual == coluna) {
            this._listaClientes.inverteOrdem(); 
        } else {
            this._listaClientes.ordena((p, s) => p[coluna] - s[coluna]);    
        }
        this._ordemAtual = coluna;    
    }

    preencherForm(tr) { 
        this.codCliente = tr.cells[0].textContent;        
        this._inputNome.value = tr.cells[1].textContent;
        this._inputNascimento.value = tr.cells[2].textContent;
        this._inputCpf.value = tr.cells[3].textContent;
        this._inputCelular.value = tr.cells[4].textContent;
        this._inputEmail.value = tr.cells[5].textContent;
        this._inputEndereco.value = tr.cells[6].textContent;
        this._inputObservacao.value = (tr.cells[7].textContent != "null" ? tr.cells[7].textContent : '');
        this._inputNome.focus;
    }
}