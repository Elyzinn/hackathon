package userHackathon.model;

import java.util.Date;

public class Candidaturas {
    private Long id;
    private Long aluno_id;
    private Long vagas_id;
    private Date data_candidatura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Long aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Long getVagas_id() {
        return vagas_id;
    }

    public void setVagas_id(Long vagas_id) {
        this.vagas_id = vagas_id;
    }

    public Date getData_candidatura() {
        return data_candidatura;
    }

    public void setData_candidatura(Date data_candidatura) {
        this.data_candidatura = data_candidatura;
    }
}
