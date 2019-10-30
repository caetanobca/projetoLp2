package psquiza;

public class Facade {
    private ControllerPesquisa controllerPesquisa;
    private ControllerProblema controllerProblema;
    private ControllerObjetivo controllerObjetivo;

    public Facade(){
        this.controllerPesquisa = new ControllerPesquisa();
        this.controllerProblema = new ControllerProblema();
        this.controllerObjetivo = new ControllerObjetivo();
    }

    public String cadastraPesquisa(String descricao, String campoDeInteresse){
        return this.controllerPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
    }

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        this.controllerPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
    }

    public void encerraPesquisa(String codigo, String motivo){
        this.controllerPesquisa.encerraPesquisa(codigo, motivo);
    }

    public void ativaPesquisa(String codigo){
        this.controllerPesquisa.ativaPesquisa(codigo);
    }

    public String exibePesquisa(String codigo){
        return this.controllerPesquisa.exibePesquisa(codigo);
    }

    public boolean pesquisaEhAtiva(String codigo){
        return this.controllerPesquisa.pesquisaEhAtiva(codigo);
    }

    public String cadastraProblema(String descricao, int viabilidade) {
        return this.controllerProblema.cadastraProblema(descricao,viabilidade);
    }

    public void apagarProblema(String codigo) {
        this.controllerProblema.apagarProblema(codigo);
    }

    public String exibeProblema(String codigo) {
        return this.controllerProblema.exibeProblema(codigo);
    }

    public String cadastraObjetivo(String tipo,String descricao,int aderencia,int viabilidade) {
        return this.controllerObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
    }

    public void apagarObjetivo(String codigo) {
        this.controllerObjetivo.apagarObjetivo(codigo);
    }

    public String exibeObjetivo(String codigo) {
        return this.controllerObjetivo.exibeObjetivo(codigo);
    }
}
