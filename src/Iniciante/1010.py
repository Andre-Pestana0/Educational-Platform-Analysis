codigoA, quantidadeA, valorA = map(float, input().split())
codigoB, quantidadeB, valorB = map(float, input().split())
custoA = quantidadeA * valorA
custoB = quantidadeB * valorB
print(f'VALOR A PAGAR: R$ {(custoA + custoB):.2f}')