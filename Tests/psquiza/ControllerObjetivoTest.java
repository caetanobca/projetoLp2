package psquiza;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerObjetivoTest {

    private ControllerObjetivo controllerObjetivo;

    @BeforeEach
    void setUp() {
        controllerObjetivo = new ControllerObjetivo();
        controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5);
    }

    @Test
    void cadastraObjetivo() {
        controllerObjetivo.cadastraObjetivo("GERAL", "Fazer os alunos ficarem mais calmos durante o almoco no LCC-2",2,4);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Realizar praticas de Yoga durante o almoco no LCC-2",3,1);

        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL","Dificultar o tracamento de curso no Controle Academico da UFCG", 2,0 );});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL","Dificultar o tracamento de curso no Controle Academico da UFCG", 2,6 );});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL","Dificultar o tracamento de curso no Controle Academico da UFCG", 2,-10 );});
    }

    @Test
    void cadastraObjetivoComTipoVazioOuNulo(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("","Fazer todos gostarem mais de testar programas.", 2,4 );});
        assertThrows(NullPointerException.class, () -> {
            controllerObjetivo.cadastraObjetivo(null,"Fazer todos gostarem mais de testar programas.", 3,4 );});

    }

    @Test
    void cadastraObjetivoComTipoInvalido(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("MALIGNO","Descricao teste", 2,2 );},"Valor invalido de tipo.");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("432","Descricao teste", 2,2 );},"Valor invalido de tipo.");
    }

    @Test
    void cadastraObjetivoComDescricaoVaziaOuNula(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL","", 1,1 );});
        assertThrows(NullPointerException.class, () -> {
            controllerObjetivo.cadastraObjetivo("GERAL",null, 1,1 );});
    }

    @Test
    void cadastraObjetivoComAderenciaInvalida(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", 0,4 );});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", 6,4 );});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", -1,4 );});
    }

    @Test
    void cadastraObjetivoComViabilidadeInvalida(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", 4,6 );});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", 3,0 );});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.cadastraObjetivo("ESPECIFICO","Realizar atividades ludicas para que todos fiquem sossegados no LCC-2.", 2,-2 );});
    }

    @Test
    void apagarObjetivo() {
        controllerObjetivo.apagarObjetivo("O1");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O1");} );

        controllerObjetivo.apagarObjetivo("O2");
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O2");} );
    }

    @Test
    void apagarObjetivoInexistente(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O3");} );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O4");} );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("OA");} );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("");} );
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.apagarObjetivo("O55");} );
    }



    @Test
    void testExibeObjetivo() {
        assertEquals(controllerObjetivo.exibeObjetivo("O1"), "O1 - GERAL - Aumentar o interesse dos alunos em realizar testes nas aulas de programacao - 10");
        assertEquals(controllerObjetivo.exibeObjetivo("O2"), "O2 - ESPECIFICO - Criar bonfiicacao aos melhores testadores de cada turma - 9");

    }

    @Test
    void exibeObjetivoInexistente(){
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.exibeObjetivo("");});
        assertThrows(NullPointerException.class, () -> {
            controllerObjetivo.exibeObjetivo(null);});
        assertThrows(IllegalArgumentException.class, () -> {
            controllerObjetivo.exibeObjetivo("O3");});
    }
}