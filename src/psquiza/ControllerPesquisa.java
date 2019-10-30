package psquiza;

import util.Validacao;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe responssavel por gerenciar as pesquisas cadastradas no sistema, as pesquisas sao armazenadas em um mapa
 * usando como chave um codigo gerado pelos 3 caractres do campo de interesse de cada pesquisa, seguido de um inteiro
 */
public class ControllerPesquisa {

    /**
     * Mapa responsavel por guardar as pesquisas cadastradas no sistema, usando como chave um codigo formado pelos 3
     * primeiros caracteres do campo de interesse seguido de um inteiro.
     */
    private Map<String, Pesquisa> pesquisas;

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    public ControllerPesquisa() {
        this.pesquisas = new HashMap<String, Pesquisa>();
        this.validador = new Validacao();
    }

    /**
     * Metodo que adiciona uma pesquisa ao sistema, a partir da sua descricao e do seu campo de interesse
     *
     * @param descricao        - Um texto livre com um resumo da pesquisa a ser realizada.
     * @param campoDeInteresse - Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados por vírgula e ter até 255 caracteres.
     * @return o codigo da pesquisa, gerado a partir dos 3 primeiros caracteres do campo de interesse seguido de um inteiro
     */
    public String cadastraPesquisa(String descricao, String campoDeInteresse) {
        this.validador.validaNulleVazio(descricao, "Descricao nao pode ser nula ou vazia.");
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

        String codigoPesquisa;
        codigoPesquisa = this.geraCodigoDePesquisa(campoDeInteresse.substring(0, 3).toUpperCase(), 1);

        this.pesquisas.put(codigoPesquisa, new Pesquisa(descricao, campoDeInteresse, codigoPesquisa));

        return codigoPesquisa;
    }

    /**
     * Metodo que a partir dos 3 primeiros caracteres do campo de interesse seguido do menor inteiro possitivo ainda
     * nao usado, gera o codigo da pesquisa.
     *
     * @param codigo - possivel codigo da pesquisa
     * @param i      - inteiro que sera usado no codigo, caso o codigo formado ainda nao exista no sistema
     * @return - o codigo que a pesquisa tera
     */
    private String geraCodigoDePesquisa(String codigo, int i) {
        if (this.pesquisas.containsKey(codigo + i)) {
            i++;
            this.geraCodigoDePesquisa(codigo, i);
        }
        return codigo + i;
    }

    /**
     * Metodo responsavel por alterar o valor de determinado atributo (descricao ou campo de interesse) em uma pesquisa.
     *
     * @param codigo               - Codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um inteiro..
     * @param conteudoASerAlterado - qual atributo sera alterado (descricao ou campo de interesse)
     * @param novoConteudo         - novo valor que os atributos devem assumir
     */
    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");
        //        this.validador.validaNulleVazio(conteudoASerAlterado, "Conteudo a ser alterado nao pode ser nulo ou vazio.");
        //        this.validador.validaNulleVazio(novoConteudo, "Novo conteudo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }

        if (!this.pesquisas.get(codigo).isAtivada()) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }

        if (!(conteudoASerAlterado.equals("DESCRICAO") || conteudoASerAlterado.equals("CAMPO"))) {
            this.validador.lancaExcecao("Nao e possivel alterar esse valor de pesquisa.");
        } else if (conteudoASerAlterado.equals("DESCRICAO")) {

            this.validador.validaNulleVazio(novoConteudo, "Descricao nao pode ser nula ou vazia.");
            this.pesquisas.get(codigo).setDescricao(novoConteudo);

        } else if (conteudoASerAlterado.equals("CAMPO")) {

            this.validador.validaNulleVazio(novoConteudo, "Formato do campo de interesse invalido.");
            this.validador.validaTamanhoString(novoConteudo, 3, 255,
                    "Formato do campo de interesse invalido.");

            this.pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
        }

    }

    /**
     * Metodo responsavel por encerrar uma pesquisa a partir do seu codigo, e assim bloqueando edicoes nessa pesquisa.
     *
     * @param codigo - codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um inteiro.
     * @param motivo - Motivo pelo qual o usuario deseja encerrar a pesquisa
     */
    public void encerraPesquisa(String codigo, String motivo) {
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");
        //        this.validador.validaNulleVazio(motivo, "Motivo nao pode ser nulo ou vazio.");


        if (!this.pesquisas.containsKey(codigo)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if (!this.pesquisas.get(codigo).isAtivada()) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        this.pesquisas.get(codigo).desativa(motivo);
    }

    /**
     * Metodo responsavel por ativar uma pesquisa a partir do seu codigo, e assim tornando possivel alterar determinada
     * pesquisa
     *
     * @param codigo - codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um inteiro
     */
    public void ativaPesquisa(String codigo) {
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if (this.pesquisas.get(codigo).isAtivada()) {
            this.validador.lancaExcecao("Pesquisa ja ativada.");
        }
        this.pesquisas.get(codigo).ativa();
    }

    /**
     * Metodo que exibe uma determinada pesquisa a partir do seu codigo
     *
     * @param codigo - codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um inteiro.
     * @return - a representacao textual de uma pesquisa
     */
    public String exibePesquisa(String codigo) {

        if (!this.pesquisas.containsKey(codigo)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }

        return this.pesquisas.get(codigo).toString();
    }

    /**
     * Metodo que verifica se determinada pesquisa esta ativa ou encerrada
     *
     * @param codigo - codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um inteiro
     * @return - true caso a pesquisa esteja ativa e false caso esteja encerrada
     */
    public boolean pesquisaEhAtiva(String codigo) {
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }

        return this.pesquisas.get(codigo).isAtivada();
    }
}