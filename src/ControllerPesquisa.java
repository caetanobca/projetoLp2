import utilnosso.Validacao;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisa {

    private Map<String, Pesquisa> pesquisas;
    private Validacao validador;

    public ControllerPesquisa(){
        this.pesquisas = new HashMap<String, Pesquisa>();
        this.validador = new Validacao();
    }

    public String cadastraPesquisa(String descricao, String campoDeInteresse){
        this.validador.validaNulleVazio(descricao,  "Descricao nao pode ser nula ou vazia.");
        this.validador.validaNulleVazio(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validador.validaTamanhoString(campoDeInteresse, 3, 255,
                "Formato do campo de interesse invalido." );

        String[] interesses = campoDeInteresse.split(",");

        if (interesses.length > 4){
            throw new IllegalArgumentException("Formato do campo de interesse invalido.");
        }else {
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

    private String geraCodigoDePesquisa (String codigo, int i){
        if (this.pesquisas.containsKey(codigo + i)){
            i++;
            this.geraCodigoDePesquisa(codigo, i);
        }
        return codigo + i;
    }

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");
//        this.validador.validaNulleVazio(conteudoASerAlterado, "Conteudo a ser alterado nao pode ser nulo ou vazio.");
//        this.validador.validaNulleVazio(novoConteudo, "Novo conteudo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }

        if (!this.pesquisas.get(codigo).isAtivada()){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        if (!(conteudoASerAlterado.equals("DESCRICAO") || conteudoASerAlterado.equals("CAMPO"))) {
            throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
        }

        if (conteudoASerAlterado.equals("DESCRICAO")){
            this.validador.validaNulleVazio(novoConteudo,  "Descricao nao pode ser nula ou vazia.");

            this.pesquisas.get(codigo).setDescricao(novoConteudo);
        }else if (conteudoASerAlterado.equals("CAMPO")){

            this.validador.validaNulleVazio(novoConteudo, "Formato do campo de interesse invalido.");
            this.validador.validaTamanhoString(novoConteudo, 3, 255,

                    "Formato do campo de interesse invalido." );
            this.pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
        }

    }

    public void encerraPesquisa(String codigo, String motivo){
                this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");
        //        this.validador.validaNulleVazio(motivo, "Motivo nao pode ser nulo ou vazio.");


        if (!this.pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        if (!this.pesquisas.get(codigo).isAtivada()){
            throw new IllegalArgumentException("Pesquisa desativada.");
        }
        this.pesquisas.get(codigo).desativa(motivo);
    }

    public void ativaPesquisa(String codigo){
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }
        if (this.pesquisas.get(codigo).isAtivada()){
            throw new IllegalArgumentException("Pesquisa ja ativada.");
        }
        this.pesquisas.get(codigo).ativa();
    }

    public String exibePesquisa(String codigo){
        //        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }

        return this.pesquisas.get(codigo).toString();
    }

    public boolean pesquisaEhAtiva(String codigo){
        this.validador.validaNulleVazio(codigo, "Codigo nao pode ser nulo ou vazio.");

        if (!this.pesquisas.containsKey(codigo)){
            throw new IllegalArgumentException("Pesquisa nao encontrada.");
        }

        return this.pesquisas.get(codigo).isAtivada();
    }
}
