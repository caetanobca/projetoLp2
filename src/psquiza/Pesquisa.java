package psquiza;

import util.Validacao;

import java.util.Objects;

/**
 * Classe que representa uma pesquisa, que tem descricao, campo de interesse, codigo, e uma variavel que indica
 * se esta ativa ou encerrada
 */
public class Pesquisa {

    /**
     * Um texto livre com um resumo da pesquisa a ser realizada.
     */
    private String descricao;

    /**
     * Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados por vírgula e ter até 255 caracteres.
     */
    private String campoDeInteresse;

    /**
     * Codiogo gerado automaticamente pelos 3 primeiros caracteres do campo de interesse e um inteiro. O codigo e o indentificador unico.
     */
    private String codigo;

    /**
     * Variavel do tipo boolean, que assume o valor true caso a pesquisa esteja ativa e false caso esteja encerrada.
     */
    private boolean ativada;

    /**
     * Um texto livre com um resumo do motivo da pesquisa ter sido encerrada.
     */
    private String motivoDesativacao;

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
        this.validador = new Validacao();

        this.validador.validaNulleVazio(descricao, "Descricao nao pode ser nula ou vazia.");
        this.validador.validaNulleVazio(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validador.validaTamanhoString(campoDeInteresse, 3, 255,
                "Formato do campo de interesse invalido.");
//        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");

        String[] interesses = campoDeInteresse.split(",");

        for (int i = 0; i < interesses.length; i++) {
            this.validador.validaNulleVazio(interesses[i], "Formato do campo de interesse invalido.");
            this.validador.validaTamanhoString(interesses[i], 3, 255,
                    "Formato do campo de interesse invalido.");
        }

        this.descricao = descricao;
        this.campoDeInteresse = campoDeInteresse;
        this.codigo = codigo;
        this.ativada = true;
    }

    /**
     * Metodo responsavel por alterar o valor da descricao da pesquisa.
     *
     * @param descricao - novo valor da descricao
     */
    public void setDescricao(String descricao) {
        this.validador.validaNulleVazio(descricao, "Descricao nao pode ser nula ou vazia.");
        if (this.ativada == false) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        this.descricao = descricao;
    }

    /**
     * Metodo responsavel por alterar o valor do campo de interesse da pesquisa.
     *
     * @param campoDeInteresse - novo valor do campo de interesse
     */
    public void setCampoDeInteresse(String campoDeInteresse) {
        this.validador.validaNulleVazio(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validador.validaTamanhoString(campoDeInteresse, 3, 255,
                "Formato do campo de interesse invalido.");

        String[] interesses = campoDeInteresse.split(",");

        if (interesses.length > 4) {
            throw new IllegalArgumentException("Formato do campo de interesse invalido.");
        } else {
            for (int i = 0; i < interesses.length; i++) {
                this.validador.validaNulleVazio(interesses[i], "Formato do campo de interesse invalido.");
                this.validador.validaTamanhoString(interesses[i], 3, 255,
                        "Formato do campo de interesse invalido.");
            }
        }

        if (this.ativada == false) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        this.campoDeInteresse = campoDeInteresse;
    }

    /**
     * Metodo responsavel por verificar se a pesquisa esta ativa ou encerrada
     *
     * @return true caso a pesquisa esteja ativa e false caso esteja encerrada
     */
    public boolean isAtivada() {
        return ativada;
    }

    /**
     * Metodo responsavel por desativar uma pesquisa e assim bloqueando qualquer alteracao na pesquisa.
     *
     * @param motivo - motivo pelo qual o usuario deseja desativar essa pesquisa
     */
    public void desativa(String motivo) {
        this.validador.validaNulleVazio(motivo, "Motivo nao pode ser nulo ou vazio.");

        this.ativada = false;
        this.motivoDesativacao = motivo;
    }

    /**
     * metodo responsavel por ativar a pesquisa e assim permitindo alteracoes no objeto.
     */
    public void ativa() {
        this.ativada = true;
    }

    /**
     * Metodo que cria uma representacao textual da pesquisa.
     *
     * @return uma representacao da pesquisa.
     */
    @Override
    public String toString() {
        return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
    }

    /**
     * Metodo que compara se duas pesquisa sao iguais, usando como criterio o codigo da pesquisa
     * @param o - objeto que sera comparado
     * @return - true caso as pesquisas tem o mesmo codigo e false caso os codigos sejam diferentes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesquisa pesquisa = (Pesquisa) o;
        return codigo.equals(pesquisa.codigo);
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}