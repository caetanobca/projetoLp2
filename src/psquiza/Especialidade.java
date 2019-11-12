package psquiza;

/**
 * Interface necessaria no sistema para utilizarmos uma composicao quando
 * for necessario a criacao de um estudante ou professor.
 */
public interface Especialidade {
    /**
     * Assinatura do metodo de edicao, que e utilizado para editar os valores
     * das classes que implementarem a interface.
     * @param atributo e o atributo a ser editado
     * @param valor e o novo valor
     */
    void edita(String atributo, String valor);
}
