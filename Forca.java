import java.util.Random;
import java.util.Scanner;

public class Forca {
    public static void main(String[] args) {
        // Criando um scanner
        Scanner scanner = new Scanner(System.in);

        // Definindo a palavra
        String palavraSecreta = sorteador();

        // Definindo variáveis úteis para o game
        int vidas = 3;
        String[] letrasCorretas = new String[5];
        int indiceLetrasCorretas = 0;
        String letrasDigitadas = "";

        // Laço de repetição
        while (vidas > 0) {
            // Atualizando a palavra
            String palavra = atualizarPalavra(palavraSecreta, letrasCorretas);
            if (palavra.equals(palavraSecreta)) {
                break;
            }

            // Exibindo ao jogador as informações
            System.out.println("=============================");
            System.out.println("Palavra: " + palavra);
            System.out.println("Letras digitadas: " + letrasDigitadas);
            System.out.println("Vidas: " + vidas);
            System.out.println("------------");

            // Solicitando ao usuário uma letra
            System.out.print("Digite uma letra: ");
            String letra = scanner.next().toLowerCase();

            // Verificando se a entrada é a palavra
            if (letra.equals(palavraSecreta)) {
                break;
            } else if (letra.length() == 5) {
                vidas--;
            } else if (letra.length() > 1) {
                System.out.println("Digite apenas UMA letra!!!");
            } else if (palavraSecreta.contains(letra)) {
                letrasCorretas[indiceLetrasCorretas] = letra;
                indiceLetrasCorretas++;
            } else {
                letrasDigitadas = String.format("%s %s", letrasDigitadas, letra);
                vidas--;
            }
        }
        System.out.println("=============================");

        // Mensagem de vitória ou derrota
        if (vidas > 0) {
            System.out.println("PARABÉNS! VOCÊ GANHOU!!!!!! \nA palavra era: " + palavraSecreta);
        } else {
            System.out.println("Você perdeu :( \nA palavra era: " + palavraSecreta);
        }

        // Fechando o scanne
        scanner.close();
    }

    // Sortea uma palavra
    public static String sorteador() {
        // Criando um random
        Random random = new Random();

        // Lista de palavras para ser sorteadas
        String[] palavras = {"amigo", "pedra", "verde", "festa", "nuvem", "poema"};

        // Sorteando um número aleatório para ser o indice para acessar alguma palavra acima
        int indice = random.nextInt(0, palavras.length);

        // Retornando a palavra
        return palavras[indice];
    }

    // Verifica a palavra e retorna com as letras acertadas e o "*"
    public static String atualizarPalavra(String palavraSecreta, String[] letrasCorretas) {
        // Iniciando a variável que será retornada
        StringBuilder palavra = new StringBuilder();

        // Iterando em letra por letra da palavra secreta
        for (char letraSecreta : palavraSecreta.toCharArray()) {
            boolean letraEncontrada = false;

            // Verificando se a letra secreta está nas letras corretas
            for (String letraCorreta : letrasCorretas) {
                // Definindo a variavel para o if não ficar confuso
                String letraSecretaString = String.valueOf(letraSecreta);

                // Verificando se a letra equivale e parando o laço
                if ((letraCorreta != null) && (letraCorreta.equals(letraSecretaString))) {
                    letraEncontrada = true;
                    break;
                }
            }

            // Se a letra foi encontrada, adiciona ela à palavra, senão adiciona "*"
            if (letraEncontrada) {
                palavra.append(letraSecreta);
            } else {
                palavra.append('*');
            }
        }

        // Retornando a palavra atualizada
        return palavra.toString();
    }
}