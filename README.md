# Simulador-Z808

### _Repositório para o projeto máquina virtual do sistema computacional hipotético Z808_

### Objetivo 🎯

* Consiste em implementar a máquina virtual (emulador) do sistema computacional Z808 - conforme apresentado no livro _Tradução de programas da montagem a
carga por Cristian Koliver_.
* Tal sistema será composto de dois módulos que deverão operar de forma integrada: o executor
(emulador propriamente dito) e uma interface visual. 

### Descrição do emulador Z808 📝

1. Memória
* A memória do computador é definida pelos seguintes atributos:

|  Tamanho da memória  | 64KB |
|----------------------|------|
| Palavra de memória | 16b |
| Unidade de endereçamento | Palavra |
| Bit de paridade | NA |
| Cache | NA |

2. Registrador de dados
* O Z808 possui dois registradores de dados AX (Acumulador) e DX (Registrador de dados) de 16 bits.
  Os bits recebem um designação numérica de 0 a 15, da direita para esquerda, sendo o bit 0 o de mais baixa ordem ou menos significativo.

3. Demais Registradores:
* A lista seguinte mostra os demais registradores implementados no computador hipotético e sua
descrição.

|  Registrador  | Tipo | Tamanho (bits) | Descrição |
|---------------|------|----------------|-----------|
| SP | STACK POINTER | 16 | Aponta para o topo da memória do tipo pilha. Usado pelas instruções push e pop. |
| SI | SOURCE INDEX | 16 | Aponta para a origem dos dados que serão movimentados. É usado para indexação de tabelas no endereçamento indireto. |
| IP | Instruction pointer | 16 | Contém durante a execução de um programa o endereço na memória da próxima instrução a ser executada. |
| SR | Status register | 16 | Contém seis flags de um bit. Usados para indicar várias condições durante a execução do programa. |

#### Tecnologias Utilizadas 💻

| Java |
|------|
|  17  |
