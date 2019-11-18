package psquiza;

import util.Validacao;

import java.util.Objects;

/**
 * Representacao de um pesquisador que possui nome, funcao, biografia, email e uma URL para foto.
 * Um pesquisador pode ser um estudante, professor ou externo. Ele pode estar ativo ou desativado.
 * Cada pesquisador eh identificado unicamente pelo seu email.
 *
 * @author fernandolc
 */
public class Pesquisador {

    /**
     *  O nome do pesquisador.
     */
    private String nome;

    /**
     * A funcao do pesquisador (estudante, professor ou externo).
     */
    private String funcao;

    /**
     * A biografia do pesquisador.
     */
    private String biografia;

    /**
     * O email do pesquisador. Deve ter pelo menos uma letra e/ou numero antes e depois do @.
     */
    private String email;

    /**
     * A URL da foto do pesquisador. Deve comecar com "http://" ou "https://".
     */
    private String fotoURL;

    /**
     * Representa se o pesquisador estah ativo ou nao. Estando desativado, nenhuma alteracao pode ser realizada
     * no pesquisador.
     */
    private boolean ativo;

    /**
     * Objeto da classe Validacao, que tem como funcao, lancar excecoes caso seja necessario(Entradas nulas e vazias).
     */
    private Validacao validador;

    private boolean especializado;

    private Especialidade especialidade;

    /**
     * Constroi um pesquisador a partir de seu nome, funcao, biografia, email valido,
     * URL da foto valida e suas respectivas pesquisas.
     * Caso qualquer atributo esteja invalido, uma excecao sera lancada.
     * @param nome o nome do pesquisador
     * @param funcao a funcao do pesquisador (estudante, professor ou externo)
     * @param biografia a biografia do pesquisador
     * @param email o email do pesquisador
     * @param fotoURL a foto do pesquisador
     */
    public Pesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
        this.validador = new Validacao();
        validador.validaNulleVazio(nome, "Campo nome nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(funcao, "Campo funcao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(biografia, "Campo biografia nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(email, "Campo email nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
        validador.validaEmail(email, "Formato de email invalido.");
        validador.validaFoto(fotoURL, "Formato de foto invalido.");

        this.nome = nome;
        this.funcao = funcao;
        this.biografia = biografia;
        this.email = email;
        this.fotoURL = fotoURL;
        this.ativo = true;
    }

    public boolean isEspecializado() {
        return especializado;
    }

    public void setEspecializado(boolean especializado) {
        this.especializado = especializado;
    }

    /**
     * Modifica o nome do pesquisador para um novo. Caso o nome seja nulo/vazio, uma
     * excecao sera lancada.
     *
     * @param nome o novo nome do pesquisador
     */
    public void setNome(String nome) {
        this.validador.validaNulleVazio(nome,"Campo nome nao pode ser nulo ou vazio.");
        this.nome = nome;
    }

    /**
     * Modifica a funcao do pesquisador para uma nova. Caso a nova funcao seja nula/vazia, uma
     * excecao sera lancada.
     *
     * @param funcao a nova funcao do pesquisador
     */
    public void setFuncao(String funcao) {
        this.validador.validaNulleVazio(funcao,"Campo funcao nao pode ser nulo ou vazio.");
        this.funcao = funcao;
    }

    /**
     * Modifica a biografia do pesquisador para uma nova. Caso a nova biografia seja nula/vazia, uma
     * excecao sera lancada.
     *
     * @param biografia a nova biografia do pesquisador
     */
    public void setBiografia(String biografia) {
        this.validador.validaNulleVazio(biografia,"Campo biografia nao pode ser nulo ou vazio.");
        this.biografia = biografia;
    }

    /**
     * Modifica o email do pesquisador para um novo. Caso o novo email seja nulo/vazio ou em formato
     * invalido, uma excecao sera lancada.
     *
     * @param email o novo email do pesquisador
     */
    public void setEmail(String email) {
        this.validador.validaNulleVazio(email,"Campo email nao pode ser nulo ou vazio.");
        validador.validaEmail(email, "Formato de email invalido.");
        this.email = email;
    }

    /**
     * Modifica da URL da foto para uma nova. Caso a nova URL seja nula/vazia ou em formato invalido,
     * uma excecao sera lancada.
     *
     * @param fotoURL a nova URL da foto do pesquisador
     */
    public void setFotoURL(String fotoURL) {
        this.validador.validaNulleVazio(fotoURL,"Campo fotoURL nao pode ser nulo ou vazio.");
        this.validador.validaFoto(fotoURL,"Formato de foto invalido.");
        this.fotoURL = fotoURL;
    }

    /**
     * Ativa um pesquisador, caso ele enao esteja ativo
     */
    public void ativa() {
        if (this.ativo == true) {
            throw new IllegalArgumentException("Pesquisador ja ativado.");
        }
        this.ativo = true;
    }

    /**
     * Desativa um pesquisador, caso ele nao esteja desativado
     */
    public void desativa() {
        if (this.ativo == false) {
            throw new IllegalArgumentException("Pesquisador ja ativado.");
        }
        this.ativo = false;
    }

    /**
     * Retorna um booleano que representa o status do pesquisador (ativo ou desativado).
     *
     * @return a representacao booleana do status do pesquisador
     */
    public boolean isAtivo(){
        return ativo;
    }


    public String getFuncao() {
        return funcao;
    }

    /**
     * Retorna uma representacao em String do pesquisador. A representacao segue o formato:
     * "NOME (FUNCAO) - BIOGRAFIA - EMAIL - FOTOURL".
     *
     * @return a representacao em String de um pesquisador
     */
    @Override
    public String toString() {
        return this.nome + " (" + this.funcao + ") - " + this.biografia + " - " + this.email + " - " + this.fotoURL;
    }

    /**
     * Verifica se o pesquisador atual eh igual a outro com base em seus emails. Cada pesquisador
     * eh identificado unicamente por seu email.
     *
     * @param o eh outro objeto do tipo Pesquisador a ser verificado
     * @return representacao booleana constatando se sao ou nao o mesmo objeto
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesquisador that = (Pesquisador) o;
        return Objects.equals(email, that.email);
    }

    /**
     * Retorna inteiro unico baseado no email do pesquisador.
     *
     * @return inteiro unico baseado no email que identifica o pesquisador
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Metodo responsavel por pegar a biografia do Pesquisador.
     * @return biografia do Pesquisador.
     */
    public String getBiografia() {
        return this.biografia;
    }

    

    public void especializaProfessor(String formacao, String unidade, String data) {
        this.especialidade = new Professor(formacao, unidade, data);
    }

    public void especializaEstudante(int semestre, double IEA) {
        this.especialidade = new Estudante(semestre, IEA);
    }

    public void alteraEspecialidade(String atributo, int valorUsarSemestre) {
        this.especialidade.edita(atributo, valorUsarSemestre + "");
    }

    public void alteraEspecialidade(String atributo,double valorUsarIEA) {
        this.especialidade.edita(atributo, valorUsarIEA + "");
    }

    public void alteraEspecialidade(String atributo, String novoValor) {
        this.especialidade.edita(atributo, novoValor);
    }

    public String exibeEspecializado() {
        return this.toString() + this.especialidade.toString();
    }
}
