# javer
Utilitário para verificar a versão compilada do Java de uma classe, biblioteca ou diretório.

100% java e sem dependências, o Javer é uma aplicação simples para verificação da versão em que uma 
classe ou jar foi compilado, além de poder verificar um diretório completo que possua arquivos do tipo jar ou .class.

Sua implementação se baseia no artigo da Wikipedia [Java Class File](https://en.wikipedia.org/wiki/Java_class_file#General_layout), 
usando somente os bytes de `major version`.

Como usar:

`java -jar javer.jar <path>`  
ex: `java -jar javer.jar javer.jar`

path pode ser o caminho do completo do arquivo, ou o caminho completo da biblioteca .jar ou uma pasta contendo .class e/ou .jar.

### Features desejadas

* Parametro para ocorrer erro caso haja uma versão maior ou igual ao parametro, retornando erro
* Plugin para maven
* Plugin para gradle
* Verificar as dependências de um arquivo pom.xml
* Verificar um arquivo remoto via http
* Verificar um arquivo remoto via ftp

### Releases

As releases são geradas em Java 11  
[Baixe aqui](https://github.com/codigomix-lives/javer/releases)

### Primeira versão desenvolvida em live coding. Verifique os vídeos em 

Parte 1: https://www.youtube.com/watch?v=UbTp7hkw-sI  
Parte 2: https://www.youtube.com/watch?v=q98YPPtFAQA  
Parte 3: https://www.youtube.com/watch?v=gb0aPWwHTmU  

### Licença

Verifique a licença em [LICENSE](./LICENSE)

### Código de Conduta

Veja o código de conduta em [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md)





