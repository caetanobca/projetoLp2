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

        if ("".equals(verifica)) {
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

    /**
     * Método que verifica se a data inserida é válida, caso não seja, lança um
     * erro.
     *
     * @param verifica       data a ser verificada.
     * @param mensagemDeErro mensagem de erro com o erro a ser lançado.
     */
    public void validaTamanhoData(String verifica, String mensagemDeErro) {
        String array[] = new String[3];

        array = verifica.split("/");

        if (Integer.parseInt(array[0]) < 1 || Integer.parseInt(array[1]) < 1 || Integer.parseInt(array[2]) < 1) {
            throw new IllegalArgumentException(mensagemDeErro);
        }

        if (Integer.parseInt(array[1]) > 12) {
            throw new IllegalArgumentException(mensagemDeErro);
        } else if (Integer.parseInt(array[1]) == 1 || Integer.parseInt(array[1]) == 3 || Integer.parseInt(array[1]) == 5
                || Integer.parseInt(array[1]) == 7 || Integer.parseInt(array[1]) == 8
                || Integer.parseInt(array[1]) == 10) {
            if (Integer.parseInt(array[0]) > 31) {
                throw new IllegalArgumentException(mensagemDeErro);
            }
        } else if (Integer.parseInt(array[1]) == 2) {
            if (Integer.parseInt(array[0]) > 28) {
                throw new IllegalArgumentException(mensagemDeErro);
            }

        } else {
            if (Integer.parseInt(array[0]) > 30) {
                throw new IllegalArgumentException(mensagemDeErro);
            }
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
     * @param nivelRisco String com o nivel do risco a ser verificado
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
     * @param valor e a viabilidade do problema
     * @param mensagemDeErro e a mensagem de erro com o erro a ser lancado.
     */
    public void validaViabilidadeOuAderencia(int valor,String mensagemDeErro) {


        if((valor<1) || (valor>5)){
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }
    /**
     * Verifica se o email esta no formato valido, ou seja, com pelo menos uma letra e/ou um numero
     * antes e depois do @. Caso contrario, uma excessao eh lancada.
     *
     * @param verifica o email a ser verificado
     * @param mensagemDeErro a mensagem de erro a ser lancada caso ele ocorra
     */
    public void validaEmail(String verifica, String mensagemDeErro){
        if(!verifica.contains("@")){
            throw new IllegalArgumentException(mensagemDeErro);
        }
        if(verifica.indexOf("@") == verifica.length()-1){
            throw new IllegalArgumentException(mensagemDeErro);
        }
        if(verifica.split("@")[0].trim().isEmpty() || verifica.split("@")[1].trim().isEmpty()){
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }

    /**
     * Verifica se a url da foto esta no formato valido, ou seja, inicializando com "http://" ou "https://",
     * seguido de um endereco;
     *
     * @param verifica a URL da foto a ser verificada
     * @param mensagemDeErro a mensagem de erro a ser lancada caso ele ocorra
     */
    public void validaFoto(String verifica, String mensagemDeErro){
        if(!verifica.contains("://")){
            throw new IllegalArgumentException(mensagemDeErro);
        }
        if(!(verifica.split("://")[0].equals("http") || verifica.split("://")[0].equals("https"))){
            throw new IllegalArgumentException(mensagemDeErro);
        }

        if(verifica.split("://")[1].isEmpty()){
            throw new IllegalArgumentException(mensagemDeErro);
        }
    }
}

