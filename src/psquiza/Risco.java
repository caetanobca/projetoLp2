package psquiza;

import util.Validacao;

public class Risco {

    private String descricao;
    private NivelRisco nivelRisco;
    private Validacao validador;


   ?

    private setNivelRisco(String nivelRisco){
        if(nivelRisco.trim().toUpperCase().equals("BAIXO")){
            this.nivelRisco = NivelRisco.BAIXO;
        } else if(nivelRisco.trim().toUpperCase().equals("MEDIO")){
            this.nivelRisco = NivelRisco.MEDIO;
        } else if(nivelRisco.trim().toUpperCase().equals("ALTO")){
            this.nivelRisco = NivelRisco.ALTO;
        } else {
            validador.lancaExcecao("Valor invalido do nivel do risco.");
        }

    }

    @Override
    public String toString() {
        return nivelRisco + " - " + descricao;
    }
}
