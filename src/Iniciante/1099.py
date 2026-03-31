# Problema 1099 - Beecrowd - Iniciante - Nível 1

# Leitura da variável 'n' (número de testes que serão feitos)
n = int(input())

# Declaração da variável soma
soma = 0

# Início do primeiro loop
for contador1 in range(n):
	
	# Leitura das variáveis 'x' e 'y'
	x,y = map(int, input().split(' '))
  
	# Condicional caso 'y' seja maior que 'x'
	if(y>x):
		k = y-x

		# Início do segundo loop
		for contador2 in range(k-1):
			x=x+1

			if (x%2 != 0):
				soma = soma + x


		print(soma)


		soma=0

	else:
		k = x-y

		# Início do terceiro loop
		for contador3 in range(k-1):
			y=y+1
			if (y%2 != 0):
				soma = soma + y

		# Printando a soma dos ímpares entre 'x' e 'y'		
		print(soma)

		# Zerando a soma para o próximo teste
		soma=0