package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

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
}