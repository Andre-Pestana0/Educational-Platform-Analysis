import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // Representa o par (ocorrências, inteiro) para o Caso 3
    static class OcorrenciaInteiro implements Comparable<OcorrenciaInteiro> {
        int ocorrencia;
        int inteiro;

        public OcorrenciaInteiro(int ocorrencia, int inteiro) {
            this.ocorrencia = ocorrencia;
            this.inteiro = inteiro;
        }

        @Override
        public int compareTo(OcorrenciaInteiro o) {
            if (this.ocorrencia != o.ocorrencia) {
                return Integer.compare(this.ocorrencia, o.ocorrencia);
            }
            return Integer.compare(this.inteiro, o.inteiro);
        }
    }

    // Representa a tupla (qtdComEssaOcorrencia, valorOcorrencia, inteiro) para os Casos 1 e 2
    static class InfoOcorrencia implements Comparable<InfoOcorrencia> {
        int quantidade;
        int valorOcorrencia;
        int inteiro;

        public InfoOcorrencia(int quantidade, int valorOcorrencia, int inteiro) {
            this.quantidade = quantidade;
            this.valorOcorrencia = valorOcorrencia;
            this.inteiro = inteiro;
        }

        @Override
        public int compareTo(InfoOcorrencia o) {
            if (this.quantidade != o.quantidade) {
                return Integer.compare(this.quantidade, o.quantidade);
            }
            if (this.valorOcorrencia != o.valorOcorrencia) {
                return Integer.compare(this.valorOcorrencia, o.valorOcorrencia);
            }
            return Integer.compare(this.inteiro, o.inteiro);
        }
    }

    public static void tryCaso3(Map<Integer, Integer> ocorrencias) {
        long soma = 0;
        for (int oc : ocorrencias.values()) {
            soma += oc;
        }

        // Verifica se a média é um número inteiro
        if (soma % ocorrencias.size() == 0) {
            double media = (double) soma / ocorrencias.size();
            List<OcorrenciaInteiro> diferentes = new ArrayList<>();
            int contador = 0;

            for (Map.Entry<Integer, Integer> entry : ocorrencias.entrySet()) {
                int inteiro = entry.getKey();
                int oc = entry.getValue();

                if (oc != media) {
                    contador++;
                    diferentes.add(new OcorrenciaInteiro(oc, inteiro));
                }
                if (contador > 2) {
                    return;
                }
            }

            Collections.sort(diferentes);
            System.out.printf("-%d +%d%n", diferentes.get(1).inteiro, diferentes.get(0).inteiro);
            System.exit(0);
        }
    }

    public static void tryCasos12(Map<Integer, Integer> ocorrencias) {
        List<InfoOcorrencia> ocorrenciasDiferentes = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : ocorrencias.entrySet()) {
            int inteiro = entry.getKey();
            int oc = entry.getValue();

            boolean jáExiste = false;
            for (InfoOcorrencia item : ocorrenciasDiferentes) {
                if (item.valorOcorrencia == oc) {
                    jáExiste = true;
                    break;
                }
            }

            if (!jáExiste) {
                // Conta quantas vezes a frequência 'oc' aparece no mapa
                int count = 0;
                for (int val : ocorrencias.values()) {
                    if (val == oc) {
                        count++;
                    }
                }
                ocorrenciasDiferentes.add(new InfoOcorrencia(count, oc, inteiro));
            }
        }

        if (ocorrenciasDiferentes.size() > 2) {
            return;
        }

        Collections.sort(ocorrenciasDiferentes);
        InfoOcorrencia alterar = ocorrenciasDiferentes.get(0);
        InfoOcorrencia manter = ocorrenciasDiferentes.get(1);

        if (alterar.quantidade == 1) { // Apenas um diferente
            if (alterar.valorOcorrencia + 1 == manter.valorOcorrencia) {
                System.out.printf("+%d%n", alterar.inteiro);
                System.exit(0);
            } else if (alterar.valorOcorrencia - 1 == manter.valorOcorrencia) {
                System.out.printf("-%d%n", alterar.inteiro);
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int K = scanner.nextInt();
        int N = scanner.nextInt();

        Map<Integer, Integer> ocorrencias = new HashMap<>();

        // Inicializa o mapa com 0 para todas as chaves de 1 a K
        for (int i = 1; i <= K; i++) {
            ocorrencias.put(i, 0);
        }

        // Conta as ocorrências dos inteiros da entrada
        for (int i = 0; i < N; i++) {
            int inteiro = scanner.nextInt();
            ocorrencias.put(inteiro, ocorrencias.get(inteiro) + 1);
        }

        // Tenta resolver para cada caso
        tryCaso3(ocorrencias);
        tryCasos12(ocorrencias);

        // Se nenhum caso serviu, imprime '*'
        System.out.println("*");

        scanner.close();
    }
}