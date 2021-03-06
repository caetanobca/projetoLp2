package psquiza.pesquisa;

import psquiza.ComparadorBusca;
import psquiza.Pesquisa;
import psquiza.atividade.*;
import psquiza.objetivo.Objetivo;
import psquiza.pesquisador.Pesquisador;
import psquiza.problema.Problema;
import util.Validacao;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


/**
 * Classe responssavel por gerenciar as pesquisas cadastradas no sistema, as pesquisas sao armazenadas em um mapa
 * usando como chave um codigo gerado pelos 3 caractres do campo de interesse de cada pesquisa, seguido de um inteiro
 */
public class ControllerPesquisa implements Serializable {


    /**
     * Mapa responsavel por guardar as pesquisas cadastradas no sistema, usando como chave um codigo formado pelos 3
     * primeiros caracteres do campo de interesse seguido de um inteiro.
     */
    private Map<String, Pesquisa> pesquisas;

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    /**
     * Enum que define a estrategia de sugestão de próxima Atividade.
     */
    private SugestaoProxAtividade estrategia;

    private Map<String, Objetivo> objetivos;
    private Map<String, Problema> problemas;
    private Map<String, Atividade> atividades;
    private Map<String, Pesquisador> pesquisadores;

    public ControllerPesquisa(Map<String, Objetivo> objetivoMap, Map<String, Problema> problemaMap, Map<String, Atividade> atividadeMap, Map<String, Pesquisador> pesquisadorMap) {
        this.pesquisas = new HashMap<String, Pesquisa>();
        this.validador = new Validacao();
        this.estrategia = SugestaoProxAtividade.MAIS_ANTIGA;
        this.objetivos = objetivoMap;
        this.problemas = problemaMap;
        this.pesquisadores = pesquisadorMap;
        this.atividades = atividadeMap;
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
        codigoPesquisa = this.geraCodigoDePesquisa(campoDeInteresse.substring(0, 3).toUpperCase(), 0);

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
        if (!this.pesquisas.containsKey(codigo + (i+1))) {
            return codigo + (i+1);
        }
        return this.geraCodigoDePesquisa(codigo, i+1);
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
    public boolean associaProblema(String idPesquisa, String problema) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(problema, "Campo idProblema nao pode ser nulo ou vazio.");

        boolean associou = false;
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");

        } else if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");

        } else {
            associou = this.pesquisas.get(idPesquisa).associaProblema(this.problemas.get(problema));

        }

        return associou;
    }

    /**
     * Metodo responsavel por desassociar um Problema para uma Pesquisa, validando o Id de Pesquisa inserido e passando
     * um objeto do tipo Problema para Pesquisa.
     *
     * @param idPesquisa Identificacao da Pesquisa.
     *
     * @return true caso a desassociacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaProblema(String idPesquisa){
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        boolean desassociou = false;
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");

        } else if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");

        } else {
            desassociou = this.pesquisas.get(idPesquisa).desassociaProblema();

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
    public boolean associaObjetivo(String idPesquisa, String objetivo) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(objetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

        boolean associou = false;
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");

        } else if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");

        } else {
            associou = this.pesquisas.get(idPesquisa).associaObjetivo(this.objetivos.get(objetivo));

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
    public boolean desassociaObjetivo(String idPesquisa, String objetivo) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(objetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
        boolean desassociou = false;
        if (!this.pesquisas.containsKey(idPesquisa)) {

            this.validador.lancaExcecao("Pesquisa nao encontrada.");

        } else if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");

        } else {
            desassociou = this.pesquisas.get(idPesquisa).desassociaObjetivo(this.objetivos.get(objetivo));

        }

        return desassociou;
    }

    /**
     * Associa Atividade em uma Pesquisa. Uma Atividade so pode so pode ser associada em
     * uma Pesquisa se ela estiver ativa, ou se a atividade nao ja tiver sido associada.
     *
     * @param idPesquisa codigo identificador da Pesquisa
     * @param atividade a Atividade a ser associada
     * @return valor booleano que representa o sucesso ou nao da associacao
     */
    public boolean associaAtividade(String idPesquisa, String atividade) {
        validador.validaNulleVazio(idPesquisa,"Campo codigoPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(atividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        if(!this.atividades.containsKey(atividade)){
            this.validador.lancaExcecao("Atividade nao encontrada");
        }

        return this.pesquisas.get(idPesquisa).associaAtividade(this.atividades.get(atividade));
    }

    /**
     * Desassocia uma atividade em uma Pesquisa. Uma Atividade nao pode ser dessasociada se
     * a pesquisa estiver desativada, ou se a atividade nao ja tiver sido associada.
     *
     * @param idPesquisa codigo identificador da Pesquisa
     * @param atividade a Atividade a ser dessasociada
     * @return valor booleano que representa o sucesso ou nao da operacao
     */
    public boolean desassociaAtividade(String idPesquisa, String atividade) {
        validador.validaNulleVazio(idPesquisa,"Campo codigoPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(atividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        if(!this.atividades.containsKey(atividade)){
            this.validador.lancaExcecao("Atividade nao encontrada");
        }
        if(!this.atividades.get(atividade).isAssociada()){
            return false;
        }
        return this.pesquisas.get(idPesquisa).desassociaAtividade(this.atividades.get(atividade));
    }

    /**
     * Metodo responsavel por associar um pesquisador  a determinadapesquisa, para identificacao de qual pesquisa e qual
     * pesquisador serao relacionados, o idPesquisa e o emailPesquisador sao utilizados. Uma excecao e lancada caso o usuario
     * queira fornecer algum valor nulo ou vazio para os parametros.
     * @param idPesquisa e o identificador unico da pesquisa
     * @param pesquisador e o pesquisador
     */
    public boolean associaPesquisador(String idPesquisa, String pesquisador) {
        validador.validaNulleVazio(idPesquisa,"Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(pesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        if(!this.pesquisadores.containsKey(pesquisador)){
            this.validador.lancaExcecao("Pesquisadora nao encontrada.");
        }
        return this.pesquisas.get(idPesquisa).associaPesquisador(this.pesquisadores.get(pesquisador));
    }

    /**
     * Metodo responsavel por desassociar uma pesquisa de um determinado pesquisador, para identificacao de qual pesquisa e qual
     * pesquisador serao desassociados, o idPesquisa e o emailPesquisador sao utilizados. Uma excecao e lancada caso o usuario
     * queira fornecer algum valor nulo ou vazio para os parametros.
     * @param idPesquisa e o identificador unico da pesquisa
     * @param pesquisador e o pesquisador
     */
    public boolean desassociaPesquisador(String idPesquisa, String pesquisador) {
        validador.validaNulleVazio(idPesquisa,"Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(pesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if (!this.pesquisas.containsKey(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if (!pesquisaEhAtiva(idPesquisa)) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        if(!this.pesquisadores.containsKey(pesquisador)){
            this.validador.lancaExcecao("Pesquisadora nao encontrada.");
        }
        return this.pesquisas.get(idPesquisa).desassociaPesquisador(this.pesquisadores.get(pesquisador));
    }

    /**
     * Metodo responsavel por atualizar os mapas desse objeto
     * @param objetivoMap - mapa de objetivo
     * @param problemaMap - mapa de problemas
     * @param atividadeMap - mapa de atividade
     * @param pesquisadorMap - mapa de pesquisador
     */
    public void carregaMapas(Map<String, Objetivo> objetivoMap, Map<String, Problema> problemaMap, Map<String, Atividade> atividadeMap, Map<String, Pesquisador> pesquisadorMap){
        this.objetivos = objetivoMap;
        this.problemas = problemaMap;
        this.atividades = atividadeMap;
        this.pesquisadores = pesquisadorMap;
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

  /**
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

    /**
     * Metodo de acesso para o mapa que armazena Pesquisa.
     * @return um Mapa com as Pesquisas do sistema
     */
    public Map<String, Pesquisa> getPesquisas() {
        return pesquisas;
    }


    /**
     * Metodo responsavel por gravar um resumo da pesquisa em um arquivo txt
     * @param codigoPesquisa - codigo da pesquisa cuja se deseja criar um resumo
     * @throws IOException
     */
    public void gravaResumo(String codigoPesquisa) throws IOException {
        this.validador.validaNulleVazio(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
        if (!this.pesquisas.containsKey(codigoPesquisa)){
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        this.pesquisas.get(codigoPesquisa).gravaResumo();
    }

    /**
     * Metodo responsavel por gravar os resutados da pesquisa em um arquivo txt
     * @param codigoPesquisa - codigo da pesquisa cuja se deseja gravar os resultados
     * @throws IOException
     */
    public void gravaResultados(String codigoPesquisa) throws IOException {
        this.validador.validaNulleVazio(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
        if (!this.pesquisas.containsKey(codigoPesquisa)){
            this.validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        this.pesquisas.get(codigoPesquisa).gravaResultado();
    }


    /**
     * Define qual sera a estrategia utilizada para sugestao de proxima atividade. Poderao ser
     * usadas as estrategias MAIS_ANTIGA, MENOS_PENDENCIAS, MAIOR_RISCO e MAIOR_DURACAO.
     *
     * @param estrategia o nome da estrategia a ser utilizada
     */
    public void configuraEstrategia(String estrategia) {
        validador.validaNulleVazio(estrategia,"Estrategia nao pode ser nula ou vazia.");
        switch(estrategia){
            case("MAIS_ANTIGA"):
                this.estrategia = SugestaoProxAtividade.MAIS_ANTIGA;
                break;
            case("MENOS_PENDENCIAS"):
                this.estrategia = SugestaoProxAtividade.MENOS_PENDENCIAS;
                break;
            case("MAIOR_RISCO"):
                this.estrategia = SugestaoProxAtividade.MAIOR_RISCO;
                break;
            case("MAIOR_DURACAO"):
                this.estrategia = SugestaoProxAtividade.MAIOR_DURACAO;
                break;
            default:
                validador.lancaExcecao("Valor invalido da estrategia");
                break;
        }
    }

    /**
     * metodo responsavell por pegar a estrategia
     * @return o toString da estrategia
     */
    public String getEstrategia() {
        return estrategia.toString();
    }

    /**
     * Retorna o codigo da proxima Atividade a ser realizada na Pesquisa com base na estrategia
     * escolhida.
     *
     * @param codigoPesquisa a Pesquisa que tera sua Atividade sugerida
     * @return o codigo da proxima Atividade sugerida
     */
    public String proximaAtividade(String codigoPesquisa) {
        validador.validaNulleVazio(codigoPesquisa,"Pesquisa nao pode ser nula ou vazia.");
        if(!pesquisas.containsKey(codigoPesquisa)){
            validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if(!pesquisas.get(codigoPesquisa).isAtivada()){
            validador.lancaExcecao("Pesquisa desativada.");
        }
        if(!pesquisas.get(codigoPesquisa).hasPendencias()){
            validador.lancaExcecao("Pesquisa sem atividades com pendencias.");
        }

        List<Atividade> atividadesComPendencias= new ArrayList<Atividade>();
        for(Atividade atividade : pesquisas.get(codigoPesquisa).getAtividades()){
            if(atividade.contaItensPendentes()>0){
                atividadesComPendencias.add(atividade);
            }
        }

        switch(estrategia.toString()){
            case("MAIS_ANTIGA"):
                Collections.sort(atividadesComPendencias);
                break;
            case("MENOS_PENDENCIAS"):
                Collections.sort(atividadesComPendencias, new ComparadorAtividadePorPendencias());
                break;
            case("MAIOR_RISCO"):
                Collections.sort(atividadesComPendencias, new ComparadorAtividadePorRisco());
                break;
            case("MAIOR_DURACAO"):
                Collections.sort(atividadesComPendencias, new ComparadorAtividadePorDuracao());
                break;
        }

        return atividadesComPendencias.get(0).getCodigoIdentificador();
    }
}




