package psquiza;

import static org.junit.jupiter.api.Assertions.*;

class ControllerObjetivoTest {

    private ControllerObjetivo controllerObjetivo;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        controllerObjetivo = new ControllerObjetivo();
        controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5);
    }

    @org.junit.jupiter.api.Test
    void cadastraObjetivo() {
        controllerObjetivo.cadastraObjetivo("GERAL", "Fazer os alunos ficarem mais calmos durante o almoco no LCC-2",2,4);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Realizar praticas de Yoga durante o almoco no LCC-2",3,1);

        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("","Fazer todos gostarem mais de testar programas.", 2,4 );},"Campo tipo nao pode ser nulo ou vazio.");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL","", 1,1 );},"Campo descricao nao pode ser nulo ou vazio.");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("MALIGNO","Descricao teste", 2,2 );},"Valor invalido de tipo.");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("123","Descricao teste", 2,2 );},"Valor invalido de tipo.");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("42","", 3,5 );},"Valor invalido de tipo.");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", 0,4 );},"Valor invalido de aderencia");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", 6,4 );},"Valor invalido de aderencia");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", -1,4 );},"Valor invalido de aderencia");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL","Dificultar o tracamento de curso no Controle Academico da UFCG", 2,0 );},"Valor invalido de viabilidade");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL","Dificultar o tracamento de curso no Controle Academico da UFCG", 2,6 );},"Valor invalido de viabilidade");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL","Dificultar o tracamento de curso no Controle Academico da UFCG", 2,-10 );},"Valor invalido de viabilidade");
    }

    @org.junit.jupiter.api.Test
    void apagarObjetivo() {
        controllerObjetivo.apagarObjetivo("O1");

        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("");},"Campo codigo nao pode ser nulo ou vazio." );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O1");},"Objetivo nao encontrado" );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O3");},"Objetivo nao encontrado" );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O4");},"Objetivo nao encontrado" );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("OA");},"Objetivo nao encontrado" );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O-2");},"Objetivo nao encontrado" );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O55");},"Objetivo nao encontrado" );

        controllerObjetivo.apagarObjetivo("O2");

        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O2");},"Objetivo nao encontrado" );
    }

    @org.junit.jupiter.api.Test
    void exibeObjetivo() {
        assertEquals(controllerObjetivo.exibeObjetivo("O1"), "O1 - GERAL - Aumentar o interesse dos alunos em realizar testes nas aulas de programacao - 10");
        assertEquals(controllerObjetivo.exibeObjetivo("O2"), "O2 - ESPECIFICO - Criar bonfiicacao aos melhores testadores de cada turma - 9");

        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.exibeObjetivo("O0");});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.exibeObjetivo("O3");});
    }
}