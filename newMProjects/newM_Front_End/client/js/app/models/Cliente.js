class Cliente {
    
    constructor(codCliente, nome, nascimento, cpf, celular, email, endereco, observacao) {        
        this._codCliente = codCliente;
        this._nome = nome;
        this._nascimento = new Date(nascimento.getTime());
        this._cpf = cpf;
        this._celular = celular;
        this._email = email;
        this._endereco = endereco;
        this._observacao = observacao;
        Object.freeze(this);
    }

    get codCliente() {
        return this._codCliente;
    }
    get nome() {
        return this._nome;
    } 
    get nascimento() {        
        return new Date(this._nascimento.getTime());
    }    
    get cpf() {
        return this._cpf;
    }
    get celular() {
        return this._celular;
    }
    get email() {
        return this._email;
    }
    get endereco() {
        return this._endereco;
    }
    get observacao() {
        return this._observacao;
    }
}