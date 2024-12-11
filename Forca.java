import java.util.Random;
import java.util.Scanner;

public class Forca {
    public static void main(String[] args) {
        // Criando um scanner
        Scanner scanner = new Scanner(System.in);

        // Definindo variáveis que serão utilizadas no jogo
        int vidas = 3;
        String palavra;
        String letra;
        String letrasErradas = "";
        String[] letrasCorretas = new String[5];
        int indiceLetrasCorretas = 0;

        // Sorteando uma palavra aleatória
        String palavraSecreta = sortearPalavra();

        // Laço de repetição do jogo
        while (vidas > 0) {
            // Atualizando a palavra: letras corretas e/ou "*"
            palavra = atualizarPalavra(palavraSecreta, letrasCorretas);

            // Verificando se a palavra já está correta
            if (palavra.equals(palavraSecreta)) {
                break;
            }

            // Exibindo as informações ao jogador
            System.out.println("\n========================");
            System.out.println("Letras erradas: " + letrasErradas);
            System.out.println("Vidas: " + vidas);
            System.out.println("------------------------");
            System.out.println("Palavra: " + palavra);

            // Solicitando ao usuário que digite uma letra
            System.out.print("Digite uma letra: ");
            letra = scanner.next();

            // Verificando se a entrada é a palavra
            if (letra.equals(palavraSecreta)) {
                break;
            }

            // Verificando se a entrada é um "chute" e descontando vida
            else if (letra.length() == 5) {
                vidas--;
            }

            // Verificando se a entrada contem mais de um digito (a diferença é que o de cima pode ser um chute de palavra, aqui um erro de digitação
            else if (letra.length() > 1) {
                System.out.println("Digite apenas UMA letra ou chute a palavra (5 letras).");
            }

            // Verificando se o usuário já digitou a letra
            else if (letrasErradas.contains(letra)) {
                System.out.println("Você já digitou essa letra.");
            }

            // Verificando se a palavra contém a letra
            else if (palavraSecreta.contains(letra)) {
                letrasCorretas[indiceLetrasCorretas] = letra;
                indiceLetrasCorretas++;
            }

            // Adicionando a letra à letrasErradas e retirando uma vida do usuário
            else {
                letrasErradas = String.format("%s %s", letrasErradas, letra);
                vidas--;
            }
        }
        System.out.println("========================");

        // Exibindo uma mensagem de vitória ou derrada
        if (vidas > 0) {
            System.out.println("VOCÊ GANHOU, PARABÉNS! \nA palavra era " + palavraSecreta);
        } else {
            System.out.println("Você perdeu :( \nA palavra era " + palavraSecreta);
        }

        // Fechando o scanner
        scanner.close();
    }

    // Metodo que atualiza a palavra: letras corretas e/ou "*"
    public static String atualizarPalavra(String palavraSecreta, String[] letrasCorretas) {
        // Criando uma StringBuilder que será retornada com às letras e/ou "*"
        StringBuilder palavra = new StringBuilder();

        // Iterrando letra por letra em palavraSecreta
        for (char letraSecreta : palavraSecreta.toCharArray()) {
            boolean letraEncontrada = false;

            // Encontrando a posição correta das letras
            for (String letraCorreta : letrasCorretas) {
                // Definindo uma váriavel String da letraSecreta para o if não ficar confuso
                String letraSecretaString = String.valueOf(letraSecreta);

                // Verificando se a letra atual equivale, caso sim, o laço irá parar
                if ((letraCorreta != null) && (letraCorreta.equals(letraSecretaString))) {
                    letraEncontrada = true;
                    break;
                }
            }

            // Se a letra foi encontrada na posição correta, será adicionada a palavra
            if (letraEncontrada) {
                palavra.append(letraSecreta);
            } else {
                palavra.append("*");
            }

        }

        // Retornando a palavra atualizada
        return palavra.toString();
    }

    // Metodo que sortea uma palavra aleatória
    public static String sortearPalavra() {
        // Criando um sorteador
        Random random = new Random();

        // Array com palavras para ser sorteada
        String[] palavras = {"amigo", "festa", "nuvem", "pedra", "poema"};

        // Sorteando um número para ser o indice para acessar a palavra aleatória
        int indice = random.nextInt(0, palavras.length);

        // Retornando a palavra
        return palavras[indice];
    }
}
