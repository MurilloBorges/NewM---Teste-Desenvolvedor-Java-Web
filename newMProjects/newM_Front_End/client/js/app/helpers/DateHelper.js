class DateHelper {
    
    constructor() {        
        throw new Error('Esta classe não pode ser instanciada');
    }
    
    static dataParaTexto(data) {        
        return `${leftPad(data.getDate(), 2)}/${leftPad(data.getMonth()+1, 2)}/${data.getFullYear()}`;
    }
    
    static textoParaData(texto) {        
        if(!/\d{2}\/\d{2}\/\d{4}/.test(texto)) {
            throw new Error('Deve estar no formato dd/mm/aaaa');             
        }
        return new Date(...texto.split('/').reverse().map((item, indice) => item - indice % 2));
    }
}