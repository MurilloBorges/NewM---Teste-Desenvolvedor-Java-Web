class ListaClientes {

    constructor() {        
        this._clientes = [];
    }

    adiciona(cliente) {        
        this._clientes.push(cliente);
    }
    get clientes() {        
        return [].concat(this._clientes);
    }
    esvazia() {        
        this._clientes = [];
    }
    ordena(criterio) {
        this._clientes.sort(criterio);        
    }
    inverteOrdem() {
        this._clientes.reverse();
    }    
}