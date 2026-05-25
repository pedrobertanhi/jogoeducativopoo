import java.util.Scanner;
import java.util.Random;

public class JogoDado implements Jogo {
    @Override
    public void iniciar(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("\n--- JOGO DO DADO ---");
        System.out.println("O dado vai rolar. Escolha se o resultado será PAR (1) ou ÍMPAR (2):");
        System.out.print("Sua opção: ");
        int escolha = scanner.nextInt();

        boolean usouDadoViciado = false;
        
        // Sistema de Vantagem
        for (Item item : jogador.getInventario()) {
            if (item instanceof ItemVantagem && ((ItemVantagem) item).getJogoAlvo().equals("DADO")) {
                System.out.println("Você tem o item: [" + item.getNome() + "]. Deseja usá-lo para forçar o resultado? (1-Sim / 2-Não)");
                if (scanner.nextInt() == 1) {
                    usouDadoViciado = true;
                    jogador.removerItem(item);
                    GerenciadorDados.registrarLog(jogador.getNome() + " usou o Dado Viciado.");
                }
                break;
            }
        }
        
        int resultadoDado = random.nextInt(6) + 1;
        System.out.println("O dado caiu no número: " + resultadoDado);
        boolean ehPar = (resultadoDado % 2 == 0);
        
        if (usouDadoViciado || (escolha == 1 && ehPar) || (escolha == 2 && !ehPar)) {
            if (usouDadoViciado) {
                System.out.println("O Dado Viciado alterou a realidade! Você ganhou 30 pontos.");
            } else {
                System.out.println("Você acertou a previsão! Ganhou 30 pontos.");
            }
            jogador.adicionarPontos(30);
            GerenciadorDados.registrarLog(jogador.getNome() + " ganhou 30 pontos no Jogo do Dado.");
        } else {
            System.out.println("Você errou a previsão!");
            GerenciadorDados.registrarLog(jogador.getNome() + " perdeu no Jogo do Dado.");
        }
    }
}