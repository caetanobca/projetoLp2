package psquiza;

public class Item {

    private String id;
    private String nome;
    private boolean status;


    public Item(String id, String nome){

        this.id = id;
        this.nome = nome;
        this.status = false;

    }

    public void setStatus(){
        this.status = true;
    }

    @Override
    public String toString() {
        return status + id;

    }
}
