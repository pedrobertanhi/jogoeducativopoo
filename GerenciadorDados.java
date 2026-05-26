import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GerenciadorDados {
    private static final String ARQUIVO_LOG = "log_banco.txt";
    private static final String ARQUIVO_SAVE = "save_jogador.txt";

    public static void registrarLog(String mensagem) {
        try (FileWriter fw = new FileWriter(ARQUIVO_LOG, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("[" + System.currentTimeMillis() + "] " + mensagem);
        } catch (IOException e) {
            System.out.println("Erro ao gravar log.");
        }
    }

    public static void salvarEstadoJogador(Jogador jogador) {
        try (FileWriter fw = new FileWriter(ARQUIVO_SAVE);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(jogador.getNome());
            pw.println(jogador.getPontos());
            System.out.println("Progresso salvo com sucesso!");
            registrarLog("Progresso de " + jogador.getNome() + " salvo.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo.");
        }
    }

    public static String carregarNome() {
        try (FileReader fr = new FileReader(ARQUIVO_SAVE);
             BufferedReader br = new BufferedReader(fr)) {
            return br.readLine();
        } catch (IOException e) {
            return null; 
        }
    }

    public static int carregarPontos() {
        try (FileReader fr = new FileReader(ARQUIVO_SAVE);
             BufferedReader br = new BufferedReader(fr)) {
            br.readLine(); 
            String pontosStr = br.readLine(); 
            if (pontosStr != null) {
                return Integer.parseInt(pontosStr);
            }
        } catch (IOException | NumberFormatException e) {
        }
        return 100; 
    }
}