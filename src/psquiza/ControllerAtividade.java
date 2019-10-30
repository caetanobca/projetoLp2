package psquiza;

import util.Validacao;

import java.util.HashMap;
import java.util.Map;

public class ControllerAtividade {

    private Map<String,Atividade> atividades;
    private Validacao validador;
    private int qtdAtividades;



    public ControllerAtividade(){
        this.atividades = new HashMap<>();
        this.validador = new Validacao();

    }


}

