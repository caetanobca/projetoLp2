package psquiza;

import util.Validacao;

import java.util.*;

/**
 * Classe de controle das acoes de(os) pesquisador(es), responsavel por construir, alterar, verificar e
 * remover objetos do tipo Pesquisador e suas herdeiras.
 */
public class ControllerPesquisador {

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
     */
    public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
        validador.validaNulleVazio(nome, "Campo nome nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(funcao, "Campo funcao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(biografia, "Campo biografia nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(email, "Campo email nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
        validador.validaFoto(fotoURL, "Formato de foto invalido.");
        validador.validaEmail(email, "Formato de email invalido.");

        Pesquisador pesquisador = new Pesquisador(nome, funcao, biografia, email, fotoURL);
        this.pesquisadores.put(email, pesquisador);
    }

    /**
     * Altera um atributo de um pesquisador ja existente para um novo valor. Caso o atributo passado nao
     * exista ou o novoValor for nulo/vazio e/ou invalido, uma excecao sera lancada.
     *
     * @param email     o email do pesquisador
     * @param atributo  o nome do atributo a ser modificado
     * @param novoValor o novo valor do atributo a ser modificado
     */
    public void alteraPesquisador(String email, String atributo, String novoValor) {
        validador.validaNulleVazio(email, "Email nao pode ser vazio ou nulo.");


        if (!this.pesquisadores.containsKey(email)) {
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        if (!this.pesquisadores.get(email).isAtivo()) {
            throw new IllegalArgumentException("Pesquisador inativo");
        }

        validador.validaNulleVazio(atributo, "Atributo nao pode ser vazio ou nulo.");

        switch (atributo) {
            case ("NOME"):
                this.validador.validaNulleVazio(novoValor, "Campo nome nao pode ser nulo ou vazio.");
                pesquisadores.get(email).setNome(novoValor);
                break;

            case ("FUNCAO"):
                this.validador.validaNulleVazio(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
                pesquisadores.get(email).setFuncao(novoValor);
                break;

            case ("BIOGRAFIA"):
                this.validador.validaNulleVazio(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
                pesquisadores.get(email).setBiografia(novoValor);
                break;

            case ("EMAIL"):
                this.validador.validaNulleVazio(novoValor, "Campo email nao pode ser nulo ou vazio.");
                validador.validaEmail(novoValor, "Formato de email invalido.");
                pesquisadores.get(email).setEmail(novoValor);
                pesquisadores.put(novoValor, pesquisadores.get(email));
                pesquisadores.remove(email);
                break;

            case ("FOTO"):
                this.validador.validaNulleVazio(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
                this.validador.validaFoto(novoValor, "Formato de foto invalido.");
                pesquisadores.get(email).setFotoURL(novoValor);
                break;
            case ("SEMESTRE"):
                int valorUsarSemestre = Integer.parseInt(novoValor);
                if(valorUsarSemestre<=0) {
                    this.validador.lancaExcecao("Formato de semestre invalido.");
                }
                pesquisadores.get(email).alteraEspecialidade(atributo, valorUsarSemestre);
                break;
            case ("IEA") :

                double valorUsarIEA = Double.parseDouble(novoValor);
                if((valorUsarIEA<0) || (valorUsarIEA>10)) {
                    this.validador.lancaExcecao("Formato de IEA invalido.");
                }
                pesquisadores.get(email).alteraEspecialidade(atributo, valorUsarIEA);
                break;
            case ("FORMACAO") :
                this.validador.validaNulleVazio(novoValor,"Campo formacao nao pode ser nulo ou vazio.");
                pesquisadores.get(email).alteraEspecialidade(atributo, novoValor);
                break;
            case ("DATA") :
                this.validador.validaNulleVazio(novoValor,"Campo formacao nao pode ser nulo ou vazio.");
                pesquisadores.get(email).alteraEspecialidade(atributo, novoValor);
                break;
            case ("UNIDADE") :
                this.validador.validaNulleVazio(novoValor,"Campo formacao nao pode ser nulo ou vazio.");
                pesquisadores.get(email).alteraEspecialidade(atributo, novoValor);
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
    public void ativaPesquisador(String email) {
        validador.validaNulleVazio(email, "Email nao pode ser vazio ou nulo.");
        if (!this.pesquisadores.containsKey(email)) {
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        if (this.pesquisadores.get(email).isAtivo()) {
            throw new IllegalArgumentException("Pesquisador ja ativado.");
        }

        pesquisadores.get(email).ativa();
    }

    /**
     * Torna o status de um pesquisador ativado como desativado. Soh eh possivel desativar pesquisadores
     * ativos e ja cadastrados. Caso contrario, uma excecao sera lancada.
     *
     * @param email o email do pesquisador a ser desativado
     */
    public void desativaPesquisador(String email) {
        validador.validaNulleVazio(email, "Email nao pode ser vazio ou nulo.");

        if (!this.pesquisadores.containsKey(email)) {
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        if (!this.pesquisadores.get(email).isAtivo()) {
            throw new IllegalArgumentException("Pesquisador inativo.");
        }

        pesquisadores.get(email).desativa();
    }

    /**
     * Retorna uma representacao em String de um pesquisador, no formato "NOME (FUNCAO) - BIOGRAFIA - EMAIL - FOTOURL".
     * Caso o pesquisador procurado nao exista, uma excecao sera lancada.
     *
     * @param email o email do pesquisador
     * @return representacao em String do pesquisador
     */
    public String exibePesquisador(String email) {
        String retorno = "";
        validador.validaNulleVazio(email, "Campo email nao pode ser nulo ou vazio.");

        if (!this.pesquisadores.containsKey(email)) {
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        if(pesquisadores.get(email).isEspecializado()) {
            String tipo = pesquisadores.get(email).getFuncao();
            if(tipo.equals("professor")) {
                retorno+=pesquisadores.get(email).exibeEspecializado();
            }else{
                retorno+=pesquisadores.get(email).exibeEspecializado();
            }
        }else{
            retorno+=pesquisadores.get(email).toString();
        }

        return retorno;
    }

    /**
     * Retorna um booleano que representa o status do pesquisador (ativo ou desativado).
     *
     * @param email o email do pesquisador
     * @return a representacao booleana do status do pesquisador
     */
    public boolean pesquisadorEhAtivo(String email) {
        validador.validaNulleVazio(email, "Email nao pode ser vazio ou nulo.");

        if (!this.pesquisadores.containsKey(email)) {
            throw new IllegalArgumentException("Pesquisador nao encontrado");
        }

        return pesquisadores.get(email).isAtivo();
    }

    /**
     * Metodo responsavel por retornar os pesquisadores armazenados no sistema.
     * @return o HashMap que contem os pesquisadores do sistema
     */
    public Map<String, Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    /**
     * Metodo responsavel por associar uma pesquisa a determinado pesquisador, para identificacao de qual pesquisa e qual
     * pesquisador serao relacionados, o idPesquisa e o emailPesquisador sao utilizados. Uma excecao e lancada caso o usuario
     * queira fornecer algum valor nulo ou vazio para os parametros.
     * @param idPesquisa e o identificador unico da pesquisa
     * @param emailPesquisador e o email e identificador unico do pesquisador
     */
    public void associaPesquisador(String idPesquisa, String emailPesquisador) {
        validador.validaNulleVazio(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idPesquisa,"Campo idPesquisa nao pode ser nulo ou vazio.");
        Pesquisador pesquisador = pesquisadores.get(emailPesquisador);
        pesquisador.adicionaPesquisa(idPesquisa);
    }

    /**
     * Metodo responsavel por desassociar uma pesquisa de um determinado pesquisador, para identificacao de qual pesquisa e qual
     * pesquisador serao desassociados, o idPesquisa e o emailPesquisador sao utilizados. Uma excecao e lancada caso o usuario
     * queira fornecer algum valor nulo ou vazio para os parametros.
     * @param idPesquisa e o identificador unico da pesquisa
     * @param emailPesquisador e o email e identificador unico do pesquisador
     */
    public void desassociaPesquisador(String idPesquisa,String emailPesquisador) {
        validador.validaNulleVazio(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idPesquisa,"Campo idPesquisa nao pode ser nulo ou vazio.");
        Pesquisador pesquisador = pesquisadores.get(emailPesquisador);
        pesquisador.removePesquisa(idPesquisa);
    }

    /**
     * Metodo responsavel por cadastrar uma especialidade em um professor, o email do professor e utilizado
     * para se identificar qual professor tera os dados atribuidos.Uma excecao e lancada caso usuario tente
     * passar algum valor nulo ou vazio, ou um email inexistente no sistema.
     * @param email e o email e identificador unico do professor e pesquisador
     * @param formacao e a formacao a ser atribuida
     * @param unidade e a unidade a ser atribuida
     * @param data e a data a ser atribuida
     */
    public void cadastraEspecialidadeProfessor(String email,String formacao,String unidade,String data) {
        validador.validaNulleVazio(email,"Campo email nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(unidade,"Campo unidade nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(formacao,"Campo formacao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(data,"Campo data nao pode ser nulo ou vazio.");
        validador.validaData(data,"Atributo data com formato invalido.");

        if(!pesquisadores.containsKey(email)) {
            validador.lancaExcecao("Pesquisadora nao encontrada.");
        }
        if(!pesquisadores.get(email).getFuncao().equals("professor")) {
            validador.lancaExcecao("Pesquisador nao compativel com a especialidade.");
        }
        pesquisadores.get(email).especializaProfessor(formacao, unidade, data);
        pesquisadores.get(email).setEspecializado(true);
    }

    /**
     * Metodo responsavel por cadastrar uma especializade no aluno, o email e utilizado para
     * identificar qual aluno ira ter os dados atribuidos.Uma excecao e lancada caso usuario tente
     * informar ao algum valor nulo ou vazio ao sistema, ou um email inexistente no sistema.
     * @param email e o email e identificador unico do aluno e pesquisador
     * @param semestre e o semestre em que o aluno esta cursando
     * @param IEA e o indice de evasao do aluno
     */
    public void cadastraEspecialidadeAluno(String email,int semestre,double IEA) {
        validador.validaNulleVazio(email, "Campo email nao pode ser nulo ou vazio.");
        if(semestre <= 0) {
            validador.lancaExcecao("Atributo semestre com formato invalido.");
        }
        if((IEA > 10) || (IEA < 0)) {
            validador.lancaExcecao("Atributo IEA com formato invalido.");
        }
        if(!pesquisadores.containsKey(email)) {
            validador.lancaExcecao("Pesquisadora nao encontrada.");
        }
        if(!pesquisadores.get(email).getFuncao().equals("estudante")) {
            validador.lancaExcecao("Pesquisador nao compativel com a especialidade.");
        }

        pesquisadores.get(email).especializaEstudante(semestre, IEA);
        pesquisadores.get(email).setEspecializado(true);
    }

    /**
     * Metodo que e responsavel por listar os pesquisadores cadastrados no sistema pertencentes a um tipo
     * passado como parametro. Uma excecao e lancada caso usuario tente passar um tipo nulo,vazio, ou inexistente.
     * @param tipo e o tipo de pesquisador que o usuario deseja que seja listada
     * @return a listagem da respresentacao como objeto de todos os pesquisadores de determinado tipo
     */
    public String listaPesquisadores(String tipo) {
        validador.validaNulleVazio(tipo, "Campo tipo nao pode ser nulo ou vazio.");
        if ((!tipo.equals("EXTERNO")) && (!tipo.equals("PROFESSORA")) && (!tipo.equals("ALUNA"))) {
            validador.lancaExcecao("Tipo " + tipo + " inexistente.");
        }
        String retorno = "";
        List<String> pesquisadoresOrdenados = new ArrayList<String>();
        switch (tipo) {
            case "PROFESSORA":
                for (String chave : pesquisadores.keySet()) {
                    if (pesquisadores.get(chave).getFuncao().equals(tipo)) {
                        if (pesquisadores.get(chave).isEspecializado() == true) {
                            pesquisadoresOrdenados.add(pesquisadores.get(chave).exibeEspecializado());
                        } else {
                            pesquisadoresOrdenados.add(pesquisadores.get(chave).toString()+" | ");
                        }
                    }
                }
            case "ALUNA":
                for (String chave : pesquisadores.keySet()) {
                    if (pesquisadores.get(chave).getFuncao().equals(tipo)) {
                        if (pesquisadores.get(chave).isEspecializado() == true) {
                            pesquisadoresOrdenados.add(pesquisadores.get(chave).exibeEspecializado());
                        } else {
                            pesquisadoresOrdenados.add(pesquisadores.get(chave).toString()+" | ");
                        }
                    }
                }
            default:
                for (String chave : pesquisadores.keySet()) {
                    if ((pesquisadores.get(chave).getFuncao().equals("externo")) || (pesquisadores.get(chave).getFuncao().equals("EXTERNO"))) {
                        pesquisadoresOrdenados.add(pesquisadores.get(chave).toString()+" | ");
                    }
                }
        }
        for(int i=0;i<pesquisadoresOrdenados.size();i++) {
            retorno+=pesquisadoresOrdenados.get(i);
        }
        retorno = retorno.substring(0,retorno.length()-3);
        return retorno;
    }


}