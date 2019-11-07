package psquiza;

import util.Validacao;


import java.util.ArrayList;
import java.util.Collections;
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
     * @param campoDeInteresse - Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados por
     *                         vírgula e ter até 255 caracteres.
     * @return o codigo da pesquisa, gerado a partir dos 3 primeiros caracteres do campo de interesse seguido de um
     * inteiro
     */
    public String cadastraPesquisa(String descricao, String campoDeInteresse) {
        this.validador.validaNulleVazio(descricao, "Descricao nao pode ser nula ou vazia.");
        this.validador.validaNulleVazio(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validador.validaCampoDeInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");

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
     * @param codigo               - Codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse
     *                             seguido de um inteiro..
     * @param conteudoASerAlterado - qual atributo sera alterado (descricao ou campo de interesse)
     * @param novoConteudo         - novo valor que os atributos devem assumir
     */
    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");
        //        this.validador.validaNulleVazio(conteudoASerAlterado, "Conteudo a ser alterado nao pode ser nulo ou
        //        vazio.");
        //        this.validador.validaNulleVazio(novoConteudo, "Novo conteudo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        } else if (!this.pesquisas.get(codigo).isAtivada()) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }


        if (conteudoASerAlterado.equals("CAMPO") || conteudoASerAlterado.equals("DESCRICAO")) {
            AlterarPesquisaEnum opcao = AlterarPesquisaEnum.CAMPO.valueOf(conteudoASerAlterado);
            if (opcao == AlterarPesquisaEnum.CAMPO.DESCRICAO) {
                this.validador.validaNulleVazio(novoConteudo, "Descricao nao pode ser nula ou vazia.");

                this.pesquisas.get(codigo).setDescricao(novoConteudo);

            } else if (opcao == AlterarPesquisaEnum.CAMPO.CAMPO) {
                this.validador.validaNulleVazio(novoConteudo, "Formato do campo de interesse invalido.");
                this.validador.validaCampoDeInteresse(novoConteudo, "Formato do campo de interesse invalido.");

                this.pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
            }
        } else {
            this.validador.lancaExcecao("Nao e possivel alterar esse valor de pesquisa.");
        }
    }


    /**
     * Metodo responsavel por encerrar uma pesquisa a partir do seu codigo, e assim bloqueando edicoes nessa pesquisa.
     *
     * @param codigo - codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um
     *               inteiro.
     * @param motivo - Motivo pelo qual o usuario deseja encerrar a pesquisa
     */
    public void encerraPesquisa(String codigo, String motivo) {
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");
        this.validador.validaNulleVazio(motivo, "Motivo nao pode ser nulo ou vazio.");


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
     * @param codigo - codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um
     *               inteiro
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
     * @param codigo - codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um
     *               inteiro.
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
     * @param codigo - codigo da pesquisa, formado pelos 3 primeiros caracteres do campo de interesse seguido de um
     *               inteiro
     * @return - true caso a pesquisa esteja ativa e false caso esteja encerrada
     */
    public boolean pesquisaEhAtiva(String codigo) {
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }

        return this.pesquisas.get(codigo).isAtivada();
    }

    /**
     * Metodo responsavel por associar um Problema para uma Pesquisa, validando o Id de Pesquisa inserido e passando
     * um objeto do tipo Problema para Pesquisa.
     *
     * @param idPesquisa Identificacao da Pesquisa.
     * @param problema   Objeto do tipo Problema a ser associado.
     * @return true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaProblemaEmPesquisa(String idPesquisa, Problema problema) {
        boolean associou = false;
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");

        } else if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");

        } else {
            associou = this.pesquisas.get(idPesquisa).associaProblemaEmPesquisa(problema);

        }

        return associou;
    }

    /**
     * Metodo responsavel por desassociar um Problema para uma Pesquisa, validando o Id de Pesquisa inserido e passando
     * um objeto do tipo Problema para Pesquisa.
     *
     * @param idPesquisa Identificacao da Pesquisa.
     * @param problema   Objeto do tipo Problema a ser desassociado.
     * @return true caso a desassociacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaProblemaEmPesquisa(String idPesquisa, Problema problema) {
        boolean desassociou = false;
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");

        } else if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");

        } else {
            desassociou = this.pesquisas.get(idPesquisa).desassociaProblemaEmPesquisa(problema);

        }

        return desassociou;
    }

    /**
     * Metodo responsavel por associar um Objetivo em uma Pesquisa, validando o Id de Pesquisa inserido e passando
     * um objeto do tipo Objetivo para Pesquisa.
     *
     * @param idPesquisa Identificacao da Pesquisa.
     * @param objetivo   Objeto do tipo Objetivo a ser associado.
     * @return true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaObjetivoEmPesquisa(String idPesquisa, Objetivo objetivo) {
        boolean associou = false;
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");

        } else if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");

        } else {
            associou = this.pesquisas.get(idPesquisa).associaObjetivoEmPesquisa(objetivo);

        }

        return associou;
    }

    /**
     * Metodo responsavel por desassociar um Objetivo de uma Pesquisa, validando o Id de Pesquisa inserido e passando
     * um objeto do tipo Objetivo para Pesquisa.
     *
     * @param idPesquisa Identificacao da Pesquisa.
     * @param objetivo   Objeto do tipo Objetivo a ser desassociado.
     * @return true caso a desassociacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaObjetivoEmPesquisa(String idPesquisa, Objetivo objetivo) {
        boolean desassociou = false;
        if (!this.pesquisas.containsKey(idPesquisa)) {

            this.validador.lancaExcecao("Pesquisa nao encontrada.");

        } else if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");

        } else {
            desassociou = this.pesquisas.get(idPesquisa).desassociaObjetivoEmPesquisa(objetivo);

        }

        return desassociou;
    }

    /**
     * Metedo responsavel por buscar um termo nas descricoes e nos campos de interesse das Pesquisas.
     * @param termo Texto que sera usado como referencia na busca.
     * @return uma lista com todos o resultados.
     */
    public List<String> busca(String termo) {
        validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

        List<String> results = new ArrayList<>();

        for (String codigo: pesquisas.keySet()){
            if (this.pesquisas.get(codigo).getDescricao().contains(termo)){
                results.add(codigo + ": " + this.pesquisas.get(codigo).getDescricao());
            }
            if (this.pesquisas.get(codigo).getCampoDeInteresse().contains(termo)){
                results.add(codigo + ": " + this.pesquisas.get(codigo).getCampoDeInteresse());
            }
        }

        Collections.sort(results, new ComparadorBusca());

        return results;
    }

  /*
     * Metodo responsavel por listar, em uma String, todas as pesquisas cadastradas de acordo com o criterio recebido.
     * O Metodo nao aceita valores diferentes de: "PROBLEMA", "OBJETIVOS", "PESQUISA".
     *
     * @param ordem Criterio de ordenacao das Pesquisas.
     * @return String com a representacao textual de todas as Pesquisas ordenadas de acordo com o criterio inserido
     * na variavel ordem.
     */
    public String listaPesquisas(String ordem) {
        validador.validaTipoOrdenacao(ordem, "Valor invalido da ordem");
        ArrayList<Pesquisa> todasAsPesquisas = new ArrayList<>();

        if (ordem.equals("PROBLEMA")) {
            todasAsPesquisas = ordenaListaPesquisaProblema();
        } else if (ordem.equals("OBJETIVOS")) {
            todasAsPesquisas = ordenaListaPesquisaObjetivo();
        } else if (ordem.equals("PESQUISA")) {
            todasAsPesquisas = ordenaListaPesquisa();

        }

        String listaDasPesquisas = "";
        if (!todasAsPesquisas.isEmpty()) {
            for (Pesquisa pesquisa : todasAsPesquisas) {
                listaDasPesquisas += pesquisa.toString() + " | ";
            }

            listaDasPesquisas = listaDasPesquisas.substring(0, listaDasPesquisas.length() - 3);
        }

        return listaDasPesquisas;
    }

    /**
     * Metodo privado que cria um ArrayList com todas as Pesquisas e as ordena de acordo com seu Codigo de
     * Identificacao, do maior para o menor codigo.
     *
     * @return um ArrayList com todas as Pesquisas.
     */
    private ArrayList<Pesquisa> ordenaListaPesquisa() {
        ArrayList<Pesquisa> pesquisasArray = new ArrayList<>();
        for (Pesquisa pesquisa : pesquisas.values()) {
            pesquisasArray.add(pesquisa);
        }

        Collections.sort(pesquisasArray);
        return pesquisasArray;
    }

    /**
     * Metodo privado que cria um ArrayList com todas as Pesquisas e as ordena de acordo com a identificacao de Problema
     * associado a elas, caso a Pesquisa nao tenha Problema associado, ela deve vir apos as pesquisas com Problema,
     * ordenadas de acordo com sem codigo identificador, do maior para o menor.
     *
     * @return um ArrayList com todas as Pesquisas.
     */
    private ArrayList<Pesquisa> ordenaListaPesquisaProblema() {
        ArrayList<Pesquisa> pesquisasComProblema = new ArrayList<>();
        ArrayList<Pesquisa> pesquisasSemProblema = new ArrayList<>();

        for (Pesquisa pesquisa : pesquisas.values()) {
            if (pesquisa.getProblemaAssociado() == null) {
                pesquisasSemProblema.add(pesquisa);
            } else {
                pesquisasComProblema.add(pesquisa);
            }
        }

        Collections.sort(pesquisasComProblema, new ComparadorPesquisaPorProblema());

        Collections.sort(pesquisasSemProblema);

        ArrayList<Pesquisa> todasAsPesquisas = new ArrayList<>();

        todasAsPesquisas.addAll(pesquisasComProblema);
        todasAsPesquisas.addAll(pesquisasSemProblema);

        return todasAsPesquisas;
    }

    /**
     * Metodo privado que cria um ArrayList com todas as Pesquisas e as ordena de acordo com a quantidade de Objetivos
     * associado a Pesquisa. Caso tenha Pesquisas com a mesma quantidade de objetivos, deve-se vir primeiro a Pesquisa
     * com objeitvo de maior Id. Para as pesquisas sem Objetivo, devem ser ordenadas de acordo com sem codigo
     * identificador, do maior para o menor.
     *
     * @return um ArrayList com todas as Pesquisas.
     */
    private ArrayList<Pesquisa> ordenaListaPesquisaObjetivo() {
        ArrayList<Pesquisa> pesquisasComObjetivo = new ArrayList<>();
        ArrayList<Pesquisa> pesquisasSemObjetivo = new ArrayList<>();

        for (Pesquisa pesquisa : pesquisas.values()) {
            if (!pesquisa.getObjetivosAssociados().isEmpty()) {
                pesquisasComObjetivo.add(pesquisa);
            } else {
                pesquisasSemObjetivo.add(pesquisa);
            }
        }

        Collections.sort(pesquisasComObjetivo, new ComparadorPesquisaPorObjetivo());

        Collections.sort(pesquisasSemObjetivo);

        ArrayList<Pesquisa> todasAsPesquisas = new ArrayList<>();

        todasAsPesquisas.addAll(pesquisasComObjetivo);
        todasAsPesquisas.addAll(pesquisasSemObjetivo);

        return todasAsPesquisas;
    }

}

