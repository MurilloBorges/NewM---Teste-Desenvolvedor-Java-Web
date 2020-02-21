$(document).ready(function(){
	$("#cpf").mask("999.999.999-99");
});

$(document).ready(function(){
    $("#celular").mask("(99) 99999-9999");
});

$(document).ready(function(){
    $("#nascimento").mask("99/99/9999");
});

function ValidarCPF(Objcpf){
    var cpf = Objcpf.value;
    exp = /\.|\-/g;
    cpf = cpf.toString().replace( exp, "" ); 
    var digitoDigitado = eval(cpf.charAt(9)+cpf.charAt(10));
    var soma1=0, soma2=0;
    var vlr =11;

    for(i=0;i<9;i++){
        soma1+=eval(cpf.charAt(i)*(vlr-1));
        soma2+=eval(cpf.charAt(i)*vlr);
        vlr--;
    }       
    soma1 = (((soma1*10)%11)==10 ? 0:((soma1*10)%11));
    soma2=(((soma2+(2*soma1))*10)%11);

    var digitoGerado=(soma1*10)+soma2;
    if(digitoGerado!=digitoDigitado) {
        alert('CPF Invalido!');   
        $('#cpf').val("");    
    }  
}

function leftPad(value, totalWidth, paddingChar) {
    var length = totalWidth - value.toString().length + 1;
    return Array(length).join(paddingChar || '0') + value;
};

function alpha(e) {
    var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 192 && k <= 382));
}