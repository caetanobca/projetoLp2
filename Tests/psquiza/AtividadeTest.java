package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtividadeTest {

    private Atividade atividade1;
    private Atividade atividade2;

    @BeforeEach
    public void criaAtividade() {
        this.atividade1 = new Atividade("A1","Visita tecnica a uma empresa de calcados",
                "BAIXO","Algum aluno pode se machucar ao operar, sem autorizacao previa, algum equipamento");
        this.atividade2 = new Atividade("A2","Visita a postos de saude de comunidades carentes",
                "BAIXO","Algum problema interno da comunidade causar um dano colateral aos alunos");
    }

    @Test
    public void testaConstroiAtividade() {
        Atividade atividadeLegal = new Atividade("A3","Visita tecnica a um engenho de cachaca",
                "BAIXO","Algum aluno ficar embreagado");
        Atividade atividadePsicologica = new Atividade("A4","Elaboracao de rodas de conversa com o tema homofobia",
                "BAIXO","Alguma opiniao adversa que possa acarretar desavencas");
    }

    @Test
    public void testaConstroiAtividadeComExcecoes() {
        assertThrows(NullPointerException.class,()-> new Atividade("A8",null,
                "Vistoriar instalacoes prediais antigas da cidade","Alguma instalacao possuir locais que nao suportem o peso"));
        assertThrows(IllegalArgumentException.class,()-> new Atividade("A4","",
                "Vistoriar instalacoes prediais antigas da cidade","Alguma instalacao possuir locais que nao suportem o peso"));
        assertThrows(NullPointerException.class,()-> new Atividade("A4","Vistoriar instalacoes prediais antigas da cidade",
                null,"Alguma instalacao possuir locais que nao suportem o peso"));
        assertThrows(IllegalArgumentException.class,()-> new Atividade("A3","Comer alimentos em prazos de validade proximos ao limite",
                "","Algum alimento ja estar estragado"));
        assertThrows(NullPointerException.class,()-> new Atividade("A7","Visitar Hospitais e conversar com pacientes",
                "BAIXO",null));
        assertThrows(IllegalArgumentException.class,()-> new Atividade("A7","Visitar Hospitais e conversar com pacientes",
                "BAIXO",""));
    }

    @Test
    public void testaCadastraItem() {
        atividade1.cadastraItem("Monitoramento Facebook");
        atividade1.cadastraItem("Degustacao de cervejas artesanais");
        atividade1.cadastraItem("Degustacao de cachaca");
        assertEquals(atividade1.toString(),"Visita tecnica a uma empresa de calcados (BAIXO - Algum aluno pode se machucar ao operar," +
                " sem autorizacao previa, algum equipamento) | PENDENTE - Monitoramento Facebook | PENDENTE - Degustacao de cervejas artesanais " +
                "| PENDENTE - Degustacao de cachaca");
    }

    @Test
    public void testaCadastraItemComExcecao() {
        assertThrows(NullPointerException.class,()-> atividade1.cadastraItem(null));
        assertThrows(IllegalArgumentException.class,()-> atividade2.cadastraItem(""));
    }

    @Test
    public void testaItensPendentes() {
        assertEquals(atividade2.contaItensPendentes(),0);
        atividade2.cadastraItem("Degustacao de cerveja artesanal");
        atividade2.cadastraItem("Monitoramento de Whatsapp");
        atividade2.cadastraItem("Monitoramento de Facebook");
        assertEquals(atividade2.contaItensPendentes(),3);
    }

    @Test
    public void testaItensRealizados() {
        assertEquals(atividade1.contaItensRealizados(),0);
        assertEquals(atividade2.contaItensRealizados(),0);
    }

    @Test
    public void testaToString() {
        atividade1.cadastraItem("Monitoramento Facebook");
        atividade1.cadastraItem("Degustacao de cervejas artesanais");
        assertEquals(atividade1.toString(),"Visita tecnica a uma empresa de calcados (BAIXO - Algum aluno pode se machucar ao operar," +
                " sem autorizacao previa, algum equipamento) | PENDENTE - Monitoramento Facebook | PENDENTE - Degustacao de cervejas artesanais");
    }

    @Test
    public void testaEqualsAtividadesIguais() {
        assertEquals(atividade1,new Atividade("A1","Monitoramento Tinder","BAIXO",
                "Nenhum risco foi identificado"));
        assertEquals(atividade2,new Atividade("A2","Visita a postos de saude de comunidades carentes",
                "BAIXO","Algum problema interno da comunidade causar um dano colateral aos alunos"));
    }

    @Test
    public void testaEqualsAtividadesDiferentes() {
        assertNotEquals(atividade1,new Atividade("A4","Visita tecnica a uma empresa de calcados",
                "BAIXO","Algum aluno pode se machucar ao operar, sem autorizacao previa, algum equipamento"));
        assertNotEquals(atividade2,new Atividade("A7","Monitoramento do Tinder",
                "BAIXO","Nenhum risco foi identificado"));
    }

    @Test
    public void testaHashCodeIgual() {
        Atividade atividadeIdIgual = new Atividade("A1","Visita a instalacao Hospitalar",
                "MEDIO","Alguma contaminacao hospitalar ser adquirida");
        Atividade atividadeTudoIgual = new Atividade("A2","Visita a postos de saude de comunidades carentes",
                "BAIXO","Algum problema interno da comunidade causar um dano colateral aos alunos");
        assertTrue(atividade1.hashCode()==atividadeIdIgual.hashCode());
        assertTrue(atividadeTudoIgual.hashCode()==atividade2.hashCode());
    }

    @Test
    public void testaHashCodeDiferente() {
        Atividade atividadeSoIdDiferente = new Atividade("A4","Visita a postos de saude de comunidades carentes",
                "BAIXO","Algum problema interno da comunidade causar um dano colateral aos alunos");
        Atividade atividadeTudoDiferente = new Atividade("A10","Degustacao de Frutos do Mar",
                "MEDIO","Algum aluno ser alergico a frutos do mar e nao saber");
        assertFalse(atividade2.hashCode()==atividadeSoIdDiferente.hashCode());
        assertFalse(atividade1.hashCode()==atividadeTudoDiferente.hashCode());
    }
}