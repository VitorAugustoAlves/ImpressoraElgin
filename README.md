# Sistema de Controle de Impressora Elgin

Projeto Elgin

## Sobre

Este projeto foi desenvolvido por Gabriel Schultz, Hyago Bringel e Vitor Teodósio, alunos do colégio UNASP, para se comunicar com uma impressora Elgin i9. Feito para um projeto da disciplina de Programação Orientada a Objetos(POO) com objetivos acadêmicos.

O sistema possui login e um menu com operações de impressão de texto, QR Code, código de barras, XML SAT e XML de cancelamento SAT. Também permite configurar a conexão, abrir a gaveta, emitir sinais sonoros, avançar e cortar o papel e consultar o status da impressora.

---

## Tecnologias Utilizadas

- Java 8 (1.8.0_351)
- Visual Studio Code
- JNA 5.15.0
- DLL E1_Impressora01
- Impressora Elgin i9

---

## Estrutura do Projeto
```
/ImpressoraElgin-main
│  
├── Main.java  
│   → Inicia o programa, cria os objetos e mantém o menu em execução.  
│  
├── Login.java  
│   → Solicita usuário e senha e controla as três tentativas de login.  
│  
├── Usuario.java  
│   → Armazena o usuário e a senha utilizados no login.  
│  
├── Menu.java  
│   → Exibe o menu, solicita os dados e valida as entradas do usuário.  
│  
├── Impressora.java  
│   → Controla a conexão e as operações realizadas pela impressora.  
│  
├── XMLSAT.xml  
│   → XML de exemplo utilizado para imprimir um cupom SAT.  
│  
├── CANC_SAT.xml  
│   → XML de exemplo utilizado para imprimir um cancelamento SAT.  
│  
├── /src  
│   └── ImpressoraDLL.java  
│       → Faz a comunicação entre o Java e a DLL da Elgin.  
│         Este arquivo não deve ser alterado.  
│  
├── /Bibliotecas E1 Impressora  
│   ├── /x64  
│   │   └── E1_Impressora01.dll  
│   │  
│   └── /x86  
│       └── E1_Impressora01.dll  
│       → DLLs da Elgin para sistemas de 64 e 32 bits.  
│  
├── /libs Java  
│   └── jna-5.15.0.jar  
│       → Biblioteca que permite ao Java utilizar as funções da DLL.  
│  
└── /.vscode  
    └── settings.json  
        → Configura o caminho da biblioteca JNA no Visual Studio Code.  
```
---

## Instalação e Execução

### 1. Baixar o projeto

1. Acesse o repositório no GitHub.
2. Clique em **Code → Download ZIP**.
3. Extraia o arquivo ZIP.
4. Não altere a organização das pastas.

### 2. Abrir no Visual Studio Code

1. Abra o Visual Studio Code.
2. Clique em **File → Open Folder**.
3. Selecione a pasta extraída do projeto.
4. Confirme que o Java está instalado e configurado no computador.
5. Caso necessário, instale o suporte para Java no Visual Studio Code.

### 3. Conferir a biblioteca JNA

O arquivo `.vscode/settings.json` deve possuir a seguinte configuração:

```json
{
    "java.project.referencedLibraries": [
        "libs Java/**/*.jar"
    ]
}
```

A biblioteca utilizada pelo projeto está localizada em:

libs Java/jna-5.15.0.jar

### 4. Conferir o diretório de execução

O programa deve ser executado a partir da pasta principal do projeto.

Essa pasta deve conter:

- `Main.java`;
- `XMLSAT.xml`;
- `CANC_SAT.xml`;
- `Bibliotecas E1 Impressora`;
- `libs Java`.

Essa organização é necessária para que o programa encontre os arquivos XML e as DLLs da impressora.

### 5. Executar o programa

1. Conecte e ligue a impressora Elgin.
2. Abra o arquivo `Main.java`.
3. Clique em ▶️ **Run**.
4. Faça o login utilizando:

Usuário: admin
Senha: 1234

5. Escolha a opção `1` para configurar a conexão.
6. Para uma conexão USB, utilize:

Tipo: 1
Modelo: i9
Conexão: USB
Parâmetro: 0

7. Escolha a opção `2` para abrir a conexão.
8. Utilize as demais opções do menu.
9. Escolha a opção `12` para encerrar o programa.

---

## Login

Usuário: admin
Senha: 1234

---

## Funcionalidades do Menu

| Opção | O que faz |
|---|---|
| 1 | Configura o tipo, modelo, conexão e parâmetro da impressora. |
| 2 | Abre a conexão com a impressora. |
| 3 | Fecha a conexão com a impressora. |
| 4 | Imprime um texto com posição, estilo e tamanho escolhidos pelo usuário. |
| 5 | Imprime um QR Code com tamanho e nível de correção escolhidos. |
| 6 | Imprime um código de barras. |
| 7 | Imprime o arquivo XML de um cupom SAT. |
| 8 | Imprime o XML de cancelamento SAT. |
| 9 | Abre a gaveta conectada à impressora. |
| 10 | Emite uma quantidade definida de bips. |
| 11 | Permite avançar o papel, consultar o status ou cortar o papel. |
| 12 | Fecha a conexão e encerra o programa. |

---

## Testes Realizados

- Login com usuário e senha corretos: acesso permitido.
- Letras no campo da senha: erro tratado e pergunta repetida.
- Letras nas opções numéricas do menu: erro tratado e pergunta repetida.
- Opção fora do intervalo de 1 a 12: valor rejeitado.
- Tipo de conexão fora do intervalo de 1 a 4: valor rejeitado.
- Configuração USB com modelo i9, conexão USB e parâmetro 0: configuração aceita.
- Parâmetro USB diferente de 0: valor rejeitado.
- Tentativa de impressão com a conexão fechada: operação bloqueada.
- Arquivos `XMLSAT.xml` e `CANC_SAT.xml`: arquivos encontrados e estrutura validada.
- Encerramento pela opção 12: programa finalizado normalmente.
- Compilação com Java 8 e JNA 5.15.0: concluída sem erros.

---

## Documentação Adicional

- Manual da impressora Elgin
- Documentação do SDK E1_Impressora
---

## Autores

- Gabriel Nogueira Schultz
- Hyago Bringel Pereira
- Vitor Augusto Alves Teodósio
