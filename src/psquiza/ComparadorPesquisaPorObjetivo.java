package psquiza;

import java.util.Comparator;

public class ComparadorPesquisaPorObjetivo implements Comparator<Pesquisa> {

    @Override
    public int compare(Pesquisa p1, Pesquisa p2) {
        int retorno = 0;
        int qtdP1 = p1.getObjetivosAssociados().size();
        int qtdP2 = p2.getObjetivosAssociados().size();

        if(qtdP1>qtdP2){
            retorno = -1;
        } else if(qtdP1<qtdP2){
            retorno = 1;
        } else{
           for(int i = 0; i< p1.getObjetivosAssociados().size();i++){
              if(p1.getObjetivosAssociados().get(i).getId().compareTo(p2.getObjetivosAssociados().get(i).getId()) != 0){
                  retorno = p1.getObjetivosAssociados().get(i).getId().compareTo(p2.getObjetivosAssociados().get(i).getId());
                  break;

              }

            }
        }

        return retorno;
    }
}
