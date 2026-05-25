public class ItemCosmetico extends Item {
    private String raridade;

    public ItemCosmetico(String nome, int preco, String raridade) {
        super(nome, preco);
        this.raridade = raridade;
    }

    public String getRaridade() { return raridade; }
}