package psquiza.pesquisa;

import psquiza.pesquisador.Pesquisador;
import psquiza.atividade.Atividade;
import psquiza.objetivo.Objetivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;

/**
 * Entidade responsavel pela logica de criar e salvar os resumos e os resultados das pesquisas
 */
public class RelatorioPesquisa implements Serializable {



    /**
     * Metodo responsavel por gravar um resumo da pesquisa em um arquivo txt
     * @param descricaoPesquisa - descricao da pesquisa, que sera gravado o resumo
     * @param codigo - codigo da pesquisa
     * @param associacao - objeto que contem todas as entidades associadas em uma pesquisa
     * @throws IOException
     */
    public void gravaResumo(String descricaoPesquisa, String codigo, AssociacaoEmPesquisa associacao) throws IOException {
        File file = new File("_" + codigo + ".txt");
        String resumo = this.criaResumo(descricaoPesquisa, associacao);
        FileWriter fw = null;

        try {
            fw = new FileWriter(file, false);
            fw.write(resumo);

        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("Nao foi possivel fechar o resumo.");
                }
            }
        }
    }


    /**
     * MEtodo responsavel por criar um resumo da pesquisa
     * @param descricaoPesquisa - descricao da pesquisa, que sera gravado o resumo
     * @param associacao - objeto que contem todas as entidades associadas em uma pesquisa
     * @return uma string com o resumo da pesquisa
     */
    private String criaResumo(String descricaoPesquisa, AssociacaoEmPesquisa associacao) {
        String resumo = "- Pesquisa: " + descricaoPesquisa;

        if (associacao.getPesquisadores().size() > 0) {
            resumo += System.lineSeparator() + "    - Pesquisadores:";


            for (Pesquisador p : associacao.getPesquisadores()) {
                if (p.isEspecializado()) {
                    resumo += System.lineSeparator() + "        - " + p.exibeEspecializado();
                } else {
                    resumo += System.lineSeparator() + "        - " + p.toString();
                }
            }
        }
        if (associacao.getProblemaAssociado() != null) {
            resumo += System.lineSeparator() + "    - Problema:" + System.lineSeparator() + "        - "
                    + associacao.getProblemaAssociado().getId() + " - " + associacao.getProblemaAssociado().toString();
        }
        if (associacao.getObjetivosAssociados().size() > 0) {
            resumo += System.lineSeparator() + "     - Objetivos:";
            Collections.sort(associacao.getObjetivosAssociados());

            for (Objetivo o : associacao.getObjetivosAssociados()) {
                resumo += System.lineSeparator() + "        - " + o.getId() + " - " + o.toString();
            }

        }

        if (associacao.getAtividades().size() > 0) {
            resumo += System.lineSeparator() + "    - Atividades:";
            Collections.sort(associacao.getAtividades());

            for (Atividade a : associacao.getAtividades()) {
                String[] atividade = a.toString().replace("|", "_").split("_");

                resumo += System.lineSeparator() + "        - " + atividade[0];

                for (int i = 0; i < atividade.length - 1; i++) {
                    resumo += System.lineSeparator() + "            - " + a.getItens().get(i).getStatus()
                            + " - " + "ITEM" + a.getItens().get(i).getId();
                }
            }
        }
        return resumo;
    }

    /**
     * Metodo responsavel por gravar os resutados da pesquisa em um arquivo txt
     * @param descricaoPesquisa - descricao da pesquisa, que sera gravado o resultado
     * @param codigo - codigo da pesquisa
     * @param associacao - objeto que contem todas as entidades associadas em uma pesquisa
     * @throws IOException
     */
    public void gravaResultado(String descricaoPesquisa, String codigo, AssociacaoEmPesquisa associacao) throws IOException {
        File file = new File(codigo + "-Resultados.txt");
        String resultado = this.criaResultado(descricaoPesquisa, associacao);
        FileWriter fw = null;

        try {
            fw = new FileWriter(file, false);
            fw.write(resultado);

        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("Nao foi possivel fechar o resumo.");
                }
            }
        }
    }

    /**
     * Metodo responsavel por criar uma representacao textual dos resultados da pesquisa
     * @param descricaoPesquisa - descricao da pesquisa, que sera gravado o resumo
     * @param associacao - objeto que contem todas as entidades associadas em uma pesquisa
     * @return uma string com os resultados da pesquisa
     */
    private String criaResultado(String descricaoPesquisa, AssociacaoEmPesquisa associacao) {
        String resultado = "- Pesquisa: " + descricaoPesquisa;
        resultado += System.lineSeparator() + "    - Resultados:";

        if (associacao.getAtividades().size() > 0) {
            Collections.sort(associacao.getAtividades());


            for (Atividade a : associacao.getAtividades()) {
                resultado += System.lineSeparator() + "        - " +
                        a.getDescricao();

                for (int i = 0; i < a.getItens().size(); i++) {
                    if (a.getItens().get(i).getDuracao() != 0) {
                        resultado += System.lineSeparator() + "            - ITEM" + a.getItens().get(i).getId() + " - "
                                + a.getItens().get(i).getDuracao();
                    }
                }

                for (int i : a.getResultados().keySet()) {
                    resultado += System.lineSeparator() + "            - " + a.getResultados().get(i).toString();
                }
            }
        }
        return resultado;
    }

}
