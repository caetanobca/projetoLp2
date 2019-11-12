package util;

public class Validacao {

    public Validacao() {

    }

    /**
     * Método que verifica o dado inserido, lançando a exceção se o dado for nulo ou
     * vazio.
     *
     * @param verifica       Parametro a ser verificado.
     * @param mensagemDeErro Mensagem de erro que será lançada junto com o tipo do erro.
     */
    public void validaNulleVazio(String verifica, String mensagemDeErro) {

        validaNull(verifica, mensagemDeErro);
        validaVazio(verifica, mensagemDeErro);

    }

    /**
     * Método que verifica o dado inserido, lançando a exceção se o dado for nulo.
     *
     * @param verifica       Parametro a ser verificado.
     * @param mensagemDeErro Mensagem de erro que será lançada junto com o tipo do erro
     */
    public void validaNull(String verifica, String mensagemDeErro) {

        if (verifica == null) {
            throw new NullPointerException(mensagemDeErro);

        }

    }

    /**
     * Método que verifica o dado inserido, lançando a exceção se o dado for vazio.
     *
     * @param verifica       Parametro a ser verificado.
     * @param mensagemDeErro Mensagem de erro que será lançada junto com o tipo do erro
     */
    public void validaVazio(String verifica, String mensagemDeErro) {

        if ("".equals(verifica.trim())) {
            throw new IllegalArgumentException(mensagemDeErro);
        }

    }

    /**
     * Método que verifica o tamanho do CPF, se for diferente de 11 digitos, lançara
     * exceção.
     *
     * @param verifica Parametro a ser verificado.
     * @param mensagem Mensagem de erro que será lançada junto com o tipo do erro
     */
    public void validaTamanhoCpf(String verifica, String mensagem) {
        if (verifica.length() != 11) {
            throw new IllegalArgumentException(mensagem);
        }

    }

    /**
     * Método que lança um erro se verificado algum erro durante a execução do
     * código do SAGA
     *
     * @param mensagem
     */
    public void lancaExcecao(String mensagem) {
        throw new IllegalArgumentException(mensagem);
    }

    public void validaInteiro(double preco, String mensagemDeErro) {
        if (preco <= 0) {
            throw new IllegalArgumentException(mensagemDeErro);

        }

    }

    public void validaData(String data,String mensagemDeErro) {
        if(data.length()!=10) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
        else if(!data.contains("/")) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
        String usar[] = data.split("/");
        if(Integer.parseInt(usar[0])>30) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
        if(Integer.parseInt(usar[1])>12) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
        if(usar[2].length()>4) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Método que verifica se o fator inserido de desconto para os combos é valido,
     * caso não seja, lança um erro.
     *
     * @param fator          fator a ser verificado.
     * @param mensagemDeErro mensagem de erro com o erro a ser lançado.
     */
    public void validaFator(double fator, String mensagemDeErro) {
        if (fator <= 0 || fator >= 1) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Método que verifica se o criterio de ordenação escolhido é válido, caso não
     * seja, lança um erro.
     *
     * @param criterio       criterio a ser verificado.
     * @param mensagemDeErro mensagem de erro com o erro a ser lançado.
     */
    public void validaCriterio(String criterio, String mensagemDeErro) {

        if (!criterio.equals("CLIENTE") && !criterio.equals("DATA") && !criterio.equals("FORNECEDOR")) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    public void validaTamanhoString(String verifica, int tamanhoMinimo, int tamanhoMaximo, String mensagemDeErro) {
        if (verifica.length() < tamanhoMinimo || verifica.length() > tamanhoMaximo) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Metodo que verifica a viabilidade de um problema ou objetivo.
     *
     * @param viabilidade    e a viabilidade do problema
     * @param mensagemDeErro e a mensagem de erro com o erro a ser lancado.
     */
    public void validaViabilidade(int viabilidade, String mensagemDeErro) {

        if ((viabilidade < 1) || (viabilidade > 5)) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Metodo que valida o nivel de risco para cadastrar uma atividade. Como existem apenas tres niveis possiveis,
     * "BAIXO","MEDIO","ALTO", o programa lançara um erro caso o nivel seja diferente desses.
     *
     * @param nivelRisco     String com o nivel do risco a ser verificado
     * @param mensagemDeErro Mensagem de erro que irá ser lancada juntamente com o erro
     */
    public void validaNivelRisco(String nivelRisco, String mensagemDeErro) {

        if (nivelRisco.trim().toUpperCase().equals("BAIXO") || nivelRisco.trim().toUpperCase().equals("MEDIO") || nivelRisco.trim().toUpperCase().equals("ALTO")) {

        } else {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Metodo que verifica a viabilidade de um problema ou objetivo.
     *
     * @param valor          e a viabilidade do problema
     * @param mensagemDeErro e a mensagem de erro com o erro a ser lancado.
     */
    public void validaViabilidadeOuAderencia(int valor, String mensagemDeErro) {


        if ((valor < 1) || (valor > 5)) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Verifica se o email esta no formato valido, ou seja, com pelo menos uma letra e/ou um numero
     * antes e depois do @. Caso contrario, uma excessao eh lancada.
     *
     * @param verifica       o email a ser verificado
     * @param mensagemDeErro a mensagem de erro a ser lancada caso ele ocorra
     */
    public void validaEmail(String verifica, String mensagemDeErro) {
        if (!verifica.contains("@")) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
        if (verifica.indexOf("@") == verifica.length() - 1) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
        if (verifica.split("@")[0].trim().isEmpty() || verifica.split("@")[1].trim().isEmpty()) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Verifica se a url da foto esta no formato valido, ou seja, inicializando com "http://" ou "https://",
     * seguido de um endereco;
     *
     * @param verifica       a URL da foto a ser verificada
     * @param mensagemDeErro a mensagem de erro a ser lancada caso ele ocorra
     */
    public void validaFoto(String verifica, String mensagemDeErro) {
        if (!verifica.contains("://")) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
        if (!(verifica.split("://")[0].equals("http") || verifica.split("://")[0].equals("https"))) {
            throw new IllegalArgumentException(mensagemDeErro);
        }

        if (verifica.split("://")[1].isEmpty()) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Verifica se o campo de interesse e valido, ou seja se contem no maximo 4 campos, se e menor que 255 caractres
     * e se cada um dos campos tem pelo menos 3 caracteres
     *
     * @param verifica       Os campos de interesse
     * @param mensagemDeErro A mensagem de erro a ser lancada caso ele ocorra.
     */
    public void validaCampoDeInteresse(String verifica, String mensagemDeErro) {
        this.validaTamanhoString(verifica, 3, 255, mensagemDeErro);

        String[] interesses = verifica.split(",");

        if (interesses.length > 4) {
            this.lancaExcecao(mensagemDeErro);
        } else {
            for (int i = 0; i < interesses.length; i++) {
                this.validaNulleVazio(interesses[i], mensagemDeErro);
                this.validaTamanhoString(interesses[i], 3, 255, mensagemDeErro);
            }
        }
    }

    /**
     * Verifica se o tipo do objetivo e Geral ou Especifico, caso seja uma String diferente dessas duas, lancara um
     * erro.
     *
     * @param verifica       String a ser verificada.
     * @param mensagemDeErro a mensagem de erro a ser lancada caso ele ocorra.
     */
    public void validaTipoObjetivo(String verifica, String mensagemDeErro) {

        if ((!verifica.equals("GERAL")) && (!verifica.equals("ESPECIFICO"))) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Metodo responsavel por verificar se o tipo de ordenacao inserido para a funcao de listar pesquisas e valido.
     * Caso seja diferente de: "PROBLEMA", "OBJETIVOS", "PESQUISA", lancara um erro.
     *
     * @param verifica String a ser verificada.
     * @param mensagemDeErro a mensagem de erro a ser lancada caso ele ocorra.
     */
    public void validaTipoOrdenacao(String verifica, String mensagemDeErro) {
        if (!verifica.equals("PROBLEMA") && !verifica.equals("OBJETIVOS") && !verifica.equals("PESQUISA")) {
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }
}