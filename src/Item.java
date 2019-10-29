public class Item {

    private String id;
    private String nome;
    private StatusItem status;


    public Item(String id, String nome){

        this.id = id;
        this.nome = nome;
        this.status = false;

    }

    public setStatus(){
        this.status = true;
    }

    @Override
    public String toString() {
        return status + id;

    }
}
