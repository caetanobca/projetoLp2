package psquiza;

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


    public ControllerPersistencia(ControllerProblema controllerProblema, ControllerObjetivo controllerObjetivo,
                           ControllerPesquisa controllerPesquisa, ControllerAtividade controllerAtividade,
                           ControllerPesquisador controllerPesquisador) {
        this.controllerObjetivo = controllerObjetivo;
        this.controllerPesquisa = controllerPesquisa;
        this.controllerProblema = controllerProblema;
        this.controllerAtividade = controllerAtividade;
        this.controllerPesquisador = controllerPesquisador;

    }

    public void salva() throws IOException {
        salvaAtividade();
        salvaPesquisa();
        salvaPesquisador();
        salvaProblema();
        salvaObjetivo();

    }

    private void salvaAtividade() throws IOException{
        FileOutputStream arquivoAtividade = new FileOutputStream("sistema_serializado/infoAtividade.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoAtividade);
        oos.writeObject(controllerAtividade);
        oos.close();
    }

    private void salvaPesquisa() throws IOException{
        FileOutputStream arquivoPesquisa = new FileOutputStream("sistema_serializado/infoPesquisa.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoPesquisa);
        oos.writeObject(controllerPesquisa);
        oos.close();
    }

    private void salvaProblema() throws IOException{
        FileOutputStream arquivoProblema = new FileOutputStream("sistema_serializado/infoProblema.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoProblema);
        oos.writeObject(controllerProblema);
        oos.close();
    }

    private void salvaPesquisador() throws IOException{
        FileOutputStream arquivoPesquisador = new FileOutputStream("sistema_serializado/infoPesquisador.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoPesquisador);
        oos.writeObject(controllerPesquisador);
        oos.close();
    }

    private void salvaObjetivo() throws IOException{
        FileOutputStream arquivoObjetivo= new FileOutputStream("sistema_serializado/infoObjetivo.txt");
        ObjectOutputStream oos = new ObjectOutputStream(arquivoObjetivo);
        oos.writeObject(controllerObjetivo);
        oos.close();
    }

    public ControllerAtividade carregaAtividade() throws IOException, ClassNotFoundException{
        FileInputStream arquivoAtividade = new FileInputStream("sistema_serializado/infoAtividade.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoAtividade);
        controllerAtividade = (ControllerAtividade) ois.readObject();
        ois.close();

        return controllerAtividade;
    }

    public ControllerPesquisa carregaPesquisa() throws IOException, ClassNotFoundException{
        FileInputStream arquivoPesquisa = new FileInputStream("sistema_serializado/infoPesquisa.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoPesquisa);
        controllerPesquisa = (ControllerPesquisa) ois.readObject();
        ois.close();

        return controllerPesquisa;
    }

    public ControllerProblema carregaProblema() throws IOException, ClassNotFoundException{
        FileInputStream arquivoProblema = new FileInputStream("sistema_serializado/infoProblema.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoProblema);
        controllerProblema = (ControllerProblema) ois.readObject();
        ois.close();

        return controllerProblema;
    }

    public ControllerPesquisador carregaPesquisador() throws IOException, ClassNotFoundException{
        FileInputStream arquivoPesquisador = new FileInputStream("sistema_serializado/infoPesquisador.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoPesquisador);
        controllerPesquisador = (ControllerPesquisador) ois.readObject();
        ois.close();

        return controllerPesquisador;
    }

    public ControllerObjetivo carregaObjetivo() throws IOException, ClassNotFoundException{
        FileInputStream arquivoObjetivo = new FileInputStream("sistema_serializado/infoObjetivo.txt");
        ObjectInputStream ois = new ObjectInputStream(arquivoObjetivo);
        controllerObjetivo = (ControllerObjetivo) ois.readObject();
        ois.close();

        return controllerObjetivo;
    }
}
