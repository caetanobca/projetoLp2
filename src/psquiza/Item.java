package psquiza;

import util.Validacao;

public class Item {

    private int id;
    private String nome;
    private StatusItem status;
    private Validacao validador;

    public Item(int id, String nome){
        this.validador = new Validacao();
        validador.validaNulleVazio(nome, "psquiza.Item nao pode ser nulo ou vazio.");
        this.id = id;
        this.nome = nome;
        this.status = status.PENDENTE;

    }

    public void setStatus(){
        this.status = status.REALIZADO;
    }

    public String getStatus(){
        return status.toString();
    }

    @Override
    public String toString() {
        return status + " - " + nome;

    }
}
