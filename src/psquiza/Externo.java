package psquiza;

public class Externo extends Pesquisador {


    /**
     * Constroi um pesquisador a partir de seu nome, funcao, biografia, email valido,
     * URL da foto valida e suas respectivas pesquisas.
     * Caso qualquer atributo esteja invalido, uma excecao sera lancada.
     *
     * @param nome      o nome do pesquisador
     * @param funcao    a funcao do pesquisador (estudante, professor ou externo)
     * @param biografia a biografia do pesquisador
     * @param email     o email do pesquisador
     * @param fotoURL   a foto do pesquisador
     */
    public Externo(String nome, String funcao, String biografia, String email, String fotoURL) {
        super(nome, funcao, biografia, email, fotoURL);
    }
}
