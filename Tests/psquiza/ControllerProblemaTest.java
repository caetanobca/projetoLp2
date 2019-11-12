package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerProblemaTest {

    private ControllerProblema controllerProblema;

    @BeforeEach
    void setUp() {
        controllerProblema = new ControllerProblema();
        controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);
        controllerProblema.cadastraProblema("O problema do barulho incessante durante a hora do almoco no LCC-2", 5);
    }

    @Test
    void cadastraProblema() {
        controllerProblema.cadastraProblema("As falta de confianca nos governos pelo jovens em uma America Latina em chamas", 2);
        controllerProblema.cadastraProblema("A problematica da falta de espelhos nos banheiros da UFCG",5);
    }

    @Test
    void cadastraProblemaDescricaoVaziaOuNula(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.cadastraProblema("", 2);});
        assertThrows(NullPointerException.class, () -> {
            controllerProblema.cadastraProblema(null,4);});
    }

    @Test
    void cadastraProblemaViabilidadeInvalida(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.cadastraProblema("A facilidade em se trancar o curso no Controle Academico da UFCG", -1);});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.cadastraProblema("A dificuldade em se aprestar atencao na aula durante o primeiro horario da segunda-feira", 8000);});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.cadastraProblema("A facilidade em se ir bem no curso de computacao quando se eh bom em programacao", 0);});

    }

    @Test
    void apagarProblemaExistente() {
        controllerProblema.apagarProblema("P1");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.apagarProblema("P1");});
    }

    @Test
    void apagarProblemaNaoExistente(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.apagarProblema("");} );
        assertThrows(NullPointerException.class, () -> {
            controllerProblema.apagarProblema(null);} );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.apagarProblema("P3");});
    }

    @Test
    void exibeProblemaExistente() {
        assertEquals(controllerProblema.exibeProblema("P1"), "P1 - A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao - 3");
        assertEquals(controllerProblema.exibeProblema("P2"), "P2 - O problema do barulho incessante durante a hora do almoco no LCC-2 - 5");

    }

    @Test
    void exibeProblemaNaoExistente(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.exibeProblema("P3");});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.exibeProblema("P0");});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.exibeProblema("");});
        assertThrows(NullPointerException.class, () -> {
            controllerProblema.exibeProblema(null);});
    }

    @Test
    void buscaTest(){
        controllerProblema.cadastraProblema("A problematica da falta de espelhos nos banheiros da UFCG",5);

        List<String> resultado = new ArrayList<>();

        resultado.add("P3: A problematica da falta de espelhos nos banheiros da UFCG");
        resultado.add("P2: O problema do barulho incessante durante a hora do almoco no LCC-2");
        resultado.add("P1: A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao");

        assertEquals(resultado,controllerProblema.busca("a"));

        List<String> resultado2 = new ArrayList<>();

        resultado2.add("P1: A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao");

        assertEquals(resultado2, controllerProblema.busca("testes"));


        List<String> resultado3 = new ArrayList<>();

        resultado3.add("P2: O problema do barulho incessante durante a hora do almoco no LCC-2");

        assertEquals(resultado3, controllerProblema.busca("barulho"));

        List<String> resultado4 = new ArrayList<>();

        resultado4.add("P3: A problematica da falta de espelhos nos banheiros da UFCG");

        assertEquals(resultado4, controllerProblema.busca("UFCG"));

    }

    @Test
    void buscaTestTermoVaziouOuNull(){
        assertThrows(IllegalArgumentException.class, ()-> controllerProblema.busca(""));
        assertThrows(NullPointerException.class, ()-> controllerProblema.busca(null));
    }
}