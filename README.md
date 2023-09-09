# api3-plugin
Avaliação 3 formação DEV backend 2023 Insoft4

# Desafio 3 Grails
## Requisitos
O desafio deve ser desenvolvido com Grails 5 e JDK 11;
Editor de texto ou IDE de sua escolha;
Banco de dados Oracle, utilizar owners da etapa de PL/SQL;
Postman para realizar as requisições.

## Implementação
Neste desafio, será desenvolvido uma aplicação que irá consumir a api2 desenvolvida no desafio anterior eum plugin para registrar os logs das interações entre as APIs.

### Etapa 1 - Plugin
Criação do projeto
O projeto deve ser criado com o comando create-plugin com o nome api3-plugin, utilize o profile rest-api-plugin.
Após gerar o projeto, altere as seguintes linhas do build.gradle:
>  apply plugin:"org.grails.grails-plugin-publish"
>
>  //...
>
>  provided "org.springframework.boot:spring-boot-starter-tomcat"
>
Para:
>  apply plugin:"maven-publish"
> 
>  //...
> 
>  compileOnly "org.springframework.boot:spring-boot-starter-tomcat"
> 

#### Configuração de publish local
Para o plugin ser utilizado na api3 ele deverá ser publicado localmente. Para configurar a publicação, adicioneo seguinte trecho ao fim do arquivo build.gradle:
>     publishing {
> 
>       publications {
> 
>         maven(MavenPublication) {
> 
>           versionMapping {
> 
>             usage('java-api') {
> 
>               fromResolutionOf('runtimeClasspath')
> 
>             }
> 
>             usage('java-runtime') {
> 
>               fromResolutionResult()
> 
>             }
> 
>           }
> 
>           from components.java
> 
>         }
> 
>       }
> 
>     }
> 
Após isso, dentro da pasta do projeto, execute o seguinte comando no terminal para publicar o pluginlocalmente:
> 
> ./gradlew publishToMavenLocal
> 

Para cada vez que for publicar o plugin utilizando o comando acima, deverá ser alterado antes apropriedade version no build.gradle de maneira incremental, por exemplo:
Primeira publicação -> executar comando
Segunda publicação -> alterar versão para 0.2 -> executar comando
Terceira publicação -> alterar versão para 0.3 -> executar comando etc.
Consequentemente deve ser alterada a versão da dependência na api3.

#### Classe de domínio
A classe deverá utilizar o gerador de id increment;
O versionamento deve estar desabilitado na classe;
Devem também ser mapeados os atributos abaixo com suas respectivas constraints:
1.Log

Atributo  Tipo      Nulo  Tamanho
data      LocalDate Não   
descricao String    Não   1000

#### Service
Deverá ser implementado um service chamado LogService, nele deve ser criado o método salvarLog que será responsável por gravar os dados na tabela de logs.

### Etapa 2 - Aplicação
#### Criação do projeto
O projeto deve ser criado com o comando create-app com o nome api3, utilize o profile rest-api.

#### Conexão do banco de dados
Utilize as mesmas configurações do desafio 2.

#### Mapeamento de URLs
Utilize os mesmos mapeamentos do desafio 2.

#### Context-path
Utilize a mesma configuração de contexto da aplicação do desafio 2.

#### Server Port
A api3 deverá ser executada na porta 8180, para configurar a porta padrão, adicione o seguinte trecho ao application.yml:
>     server:
> 
>       port:
> 
>         8180
> 

#### Dependências
Após finalizar a etapa 1, adicione o plugin como dependência ao projeto, faça as seguintes alterações no build.gradle:
> 
>   // Adicionar o repositório local a lista de respositórios
> 
>   repositories {
> 
>     mavenLocal() // <- Adicione essa linha
> 
>   // ...
> 
>   }
> 
>   // ...
>   
>   // Adicione a dependência do api3-plugin
> 
>   dependencies {
> 
>     implementation "api3.plugin:api3-plugin:0.1"
> 
>     // ...
> 
>   }
> 

#### Endpoints
A função da api3 será servir de "ponte" para a api2, desenvolvida no desafio anterior. Portanto:
1. A api3 irá receber os dados da requisição;
2. A api3 deverá repassar os dados para a api2;
3. A api2 irá processar e retornar os dados para a api3;
4. Por fim, a api3 deverá retornar a resposta da api2 para quem fez a requisição.
Adicionalmente a api3 terá que gravar na tabela de logs as respostas (JSON) de todos os controllers da api2 e somente das ações save , update e delete.

Para a api3 se comunicar com a api2, sugerimos o plugin grails-datastore-rest-client, para adiciona-lo aoprojeto altere o build.gradle como demonstrado abaixo:
>     dependencies {
> 
>       implementation "org.grails:grails-datastore-rest-client:6.1.9.RELEASE"
> 
>       // ...
> 
>     }
> 

Utilize este guia (https://github.com/grails-plugins/grails-rest-client-builder#basic-usage) para implementar a comunicação entre as APIs.
Podem ser utilizadas outras bibliotecas para realizar as requisições se desejado.

## Regras
Cada participante deverá utilizar a sua implementação da api2 para a integração, a avaliação dafuncionalidade será feita utilizando o repositório enviado no Desafio 2;
Commits na api2, api3 e plugin feitos posteriormente ao prazo final de entrega não serão consideradosna avaliação;
Toda e qualquer regra de negócio deve ser implementada na camada de service;
É completamente proibida a cópia de codigo entre os participantes;
Não devem ser usadas IAs para programar o código por você, implemente-o você;
As ações de CRUD devem ser implementadas por você, sem utilizar a lógica capenga já feita pelo Grails;
A lógica deve estar implementada única e exclusivamente no código fonte da aplicação, não utilizandoprocessos internos no banco de dados;
Todos os retornos devem ser no formato JSON.

## Entregáveis
Devem ser entregues os links dos repositórios no github de cada projeto desenvolvido no desafio 3.
No repositório da api3 deve haver no caminho ./src/main/request/postman/ a collection usada durante odesenvolvimento.
