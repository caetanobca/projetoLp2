package psquiza;

public class Facade {
    private ControllerPesquisa controllerPesquisa;
    private ControllerAtividade controllerAtividade;

    public Facade(){
        this.controllerPesquisa = new ControllerPesquisa();
        this.controllerAtividade = new ControllerAtividade();
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

    public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco){
        return this.controllerAtividade.cadastraAtividade(descricao, nivelRisco,descricaoRisco);
    }

    public void apagaAtividade(String codigo){
        this.controllerAtividade.apagaAtividade(codigo);
    }

    public void cadastraItem(String codigo, String item){
        this.controllerAtividade.cadastraItem(codigo,item);
    }

    public String exibeAtividade(String codigo){
        return this.controllerAtividade.exibeAtividade(codigo);
    }

    public int contaItensPendentes(String codigo){
        return this.controllerAtividade.contaItensPendentes(codigo);
    }

    public int contaItensRealizados(String codigo){
        return this.controllerAtividade.contaItensRealizados(codigo);
    }
}
