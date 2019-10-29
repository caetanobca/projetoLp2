import utilnosso.Validacao;

public class Risco {

    private String descricao;
    private String paraMitigar;
    private NivelRisco nivelRisco;
    private Validacao validador;


    public Risco(String descricao, String nivelRisco){
     this.descricao = descricao;
     this.validador = new Validacao();
     setNivelRisco(nivelRisco);

    }

    private setNivelRisco(String nivelRisco){
        if(nivelRisco.trim().toUpperCase.equals("BAIXO")){
            this.nivelRisco = NivelRisco.BAIXO;
        } elif(nivelRisco.trim().toUpperCase.equals("MEDIO")){
            this.nivelRisco = NivelRisco.MEDIO;
        } elif(nivelRisco.trim().toUpperCase.equals("ALTO")){
            this.nivelRisco = NivelRisco.ALTO;
        } else {
            validador.lancaExcecao("Valor invalido do nivel do risco.");
        }

    }



}
