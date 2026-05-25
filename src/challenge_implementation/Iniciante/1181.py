# Problema 1181 - Beecrowd - Iniciante - Nível 3

# Recebendo os valores de entrada.
linha = int(input())
operacao = input()
soma=0.0

# Inicialização da matriz vazia, que é uma lista que receberá linhas da matriz
matriz = []

# Loop de for para preenchimento da matriz.
for i in range(12):

    # Inicialização da linha da matriz a cada troca de linha.
    linhaMatriz = []

    for j in range(12):

        # Recebimento do valor do usuário e adicionando na linha da matriz.
        linhaMatriz.append(float(input()))

    # Adiciona a linha inteira na matriz.
    matriz.append(linhaMatriz)

# Decidindo a operação para apresentar o resultado e assim,
# para facilitar o cálculo do resultado, é feito a soma da
# linha em questão, e se caso for a média, é feito a divisão
# por 12, que é o tamanho da linha.

# resultado = sum(matriz[linha])

for i in range(linha + 1):
    for j in range(linha + 1):
        soma += matriz[i][j]

if operacao == "M":
    resultado = resultado / 12

# Impressão do resultado.
print(resultado)
