package psquiza;

import util.Validacao;

public class Pesquisa {

    /**
     * Um texto livre com um resumo da pesquisa a ser realizada.
     */
    private String descricao;

    /**
     * Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados por vírgula e ter até 255 caracteres.
     */
    private String campoDeInteresse;

    private String codigo;

    private boolean ativada;

    private String motivoDesativacao;

    private Validacao validador;

    public Pesquisa(String descricao, String campoDeInteresse, String codigo){
        this.validador = new Validacao();

        this.validador.validaNulleVazio(descricao,  "Descricao nao pode ser nula ou vazia.");
        this.validador.validaNulleVazio(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validador.validaTamanhoString(campoDeInteresse, 3, 255,
                "Formato do campo de interesse invalido." );
//        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");

        String[] interesses = campoDeInteresse.split(",");

        for (int i = 0; i < interesses.length; i++){
            this.validador.validaNulleVazio(interesses[i], "Formato do campo de interesse invalido.");
            this.validador.validaTamanhoString(interesses[i], 3, 255,
                    "Formato do campo de interesse invalido." );
        }

        this.descricao = descricao;
        this.campoDeInteresse = campoDeInteresse;
        this.codigo = codigo;
        this.ativada = true;
    }

    public void setDescricao(String descricao) {
        this.validador.validaNulleVazio(descricao,  "Descricao nao pode ser nula ou vazia.");
        this.descricao = descricao;
    }

    public void setCampoDeInteresse(String campoDeInteresse) {
        this.validador.validaNulleVazio(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validador.validaTamanhoString(campoDeInteresse, 3, 255,
                "Formato do campo de interesse invalido." );
        this.campoDeInteresse = campoDeInteresse;
    }

    public boolean isAtivada() {
        return ativada;
    }

    public void desativa(String motivo) {
        //        this.validador.validaNulleVazio(motivo, "Motivo nao pode ser nulo ou vazio.");

        this.ativada = false;
        this.motivoDesativacao = motivo;
    }

    public void ativa() {
        this.ativada = true;
    }

    @Override
    public String toString() {
        return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
    }
}
