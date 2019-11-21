package psquiza;

import java.io.IOException;

public class Facade {
    private ControllerPesquisa controllerPesquisa;
    private ControllerProblema controllerProblema;
    private ControllerObjetivo controllerObjetivo;
    private ControllerAtividade controllerAtividade;
    private ControllerPesquisador controllerPesquisador;
    private ControllerBusca controllerBusca;
    private ControllerPersistencia controllerPersistencia;


    public Facade() {
        this.controllerProblema = new ControllerProblema();
        this.controllerObjetivo = new ControllerObjetivo();
        this.controllerAtividade = new ControllerAtividade();
        this.controllerPesquisador = new ControllerPesquisador();
        this.controllerPesquisa = new ControllerPesquisa(this.controllerObjetivo.getObjetivos(),
                this.controllerProblema.getProblemas(),
                this.controllerAtividade.getAtividades(), this.controllerPesquisador.getPesquisadores());
        this.controllerBusca = new ControllerBusca(controllerProblema, controllerObjetivo, controllerPesquisa,
                controllerAtividade, controllerPesquisador);
        this.controllerPersistencia = new ControllerPersistencia(controllerProblema, controllerObjetivo,
                controllerPesquisa, controllerAtividade, controllerPesquisador);
    }

    public String cadastraPesquisa(String descricao, String campoDeInteresse) {
        return this.controllerPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
    }

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
        this.controllerPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
    }

    public void encerraPesquisa(String codigo, String motivo) {
        this.controllerPesquisa.encerraPesquisa(codigo, motivo);
    }

    public void ativaPesquisa(String codigo) {
        this.controllerPesquisa.ativaPesquisa(codigo);
    }

    public String exibePesquisa(String codigo) {
        return this.controllerPesquisa.exibePesquisa(codigo);
    }

    public boolean pesquisaEhAtiva(String codigo) {
        return this.controllerPesquisa.pesquisaEhAtiva(codigo);
    }

    public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
        this.controllerPesquisador.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
    }

    public void alteraPesquisador(String email, String atributo, String novoValor) {
        this.controllerPesquisador.alteraPesquisador(email, atributo, novoValor);
    }

    public void ativaPesquisador(String email) {
        this.controllerPesquisador.ativaPesquisador(email);
    }

    public void desativaPesquisador(String email) {
        this.controllerPesquisador.desativaPesquisador(email);
    }

    public String exibePesquisador(String email) {
        return this.controllerPesquisador.exibePesquisador(email);
    }

    public boolean pesquisadorEhAtivo(String email) {
        return this.controllerPesquisador.pesquisadorEhAtivo(email);
    }

    public String cadastraProblema(String descricao, int viabilidade) {
        return this.controllerProblema.cadastraProblema(descricao, viabilidade);
    }

    public void apagarProblema(String codigo) {
        this.controllerProblema.apagarProblema(codigo);
    }

    public String exibeProblema(String codigo) {
        return this.controllerProblema.exibeProblema(codigo);
    }

    public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
        return this.controllerObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
    }

    public void apagarObjetivo(String codigo) {
        this.controllerObjetivo.apagarObjetivo(codigo);
    }

    public String exibeObjetivo(String codigo) {
        return this.controllerObjetivo.exibeObjetivo(codigo);
    }

    public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
        return this.controllerAtividade.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
    }

    public void apagaAtividade(String codigo) {
        this.controllerAtividade.apagaAtividade(codigo);
    }

    public void cadastraItem(String codigo, String item) {
        this.controllerAtividade.cadastraItem(codigo, item);
    }

    public String exibeAtividade(String codigo) {
        return this.controllerAtividade.exibeAtividade(codigo);
    }

    public int contaItensPendentes(String codigo) {
        return this.controllerAtividade.contaItensPendentes(codigo);
    }

    public int contaItensRealizados(String codigo) {
        return this.controllerAtividade.contaItensRealizados(codigo);
    }

    public boolean associaProblema(String idPesquisa, String idProblema) {
        return this.controllerPesquisa.associaProblema(idPesquisa, idProblema);
    }

    public boolean desassociaProblema(String idPesquisa) {
        return this.controllerPesquisa.desassociaProblema(idPesquisa);
    }

    public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
        return this.controllerPesquisa.associaObjetivo(idPesquisa, idObjetivo);
    }

    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
        return this.controllerPesquisa.desassociaObjetivo(idPesquisa, idObjetivo);

    }

    public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
        return this.controllerPesquisa.associaPesquisador(idPesquisa, emailPesquisador);
    }

    public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
        return this.controllerPesquisa.desassociaPesquisador(idPesquisa, emailPesquisador);
    }

    public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
        return this.controllerPesquisa.associaAtividade(codigoPesquisa, codigoAtividade);
    }

    public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
        return this.controllerPesquisa.desassociaAtividade(codigoPesquisa, codigoAtividade);
    }

    public String busca(String termo) {
        return this.controllerBusca.busca(termo);
    }

    public String busca(String termo, int numeroDoResultado) {
        return this.controllerBusca.busca(termo, numeroDoResultado);
    }

    public int contaResultadosBusca(String termo) {
        return this.controllerBusca.contaResultadosBusca(termo);
    }

    public String listaPesquisas(String ordem) {
        return this.controllerPesquisa.listaPesquisas(ordem);
    }

    public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
        this.controllerPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
    }

    public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
        this.controllerPesquisador.cadastraEspecialidadeAluno(email, semestre, IEA);
    }

    public void executaAtividade(String codigoAtividade, int item, int duracao) {
        this.controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
    }

    public int cadastraResultado(String codigoAtividade, String resultado) {
        return this.controllerAtividade.cadastraResultado(codigoAtividade, resultado);
    }

    public boolean removeResultado(String codigoAtividade, int numeroResultado) {
        return this.controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
    }

    public String listaResultados(String codigoAtividade) {
        return this.controllerAtividade.listaResultados(codigoAtividade);
    }

    public int getDuracao(String codigoAtividade) {
        return this.controllerAtividade.getDuracao(codigoAtividade);
    }

    public String listaPesquisadores(String tipo) {
        return this.controllerPesquisador.listaPesquisadores(tipo);
    }


    public void salvar() throws IOException {
        controllerPersistencia.salva();
    }

    public void carregar() throws IOException, ClassNotFoundException {
        this.controllerPesquisa = controllerPersistencia.carregaPesquisa();
        this.controllerPesquisador = controllerPersistencia.carregaPesquisador();
        this.controllerObjetivo = controllerPersistencia.carregaObjetivo();
        this.controllerProblema = controllerPersistencia.carregaProblema();
        this.controllerAtividade = controllerPersistencia.carregaAtividade();
        this.controllerBusca = new ControllerBusca(controllerProblema, controllerObjetivo, controllerPesquisa,
                controllerAtividade, controllerPesquisador);
        this.controllerPersistencia = new ControllerPersistencia(controllerProblema, controllerObjetivo,
                controllerPesquisa, controllerAtividade, controllerPesquisador);

        this.controllerPesquisa.carregaMapas(controllerObjetivo.getObjetivos(), controllerProblema.getProblemas(),
                controllerAtividade.getAtividades(), controllerPesquisador.getPesquisadores());

    }

    public void gravarResumo(String codigoPesquisa) throws IOException {
        this.controllerPesquisa.gravaResumo(codigoPesquisa);
    }

    public void gravarResultados(String codigoPesquisa) throws IOException {
        this.controllerPesquisa.gravaResultados(codigoPesquisa);
    }

    public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
        this.controllerAtividade.defineProximaAtividade(idPrecedente, idSubsquente);
    }

    public int contaProximos(String idPrecedente) {
        return this.controllerAtividade.contaProximos(idPrecedente);
    }

    public void tiraProximaAtividade(String idPrecedente) {
        this.controllerAtividade.tiraProximaAtividade(idPrecedente);
    }

    public String pegaProximo(String idAtividade, int enesimaAtividade) {
        return this.controllerAtividade.pegaProximo(idAtividade, enesimaAtividade);
    }

    public String pegaMaiorRiscoAtividades(String idAtividade) {
        return this.controllerAtividade.pegaMaiorRiscoAtividades(idAtividade);
    }

    public void configuraEstrategia(String estrategia) {
        this.controllerPesquisa.configuraEstrategia(estrategia);
    }

    public String proximaAtividade(String codigoPesquisa) {
        return this.controllerPesquisa.proximaAtividade(codigoPesquisa);
    }
}