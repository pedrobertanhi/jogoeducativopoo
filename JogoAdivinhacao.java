import java.util.Scanner;
import java.util.Random;

public class JogoAdivinhacao implements Jogo {
    @Override
    public void iniciar(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numeroSecreto = random.nextInt(10) + 1;
        
        System.out.println("\n--- JOGO DE ADIVINHAÇÃO ---");

        // Sistema de Vantagem
        for (Item item : jogador.getInventario()) {
            if (item instanceof ItemVantagem && ((ItemVantagem) item).getJogoAlvo().equals("ADIVINHACAO")) {
                System.out.println("Você tem o item: [" + item.getNome() + "]. Deseja usar para receber uma dica? (1-Sim / 2-Não)");
                if (scanner.nextInt() == 1) {
                    jogador.removerItem(item);
                    String dica = (numeroSecreto % 2 == 0) ? "PAR" : "ÍMPAR";
                    System.out.println(">> DICA DE OURO: O número secreto é " + dica + "!");
                    GerenciadorDados.registrarLog(jogador.getNome() + " usou dica na Adivinhação.");
                }
                break;
            }
        }

        System.out.println("Tente adivinhar o número secreto entre 1 e 10!");
        System.out.print("Digite seu palpite: ");
        int palpite = scanner.nextInt();
        
        if (palpite == numeroSecreto) {
            System.out.println("Parabéns! Você acertou e ganhou 40 pontos.");
            jogador.adicionarPontos(40);
            GerenciadorDados.registrarLog(jogador.getNome() + " acertou o número secreto e ganhou 40 pontos.");
        } else {
            System.out.println("Que pena! O número correto era " + numeroSecreto);
            GerenciadorDados.registrarLog(jogador.getNome() + " errou o número secreto.");
        }
    }
}