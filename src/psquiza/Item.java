package psquiza;

import util.Validacao;

import java.util.Objects;

/**
 * Classe que representa um Item de uma Atividade, tal Item tem um status: "REALIZADO" ou "PENDENTE" e um nome.
 */
public class Item {

    /**
     * Identificador unico do item, numero resultante da ordem de cadastro.
     */
    private int id;

    /**
     * Nome do Item.
     */
    private String nome;


    /**
     * Status do Item. Objeto da ClasseEnum StatusItem.
     */
    private StatusItem status;

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    /**
     * Construtor de Item. O Construtor não aceita parametros vazios, nulos ou não válidos, caso algum valor
     * seja, ele lançara um erro.
     *
     * @param id   Identificador unico do Item
     * @param nome Nome do Item
     */
    public Item(int id, String nome) {
        this.validador = new Validacao();
        validador.validaNulleVazio(nome, "psquiza.Item nao pode ser nulo ou vazio.");
        this.id = id;
        this.nome = nome;
        this.status = status.PENDENTE;

    }


    /**
     * Metodo que altera o status do Item, para "REALIZADO".
     */
    public void setStatus() {
        this.status = status.REALIZADO;

    }

    /**
     * Metodo de acesso do status do Item.
     *
     * @return
     */
    public String getStatus() {
        return status.toString();

    }


    /**
     * Metodo que gera uma representação textual do Item.
     *
     * @return Uma String com o status e o nome do Item.
     */
    @Override
    public String toString() {
        return status + " - " + nome;

    }

    /**
     * Método utilizado para comparar dois objetos do tipo Item, utilizando como comparador o nome do Item cadastrado.
     *
     * @param o Objeto a ser comparado.
     * @return Variavel booleana, false caso seja diferente, true caso seja igual.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return nome.equals(item.nome);
    }

    /**
     * Método que retorna um inteiro único baseado no nome do Item.
     *
     * @return inteiro único baseado no nome do Item.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }


}
