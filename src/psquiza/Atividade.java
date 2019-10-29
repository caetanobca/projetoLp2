package psquiza;

import util.Validacao;

import java.util.ArrayList;
import java.util.Objects;

public class Atividade {


    private String codigoIdentificador;
    private String descricao;

    private Risco nivelRisco;
    private Validacao validador;


    private ArrayList<Item> itens;

    public Atividade(String codigoIdentificador, String descricao, String nivelRisco, String descricaoRisco) {

        this.validador = new Validacao();
        validador.validaNulleVazio(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
        validador.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");

        this.codigoIdentificador = codigoIdentificador;
        this.descricao = descricao;
        this.nivelRisco = new Risco(nivelRisco, descricaoRisco);
    }

    public void cadastraItem(String item) {
        String codigoIdentificador = "ITEM" + Integer.toString(itens.size() + 1);
        Item itemNovo = new Item(codigoIdentificador, item);
    }

    private String exibeItens(){
        String itensExibidos = "";
        for(Item item:itens){
            itensExibidos += item.toString() + " | ";
        }

        itensExibidos = itensExibidos.substring(0, itensExibidos.length() - 3);
    }

    @Override
    public String toString() {
        return descricao + nivelRisco.toString() + exibeItens();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(codigoIdentificador, atividade.codigoIdentificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoIdentificador);
    }
}
