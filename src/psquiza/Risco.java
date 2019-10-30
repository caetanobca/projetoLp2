package psquiza;

import util.Validacao;

/**
 * Classe que representa o Risco associado a uma Atividade. O Risco tem seu nível e sua descrição.
 */
public class Risco {

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
     * @param descricao Descricao do Risco.
     */
    public Risco(String nivelRisco, String descricao) {
        validador = new Validacao();

        validador.validaNulleVazio(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(descricao, "Campo descricaoRisco nao pode ser nulo ou vazio.");
        validador.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");

        this.descricao = descricao;
        this.validador = new Validacao();
        setNivelRisco(nivelRisco);

    }


    /**
     * Metodo que atribui o valor da Classe Enum NivelRisco a partir de uma String com o nivel valido.
     * @param nivelRisco String com o nivel que o Risco ira assumir.
     */
    private void setNivelRisco(String nivelRisco) {
        if (nivelRisco.trim().toUpperCase().equals("BAIXO")) {
            this.nivelRisco = NivelRisco.BAIXO;
        } else if (nivelRisco.trim().toUpperCase().equals("MEDIO")) {
            this.nivelRisco = NivelRisco.MEDIO;
        } else if (nivelRisco.trim().toUpperCase().equals("ALTO")) {
            this.nivelRisco = NivelRisco.ALTO;
        } else {
            validador.lancaExcecao("Valor invalido do nivel do risco.");
        }

    }

    /**
     * Metodo responsavel por gerar uma representação textual do Risco.
     * @return Uma String com o nivel do Risco e sua descricao.
     */
    @Override
    public String toString() {
        return nivelRisco + " - " + descricao;
    }
}
