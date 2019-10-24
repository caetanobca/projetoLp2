package utilnosso;

/**
 * Classe criada para Representar um validador de entradas
 *
 * @author Caetano Albuquerque - UFCG
 */
public class Validacao {

    /**
     * Metodo que verifica se uma string e nula ou vazia
     * @param testa - string que sera testada
     * @param msg - menssagem de erro que sera exibida
     */
    public void validaString (String testa, String msg){

        if (testa == null){
            throw new NullPointerException(msg);
        }else if (testa.trim().equals("")){
            throw new IllegalArgumentException(msg);
        }

    }

    /**
     * metodo que verifica se o tamanho de uma string e valido
     * @param testa - str que sera testada
     * @param tamanho - tamanho que a string deve ter
     * @param msg - menssagem de erro que sera exibida
     */
    public void validaTamanho(String testa, int tamanho, String msg) {
        if (!(testa.length() == tamanho)){
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Metodo que verifica se um valor esta dentro de um intervalo aceitavel
     * @param testa -valor que sera testado
     * @param minimo - menor valo do intervalo aceitavel
     * @param maximo - maior valor do intervalo aceitavel
     * @param msg - menssagem de erro que sera exibida
     */
    public void validaDouble(double testa, double minimo, double maximo, String msg){
        if(testa < minimo){
            throw new IllegalArgumentException(msg);
        }else if(testa > maximo){
            throw new IllegalArgumentException(msg);
        }
    }

}