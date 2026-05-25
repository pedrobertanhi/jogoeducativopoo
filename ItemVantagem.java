public class ItemVantagem extends Item {
    private String jogoAlvo;

    public ItemVantagem(String nome, int preco, String jogoAlvo) {
        super(nome, preco);
        this.jogoAlvo = jogoAlvo;
    }

    public String getJogoAlvo() { 
        return jogoAlvo; 
    }
}