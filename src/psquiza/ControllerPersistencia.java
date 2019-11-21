package psquiza;

import psquiza.atividade.ControllerAtividade;
import psquiza.objetivo.ControllerObjetivo;
import psquiza.pesquisa.ControllerPesquisa;
import psquiza.pesquisador.ControllerPesquisador;
import psquiza.problema.ControllerProblema;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ControllerPersistencia {


    private ControllerPesquisa controllerPesquisa;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;
    private ControllerAtividade controllerAtividade;
    private ControllerPesquisador controllerPesquisador;

    /**
     * Construtor de Controller Persistencia, recebendo controllers de outras entidades para salva-los em arquivo.
     *
     * @param controllerProblema    Objeto do tipo controllerProblema
     * @param controllerObjetivo    Objeto do tipo controllerObjetivo
     * @param controllerPesquisa    Objeto do tipo controllerPesquisa
     * @param controllerAtividade   Objeto do tipo controllerAtividade
     * @param controllerPesquisador Objeto do tipo controllerPesquisador
     */
    public ControllerPersistencia(ControllerProblema controllerProblema, ControllerObjetivo controllerObjetivo,
                                  ControllerPesquisa controllerPesquisa, ControllerAtividade controllerAtividade,
                                  ControllerPesquisador controllerPesquisador) {
        this.controllerObjetivo = controllerObjetivo;
        this.controllerPesquisa = controllerPesquisa;
        this.controllerProblema = controllerProblema;
        this.controllerAtividade = controllerAtividade;
        this.controllerPesquisador = controllerPesquisador;

    }

    /**
     * Método responsável por chamar todos os métodos que salvam os controllers em arquivos .txt.
     *
     * @throws IOException
     */
    public void salva() throws IOException {
        salvaAtividade();
        salvaPesquisa();
        salvaPesquisador();
        salvaProblema();
        salvaObjetivo();

    }

    /**
     * Método responsavel por salvar informações do controller de Atividade em arquivo .txt
     *
     * @throws IOException
     */
    private void salvaAtividade() throws IOException {
        FileOutputStream arquivoAtividade = new FileOutputStream("sistema_serializado/infoAtividade.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoAtividade);
        oos.writeObject(controllerAtividade);
        oos.close();
    }

    /**
     * Método responsavel por salvar informações do controller de Pesquisa em arquivo .txt
     *
     * @throws IOException
     */
    private void salvaPesquisa() throws IOException {
        FileOutputStream arquivoPesquisa = new FileOutputStream("sistema_serializado/infoPesquisa.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoPesquisa);
        oos.writeObject(controllerPesquisa);
        oos.close();
    }

    /**
     * Método responsavel por salvar informações do controller de Problema em arquivo .txt
     *
     * @throws IOException
     */
    private void salvaProblema() throws IOException {
        FileOutputStream arquivoProblema = new FileOutputStream("sistema_serializado/infoProblema.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoProblema);
        oos.writeObject(controllerProblema);
        oos.close();
    }

    /**
     * Método responsavel por salvar informações do controller de Pesquisador em arquivo .txt
     *
     * @throws IOException
     */
    private void salvaPesquisador() throws IOException {
        FileOutputStream arquivoPesquisador = new FileOutputStream("sistema_serializado/infoPesquisador.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoPesquisador);
        oos.writeObject(controllerPesquisador);
        oos.close();
    }

    /**
     * Método responsavel por salvar informações do controller de Objetivo em arquivo .txt
     *
     * @throws IOException
     */
    private void salvaObjetivo() throws IOException {
        FileOutputStream arquivoObjetivo = new FileOutputStream("sistema_serializado/infoObjetivo.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoObjetivo);
        oos.writeObject(controllerObjetivo);
        oos.close();
    }

    /**
     * Método responsável por ler o arquivo txt e retornar um objeto do tipo ContollerAtividade com as informações
     * contidas no arquivo, anteriormente salvas.
     *
     * @return Objeto do tipo ControllerAtividade
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ControllerAtividade carregaAtividade() throws IOException, ClassNotFoundException {
        FileInputStream arquivoAtividade = new FileInputStream("sistema_serializado/infoAtividade.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoAtividade);
        controllerAtividade = (ControllerAtividade) ois.readObject();
        ois.close();

        return controllerAtividade;
    }

    /**
     * Método responsável por ler o arquivo txt e retornar um objeto do tipo ContollerPesquisa com as informações
     * contidas no arquivo, anteriormente salvas.
     *
     * @return Objeto do tipo ControllerPesquisa
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ControllerPesquisa carregaPesquisa() throws IOException, ClassNotFoundException {
        FileInputStream arquivoPesquisa = new FileInputStream("sistema_serializado/infoPesquisa.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoPesquisa);
        controllerPesquisa = (ControllerPesquisa) ois.readObject();
        ois.close();

        return controllerPesquisa;
    }

    /**
     * Método responsável por ler o arquivo txt e retornar um objeto do tipo ContollerProblema com as informações
     * contidas no arquivo, anteriormente salvas.
     *
     * @return Objeto do tipo ControllerProblema
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ControllerProblema carregaProblema() throws IOException, ClassNotFoundException {
        FileInputStream arquivoProblema = new FileInputStream("sistema_serializado/infoProblema.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoProblema);
        controllerProblema = (ControllerProblema) ois.readObject();
        ois.close();

        return controllerProblema;
    }

    /**
     * Método responsável por ler o arquivo txt e retornar um objeto do tipo ContollerPesquisador com as informações
     * contidas no arquivo, anteriormente salvas.
     *
     * @return Objeto do tipo ControllerPesquisador
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ControllerPesquisador carregaPesquisador() throws IOException, ClassNotFoundException {
        FileInputStream arquivoPesquisador = new FileInputStream("sistema_serializado/infoPesquisador.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoPesquisador);
        controllerPesquisador = (ControllerPesquisador) ois.readObject();
        ois.close();

        return controllerPesquisador;
    }

    /**
     * Método responsável por ler o arquivo txt e retornar um objeto do tipo ContollerObjetivo com as informações
     * contidas no arquivo, anteriormente salvas.
     *
     * @return Objeto do tipo ControllerObjetivo
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ControllerObjetivo carregaObjetivo() throws IOException, ClassNotFoundException {
        FileInputStream arquivoObjetivo = new FileInputStream("sistema_serializado/infoObjetivo.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoObjetivo);
        controllerObjetivo = (ControllerObjetivo) ois.readObject();
        ois.close();

        return controllerObjetivo;
    }
}
