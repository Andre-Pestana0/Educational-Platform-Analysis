import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static String umDig(char num) {
        switch (num) {
            case '0': return "zero";
            case '1': return "um";
            case '2': return "dois";
            case '3': return "tres";
            case '4': return "quatro";
            case '5': return "cinco";
            case '6': return "seis";
            case '7': return "sete";
            case '8': return "oito";
            case '9': return "nove";
            default: return "";
        }
    }

    public static String doisDig(String num, int start) {
        char d0 = num.charAt(start);
        char d1 = num.charAt(start + 1);

        if (d0 == '1') {
            switch (d1) {
                case '0': return "dez";
                case '1': return "onze";
                case '2': return "doze";
                case '3': return "treze";
                case '4': return "quatorze";
                case '5': return "quinze";
                case '6': return "dezesseis";
                case '7': return "dezessete";
                case '8': return "dezoito";
                case '9': return "dezenove";
            }
        }

        String ans = "";
        switch (d0) {
            case '2': ans = "vinte"; break;
            case '3': ans = "trinta"; break;
            case '4': ans = "quarenta"; break;
            case '5': ans = "cinquenta"; break;
            case '6': ans = "sessenta"; break;
            case '7': ans = "setenta"; break;
            case '8': ans = "oitenta"; break;
            case '9': ans = "noventa"; break;
        }

        if (d1 != '0') {
            if (!ans.isEmpty()) ans += " e ";
            ans += umDig(d1);
        }

        return ans;
    }

    public static String tresDig(String num, int start) {
        char d0 = num.charAt(start);
        char d1 = num.charAt(start + 1);
        char d2 = num.charAt(start + 2);

        String ans = "";
        if (d0 == '1') {
            if (d1 == '0' && d2 == '0') return "cem";
            ans = "cento e ";
            if (d1 == '0') {
                ans += umDig(d2);
            } else {
                ans += doisDig(num, start + 1);
            }
            return ans;
        }

        switch (d0) {
            case '2': ans = "duzentos"; break;
            case '3': ans = "trezentos"; break;
            case '4': ans = "quatrocentos"; break;
            case '5': ans = "quinhentos"; break;
            case '6': ans = "seiscentos"; break;
            case '7': ans = "setecentos"; break;
            case '8': ans = "oitocentos"; break;
            case '9': ans = "novecentos"; break;
        }

        if (d1 != '0' || d2 != '0') {
            if (!ans.isEmpty()) ans += " e ";
            if (d1 == '0') {
                ans += umDig(d2);
            } else {
                ans += doisDig(num, start + 1);
            }
        }

        return ans;
    }

    public static String quatroDig(String num) {
        String ans = "";
        char d0 = num.charAt(0);
        char d1 = num.charAt(1);
        char d2 = num.charAt(2);
        char d3 = num.charAt(3);

        switch (d0) {
            case '1': ans = ""; break;
            case '2': ans = "dois "; break;
            case '3': ans = "tres "; break;
            case '4': ans = "quatro "; break;
            case '5': ans = "cinco "; break;
            case '6': ans = "seis "; break;
            case '7': ans = "sete "; break;
            case '8': ans = "oito "; break;
            case '9': ans = "nove "; break;
        }

        ans += "mil";

        if (d1 != '0' || d2 != '0' || d3 != '0') {
            if (d1 != '0') {
                if (d2 == '0' && d3 == '0') {
                    ans += " e ";
                } else {
                    ans += " ";
                }
                ans += tresDig(num, 1);
            } else {
                ans += " e ";
                if (d2 != '0') {
                    ans += doisDig(num, 2);
                } else {
                    ans += umDig(d3);
                }
            }
        }

        return ans;
    }

    public static String cincoDig(String num) {
        String ans = doisDig(num, 0);

        char d2 = num.charAt(2);
        char d3 = num.charAt(3);
        char d4 = num.charAt(4);

        if (d2 == '0' && d3 == '0' && d4 == '0') {
            ans += " mil";
        } else {
            if (d2 != '0') {
                if (d3 != '0' || d4 != '0') {
                    ans += " mil ";
                } else {
                    ans += " mil e ";
                }
                ans += tresDig(num, 2);
            } else {
                ans += " mil e ";
                if (d3 == '0') {
                    ans += umDig(d4);
                } else {
                    ans += doisDig(num, 3);
                }
            }
        }
        return ans;
    }

    public static String seisDig(String num) {
        String ans = tresDig(num, 0) + " mil";

        char d3 = num.charAt(3);
        char d4 = num.charAt(4);
        char d5 = num.charAt(5);

        if (d3 != '0') {
            if (d4 == '0' && d5 == '0') {
                ans += " e ";
            } else {
                ans += " ";
            }
            ans += tresDig(num, 3);
        } else {
            if (d4 != '0') {
                ans += " e ";
                ans += doisDig(num, 4);
            } else if (d5 != '0') {
                ans += " e ";
                ans += umDig(d5);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int num = Integer.parseInt(line);
            String numero = String.valueOf(num);
            String ans = "";

            switch (numero.length()) {
                case 1: ans = umDig(numero.charAt(0)); break;
                case 2: ans = doisDig(numero, 0); break;
                case 3: ans = tresDig(numero, 0); break;
                case 4: ans = quatroDig(numero); break;
                case 5: ans = cincoDig(numero); break;
                case 6: ans = seisDig(numero); break;
            }

            output.append(ans).append('\n');

            // Limpa o buffer se a resposta ficar muito grande para economizar RAM
            if (output.length() > 32768) {
                System.out.print(output);
                output.setLength(0);
            }
        }

        if (output.length() > 0) {
            System.out.print(output);
        }
    }
}