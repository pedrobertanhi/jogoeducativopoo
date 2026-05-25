import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== BEM-VINDO AO GAME SYSTEM ===");
        System.out.print("Digite seu nome de usuário: ");
        String nomeIn = scanner.nextLine();
        
        Jogador jogador = new Jogador(nomeIn);
        int pontosIniciais = GerenciadorDados.carregarPontos();
        jogador.adicionarPontos(pontosIniciais);

        // Sistema de Economia - Loja Completa com Itens e Vantagens
        List<Item> loja = new ArrayList<>();
        loja.add(new ItemCosmetico("Emblema de Ouro POO", 120, "Épico"));
        loja.add(new ItemVantagem("Gabarito Vazado", 80, "QUIZ"));
        loja.add(new ItemVantagem("Lente Reveladora", 40, "ADIVINHACAO"));
        loja.add(new ItemVantagem("Dado Viciado", 60, "DADO"));

        GerenciadorDados.registrarLog("Sessão inicializada para: " + jogador.getNome());

        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n========================================");
            System.out.println(" JOGADOR: " + jogador.getNome() + " | SALDO: " + jogador.getPontos() + " pts");
            System.out.println("========================================");
            System.out.println("1. Jogar Quiz Educativo (POO)");
            System.out.println("2. Jogar Adivinhação de Números");
            System.out.println("3. Jogar Jogo do Dado");
            System.out.println("4. Acessar a Loja de Itens");
            System.out.println("5. Olhar Meu Inventário");
            System.out.println("6. Salvar Progresso e Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Jogo quiz = new QuizEducativo();
                    quiz.iniciar(jogador);
                    break;
                case 2:
                    Jogo adv = new JogoAdivinhacao();
                    adv.iniciar(jogador);
                    break;
                case 3:
                    Jogo dado = new JogoDado();
                    dado.iniciar(jogador);
                    break;
                case 4:
                    System.out.println("\n--- VITRINE DA LOJA ---");
                    for (int i = 0; i < loja.size(); i++) {
                        Item item = loja.get(i);
                        String info = item.getNome() + " - " + item.getPreco() + " pts";
                        if (item instanceof ItemCosmetico) {
                            info += " (Raridade: " + ((ItemCosmetico) item).getRaridade() + ")";
                        } else if (item instanceof ItemVantagem) {
                            info += " (Vantagem para o jogo: " + ((ItemVantagem) item).getJogoAlvo() + ")";
                        }
                        System.out.println((i + 1) + ") " + info);
                    }
                    System.out.print("Selecione o número para comprar (ou 0 para voltar): ");
                    int compra = scanner.nextInt();
                    
                    if (compra > 0 && compra <= loja.size()) {
                        Item itemEscolhido = loja.get(compra - 1);
                        if (jogador.comprarItem(itemEscolhido)) {
                            System.out.println("Compra realizada com sucesso: " + itemEscolhido.getNome());
                            GerenciadorDados.registrarLog(jogador.getNome() + " comprou " + itemEscolhido.getNome());
                        } else {
                            System.out.println("Pontos insuficientes para realizar esta compra.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("\n--- MEU INVENTÁRIO ---");
                    if (jogador.getInventario().isEmpty()) {
                        System.out.println("Você ainda não possui itens.");
                    } else {
                        for (Item item : jogador.getInventario()) {
                            System.out.println("- " + item.getNome());
                        }
                    }
                    break;
                case 6:
                    GerenciadorDados.salvarEstadoJogador(jogador);
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida, digite novamente.");
                    break;
            }
        }
        scanner.close();
    }
}