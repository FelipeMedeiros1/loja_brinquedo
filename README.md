# Sistema de Descontos da Loja de Brinquedos

## Descrição

Este projeto é uma API Restful em Java que simula um sistema de descontos de uma loja de brinquedos. O sistema oferece dois tipos de descontos:
1. **Desconto por Quantidade**: Se o cliente comprar 3 ou mais brinquedos do mesmo tipo, ele ganha 10% de desconto em cada um desses brinquedos.
2. **Desconto por Valor Total**: Se o valor total da compra for maior que R$ 1.000,00, o cliente ganha 5% de desconto em toda a compra.

O programa lê a quantidade e o preço unitário de cada tipo de brinquedo, calcula o valor total da compra sem desconto, aplica os descontos se aplicáveis, e mostra o valor total da compra com desconto.

## Funcionalidades

- Ler a quantidade de brinquedos de cada tipo que o cliente deseja comprar.
- Ler o preço unitário de cada tipo de brinquedo.
- Calcular o valor total da compra sem desconto.
- Aplicar o desconto por quantidade, se aplicável.
- Aplicar o desconto por valor total, se aplicável.
- Mostrar o valor total da compra com desconto.

## Tecnologias Utilizadas

- Linguagem: Java 17
- Maven
- Framework: Spring boot 3
- Springdoc Swagger
- SOLID

## Instruções de Uso

1. Clone o repositório ou faça o download do código-fonte.
2. Execute o programa.
3. Cadastre os brinquedos
4. Registre o usuário
5. Adicione itens no carrinho
6. Finalize a compra
7. Veja os resultados dos cálculos, incluindo os descontos aplicados e o valor total da compra com desconto.

 ## Para acessar no browser
 * http://localhost:8080/swagger-ui.html
