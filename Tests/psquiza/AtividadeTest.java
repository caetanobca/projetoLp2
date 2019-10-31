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

    }

}