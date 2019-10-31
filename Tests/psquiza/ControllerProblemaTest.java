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

        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.cadastraProblema("", 2);},"Campo descricao nao pode ser nulo ou vazio.");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.cadastraProblema("A facilidade em se trancar o curso no Controle Academico da UFCG", -1);}, "Valor invalido de viabilidade." );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.cadastraProblema("A dificuldade em se aprestar atencao na aula durante o primeiro horario da segunda-feira", 8000);},"Valor invalido de viabilidade.");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.cadastraProblema("A facilidade em se ir bem no curso de computacao quando se eh bom em programacao", 0);},"Valor invalido de viabilidade.");
    }

    @Test
    void apagarProblema() {
        controllerProblema.apagarProblema("P1");

        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.apagarProblema("P1");},"Problema nao encontrado");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.apagarProblema("");}, "Campo codigo nao pode ser nulo ou vazio." );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.apagarProblema("P3");},"Problema nao encontrado");

        controllerProblema.apagarProblema("P2");

        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.apagarProblema("P2");},"Problema nao encontrado");
    }

    @Test
    void exibeProblema() {
        assertEquals(controllerProblema.exibeProblema("P1"), "P1 - A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao - 3");
        assertEquals(controllerProblema.exibeProblema("P2"), "P2 - O problema do barulho incessante durante a hora do almoco no LCC-2 - 5");

        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.exibeProblema("P3");},"Problema nao encontrado");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProblema.exibeProblema("P0");},"Problema nao encontrado");
    }
}