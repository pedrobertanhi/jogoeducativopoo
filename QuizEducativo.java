import java.util.Scanner;

public class QuizEducativo implements Jogo {
    @Override
    public void iniciar(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- QUIZ EDUCATIVO DE POO ---");

        // Sistema de Vantagem
        for (Item item : jogador.getInventario()) {
            if (item instanceof ItemVantagem && ((ItemVantagem) item).getJogoAlvo().equals("QUIZ")) {
                System.out.println("Você tem o item: [" + item.getNome() + "]. Deseja usar para pular a pergunta e ganhar? (1-Sim / 2-Não)");
                if (scanner.nextInt() == 1) {
                    System.out.println("Item usado! Resposta preenchida automaticamente.");
                    jogador.removerItem(item);
                    jogador.adicionarPontos(50);
                    GerenciadorDados.registrarLog(jogador.getNome() + " usou vantagem no Quiz.");
                    return; // Encerra o jogo aqui, pois ele já ganhou
                }
                break; // Pergunta só uma vez para evitar bugs
            }
        }

        System.out.println("Qual pilar do POO esconde os detalhes internos de uma classe e protege os dados?");
        System.out.println("1) Herança");
        System.out.println("2) Encapsulamento");
        System.out.println("3) Polimorfismo");
        System.out.print("Sua resposta: ");
        
        int resposta = scanner.nextInt();
        if (resposta == 2) {
            System.out.println("Correto! Você ganhou 50 pontos.");
            jogador.adicionarPontos(50);
            GerenciadorDados.registrarLog(jogador.getNome() + " acertou o Quiz e ganhou 50 pontos.");
        } else {
            System.out.println("Incorreto! A resposta certa era Encapsulamento.");
            GerenciadorDados.registrarLog(jogador.getNome() + " errou o Quiz.");
        }
    }
}