package psquiza;

import util.Validacao;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe que representa uma Atividade a ser realizada afim de obter resultados. Cada atividade planejada apresenta
 * uma descricao do que deve ser feito, uma duracao planejada, resultados esperados e um risco associado.
 */
public class Atividade {

    /**
     * Codigo identificador da Atividade, cada atividade eh identificada por uma String com o codigo A + valor
     * comecando a partir de 1, gerado automaticamente pelo sistema.
     */
    private String codigoIdentificador;

    /**
     * Descricao do que deve ser feito para a conclusao da Atividade.
     */
    private String descricao;

    /**
     * Objeto da classe Risco, que representa o Risco daquela Atividade.
     */
    private Risco nivelRisco;

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    /**
     * HashSet com um conjunto de Itens.
     */
    private Set<Item> itens;

    /**
     * Construtor da Classe Atividade. O Construtor não aceita parametros vazios, nulos ou não válidos, caso algum
     * valor seja, ele lançara um erro.
     *
     * @param codigoIdentificador Codigo identificador da Atividade.
     * @param descricao           Descricao da Atividade
     * @param nivelRisco          Nivel do Risco a ser associado a essa Atividade
     * @param descricaoRisco      Descricao do Risco a ser associado a essa Atividade.
     */
    public Atividade(String codigoIdentificador, String descricao,
                     String nivelRisco, String descricaoRisco) {

        this.validador = new Validacao();
        validador.validaNulleVazio(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
        validador.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");

        this.codigoIdentificador = codigoIdentificador;
        this.descricao = descricao;
        this.nivelRisco = new Risco(nivelRisco, descricaoRisco);
        this.itens = new LinkedHashSet<Item>();

    }

    /**
     * Método responsavel por cadastrar um item e adiciona-los no conjunto de itens da Atividade.
     *
     * @param item Descricao do item a ser cadastrado.
     */
    public void cadastraItem(String item) {
        validador.validaNulleVazio(item, "Item nao pode ser nulo ou vazio.");
        int codigoIdentificador = itens.size() + 1;
        Item itemNovo = new Item(codigoIdentificador, item);
        itens.add(itemNovo);
    }

    /**
     * Metodo responsavel por contar quantos itens tem o status "PENDENTE" no conjunto de itens.
     *
     * @return Numero inteiro referente ao numero de itens com o status "PENDENTE" no conjunto de itens.
     */
    public int contaItensPendentes() {
        int contador = 0;

        for (Item item : itens) {
            if ("PENDENTE".equals(item.getStatus())) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Metodo responsavel por contar quantos itens tem o status "PENDENTE" no conjunto de itens.
     *
     * @return Numero inteiro referente ao numero de itens com o status "PENDENTE" no conjunto de itens.
     */
    public int contaItensRealizados() {
        int contador = 0;

        for (Item item : itens) {
            if ("REALIZADO".equals(item.getStatus())) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Metodo responsavel por contar quantos itens tem o status "RESOLVIDO" no conjunto de itens.
     *
     * @return Numero inteiro referente ao numero de itens com o status "RESOLVIDO" no conjunto de itens.
     */
    private String exibeItens() {
        String itensExibidos = "";

        if (!itens.isEmpty()) {

            for (Item item : itens) {
                itensExibidos += " | " + item.toString();
            }


        }
        return itensExibidos;
    }

    /**
     * Metodo responsavel por gerar uma representação textual da Atvividade.
     *
     * @return Uma String com a descricao da Atividade, seu Risco e os Itens pertencentes a mesma.
     */
    @Override
    public String toString() {
        return descricao + " (" + nivelRisco.toString() + ")" + exibeItens();
    }

    /**
     * Método utilizado para comparar dois objetos Atividade, utilizando como comparador o codigo identificador de cada objeto
     * Atividade.
     *
     * @param o Objeto a ser comparado.
     * @return Variavel booleana, false caso seja diferente, true caso seja igual.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(codigoIdentificador,
                atividade.codigoIdentificador);
    }

    /**
     * Método que retorna um inteiro único baseado no codigo identificador da Atividade.
     * @return inteiro único baseado no codigo identificador da Atividade.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigoIdentificador);
    }
}
