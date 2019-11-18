package psquiza;

import util.Validacao;

import java.io.Serializable;

/**
 * Classe que representa o Risco associado a uma Atividade. O Risco tem seu nível e sua descrição.
 */
public class Risco implements Serializable {


    /**
     * Descricao do Risco
     */
    private String descricao;

    /**
     * Nivel do Risco. Objeto da classe Enum.
     */
    private NivelRisco nivelRisco;

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    /**
     * Construtor da Classe Risco. O Construtor não aceita parametros vazios, nulos ou não válidos, caso algum valor
     * seja, ele lançara um erro.
     *
     * @param nivelRisco Nivel do Risco.
     * @param descricao  Descricao do Risco.
     */
    public Risco(String nivelRisco, String descricao) {
        validador = new Validacao();

        validador.validaNulleVazio(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(descricao, "Campo descricaoRisco nao pode ser nulo ou vazio.");
        validador.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");

        this.descricao = descricao;
        this.validador = new Validacao();

        this.nivelRisco = NivelRisco.valueOf(nivelRisco.trim().toUpperCase());

    }

    /**
     * Metodo responsavel por gerar uma representação textual do Risco.
     *
     * @return Uma String com o nivel do Risco e sua descricao.
     */
    @Override
    public String toString() {
        return nivelRisco + " - " + descricao;
    }

    /**
     * Metodo responsavel por pegar o valor da variavel descricao.
     *
     * @return o valor da variavel descricao.
     */
    public String getDescricao() {
        return this.descricao;
    }
}
