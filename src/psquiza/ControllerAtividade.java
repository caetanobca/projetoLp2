package psquiza;

import util.Validacao;

import java.util.HashMap;
import java.util.Map;


public class ControllerAtividade {

    private Map<String, Atividade> atividades;
    private Validacao validador;
    private int qtdCadastrados;


    public ControllerAtividade() {
        this.atividades = new HashMap<>();
        this.validador = new Validacao();
        this.qtdCadastrados = 0;

    }

    public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {

        validador.validaNulleVazio(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
        validador.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");

        String chaveNova = geraCodigoIdentificador();

        Atividade atividadeNova = new Atividade(chaveNova, descricao, nivelRisco, descricaoRisco);

        atividades.put(chaveNova, atividadeNova);
        qtdCadastrados++;

        return chaveNova;
    }

    private String geraCodigoIdentificador() {
        int codigo = qtdCadastrados + 1;
        String codigoCompleto = "A" + Integer.toString(codigo);

        return codigoCompleto;

    }

    public void apagaAtividade(String codigoIdentificador) {
        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");



        if (!atividades.containsKey(codigoIdentificador)) {
            validador.lancaExcecao("Atividade nao encontrada");
        } else {

            atividades.remove(codigoIdentificador);
        }

    }

    public void cadastraItem(String codigoIdentificador, String item) {
        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(item, "Item nao pode ser nulo ou vazio.");

        if (!atividades.containsKey(codigoIdentificador)) {
            validador.lancaExcecao("Atividade nao encontrada");
        } else {

            atividades.get(codigoIdentificador).cadastraItem(item);
        }

    }

    public String exibeAtividade(String codigoIdentificador) {

        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");
        String msg = "";


        if (!atividades.containsKey(codigoIdentificador)) {

            validador.lancaExcecao("Atividade nao encontrada");
        } else {

            msg = atividades.get(codigoIdentificador).toString();
        }

        return msg;
    }

    public int contaItensRealizados(String codigoIdentificador){
        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");

        int itensRealizados = 0;

        if (!atividades.containsKey(codigoIdentificador)) {
            validador.lancaExcecao("Atividade nao encontrada");
        } else {
            itensRealizados = atividades.get(codigoIdentificador).contaItensRealizados();
        }

        return itensRealizados;
    }

    public int contaItensPendentes(String codigoIdentificador){
        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");

        int itensPendentes = 0;

        if (!atividades.containsKey(codigoIdentificador)) {
            validador.lancaExcecao("Atividade nao encontrada");
        } else {
            itensPendentes = atividades.get(codigoIdentificador).contaItensPendentes();
        }

        return itensPendentes;
    }
}

