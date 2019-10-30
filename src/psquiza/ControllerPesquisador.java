package psquiza;

import util.Validacao;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de controle das acoes de(os) pesquisador(es), responsavel por construir, alterar, verificar e
 * remover objetos do tipo Pesquisador e suas herdeiras.
 */
public class ControllerPesquisador {

<<<<<<< HEAD
    /**
     * Mapa que relaciona o email dos pesquisadores com objetos Pesquisador. Cada pesquisador eh identificado
     * unicamente pelo seu email.
     */
    private Map<String, Pesquisador> pesquisadores;

    /**
     * Objeto responsavel por validar as entradas do usuario atraves dos seus metodos.
     */
    private Validacao validador;

    /**
     * Constroi um ControllerPesquisador de forma padrao, instanciando o mapa de Pesquisador e o validador.
     */
    public ControllerPesquisador() {
        this.pesquisadores = new HashMap<>();
        this.validador = new Validacao();
    }

    /**
     * Cadastra um novo pesquisador a partir do seu nome, funcao, biografia, email e fotoURL. O email deve ter
     * pelo menos uma letra/numero antes e depois do @ e a fotoURL deve comecar com "http://" ou "https://".
     * Todos os atributos sao verificados e caso qualquer um deles esteja invalido (nulo/vazio ou em formato
     * errado), uma excecao sera lancada.
     *
     * @param nome o nome do pesquisador
     * @param funcao a funcao do pesquisador (estudante, professor ou externo)
     * @param biografia a biografia do pesquisador
     * @param email o email do pesquisador
     * @param fotoURL a foto do pesquisador
     */
    public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL){
        validador.validaNulleVazio(nome, "Campo nome nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(funcao, "Campo funcao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(biografia, "Campo biografia nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(email, "Campo email nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
        validador.validaFoto(fotoURL,"Formato de foto invalido.");
        validador.validaEmail(email, "Formato de email invalido.");

        pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
    }

    /**
     * Altera um atributo de um pesquisador ja existente para um novo valor. Caso o atributo passado nao
     * exista ou o novoValor for nulo/vazio e/ou invalido, uma excecao sera lancada.
     *
     * @param email o email do pesquisador
     * @param atributo o nome do atributo a ser modificado
     * @param novoValor o novo valor do atributo a ser modificado
     */
    public void alteraPesquisador(String email, String atributo, String novoValor){

        if(!this.pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        if(!this.pesquisadores.get(email).isAtivo()){
            throw new IllegalArgumentException("Pesquisador inativo");
        }

        switch (atributo){
            case("nome"):
                this.validador.validaNulleVazio(novoValor,"Campo nome nao pode ser nulo ou vazio.");
                pesquisadores.get(email).setNome(novoValor);
                break;

            case("funcao"):
                this.validador.validaNulleVazio(novoValor,"Campo funcao nao pode ser nulo ou vazio.");
                pesquisadores.get(email).setFuncao(novoValor);
                break;

            case("biografia"):
                this.validador.validaNulleVazio(novoValor,"Campo biografia nao pode ser nulo ou vazio.");
                pesquisadores.get(email).setBiografia(novoValor);
                break;

            case("email"):
                this.validador.validaNulleVazio(novoValor,"Campo email nao pode ser nulo ou vazio.");
                validador.validaEmail(novoValor, "Formato de email invalido.");
                pesquisadores.get(email).setEmail(novoValor);

            case("fotoURL"):
                this.validador.validaNulleVazio(novoValor,"Campo fotoURL nao pode ser nulo ou vazio.");
                this.validador.validaFoto(novoValor,"Formato de foto invalido.");
                pesquisadores.get(email).setFotoURL(novoValor);
                break;

            default:
                throw new IllegalArgumentException("Atributo invalido.");
        }
    }

    /**
     * Torna o status de um pesquisador desativado como ativo. Soh eh possivel ativar pesquisadores
     * desativados e ja cadastrados. Caso contrario, uma excecao sera lancada.
     *
     * @param email o email do pesquisador a ser ativado
     */
    public void ativaPesquisador(String email){

        if(!this.pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        if(this.pesquisadores.get(email).isAtivo()){
            throw  new IllegalArgumentException("Pesquisador ja ativado.");
        }

        pesquisadores.get(email).setAtivo(true);
    }

    /**
     * Torna o status de um pesquisador ativado como desativado. Soh eh possivel desativar pesquisadores
     * ativos e ja cadastrados. Caso contrario, uma excecao sera lancada.
     *
     * @param email o email do pesquisador a ser desativado
     */
    public void desativaPesquisador(String email){
        if(!this.pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        if(!this.pesquisadores.get(email).isAtivo()){
            throw  new IllegalArgumentException("Pesquisador inativo.");
        }

        pesquisadores.get(email).setAtivo(false);
    }

    /**
     * Retorna uma representacao em String de um pesquisador, no formato "NOME (FUNCAO) - BIOGRAFIA - EMAIL - FOTOURL".
     * Caso o pesquisador procurado nao exista, uma excecao sera lancada.
     *
     * @param email o email do pesquisador
     * @return representacao em String do pesquisador
     */
    public String exibePesquisador(String email){

        if(!this.pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        return pesquisadores.get(email).toString();
    }

    /**
     * Retorna um booleano que representa o status do pesquisador (ativo ou desativado).
     *
     * @param email o email do pesquisador
     * @return a representacao booleana do status do pesquisador
     */
    public boolean pesquisadorEhAtivo(String email){

        if(!this.pesquisadores.containsKey(email)){
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        return pesquisadores.get(email).isAtivo();
    }

}
=======
    private String nome;
}
>>>>>>> 6c8e5a2f37986ebe90b97bb304c99f087878e672
