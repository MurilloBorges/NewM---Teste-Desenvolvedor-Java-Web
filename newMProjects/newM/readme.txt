O banco utilizado para salvar os dados foi PostgreSQL;

Fiquei em dúvida quanto aos solicitados:
- Não gerar o script do banco pela IDE.
- Não utilizar persistência e frameworks de banco de dados.

Para o primeiro criei o script da tabela a parte, porém mepeei a mesma no JPA.

Para o segundo, poderia ter utilizado diversas formas mais fáceis acredito eu, para persistir as informações no banco, 
porém pelo solicitado e pela dúvida que me veio optei por usar uma implementação do JPA que é a native query, não sei 
se a mesma se enquadra nos requisitos das dúvidas citadas acima por se usar da notação:
@PersistenceContext
private EntityManager em;


Funcionalidade
1 - Implementada máscara nos campos CPF, Celular e Data de nasciento;
2 - Implementada função de validar CPF;
3 - Implementada função para não permitir caracteres especiais no nome;

CRUD (
	4 - O filtro de busca funciona da seguinte maneira:
		-> Não possui filtro de pesquisa, trás tudo (limitado a 10 registro, sendo eles os últios 10 inseridos);
		-> Possui filtro de pesquisa, trás os resultados com base no nome e no email (faz uso de like e case-insensitive);
	4 - Para incluir um registro, o formulário deve estar vazio e ser preenchido e então efetuar o clique no botão incluir;
	5 - Para alterar o registro, basta dar double click na tabela, que o registro é carregado no formuláro,
	efetua-se a alteração desejada e então clique no botão salvar;
	6 - Para remover o registro a ideia é a mesma que a de alterar, carrega o registro no formulário atráves do
	double click na tabela e então clique no botão remover;
	
	Obs: toda vez  que é feita uma operação de inclusão, alteração ou exclusão a busca dos registro é efetuada novamente;
)

Obs: 
1 - O Back End está pronto (foi utilizado Spring Boot), porém com o tempo seriam feitas algumas refatorações, como criar 
uma classe de exception para os endpoint, otimizar o update, insert e etc.
2 - O Front end teria que ser feita também uma refatoração e alguns ajustes quanto a mensagem, dinâmica, tratamento de exceções e etc.
