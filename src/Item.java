public class Item {

    private int id;
    private String descricao;
    private StatusItem status;

    public Item(int id, String desricao){

        this.id = id;
        this.descricao = descricao;
        this.status = StatusItem.PENDENTE;

    }

    public setStatus(){
        this.status = StatusItem.REALIZADO;
    }


}
