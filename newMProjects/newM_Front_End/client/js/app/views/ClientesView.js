class ClientesView extends View {
    
    constructor(elemento) {        
        super(elemento);
    }
    
    template(model) {        
        return `
        <table class="table table-hover table-bordered">        
            <thead>
                <tr>
                    <th onclick="clienteController.ordena('nome')">NOME</th>
                    <th onclick="clienteController.ordena('nascimento')">DATA DE NASCIMENTO</th>
                    <th onclick="clienteController.ordena('cpf')">CPF</th>
                    <th onclick="clienteController.ordena('celular')">CELULAR</th>
                    <th onclick="clienteController.ordena('email')">E-MAIL</th>
                    <th onclick="clienteController.ordena('endereco')">ENDEREÇO</th>
                </tr>
            </thead>        
            <tbody>
                ${model.clientes.map(n => `                    
                    <tr ondblclick="clienteController.preencherForm(this)">
                        <td id="tdCodCliente" style="visibility: hidden; display:none;">${n.codCliente}</td>
                        <td id="tdNome">${n.nome}</td>
                        <td id="tdNascimento">${DateHelper.dataParaTexto(n.nascimento)}</td>
                        <td id="tdCPF">${n.cpf}</td>
                        <td id="tdCelular">${n.celular}</td>
                        <td id="tdEmail">${n.email}</td>
                        <td id="tdEndereco">${n.endereco}</td>
                        <td id="tdObservacao" style="visibility: hidden; display:none;">${n.observacao}</td>
                    </tr>                    
                `).join('')}                
            </tbody>                                          
        </table>
        `;
    }
}