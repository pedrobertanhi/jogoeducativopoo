import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private int pontos;
    private List<Item> inventario;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontos = 0; 
        this.inventario = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public int getPontos() { return pontos; }
    
    public void adicionarPontos(int valor) {
        this.pontos += valor;
    }

    public boolean comprarItem(Item item) {
        if (this.pontos >= item.getPreco()) {
            this.pontos -= item.getPreco();
            this.inventario.add(item);
            return true;
        }
        return false;
    }

    public void removerItem(Item item) {
        this.inventario.remove(item);
    }

    public List<Item> getInventario() { return inventario; }
}