package userHackathon.model;

public class Relatorios {
    private Long id_empresas;
    private Long id_alunos;
    private Long id_vagas;
    private Long id_candidaturas;
    private Long id_contratos;
    private String curso;
    private Integer periodo;
    private String dataNascimento;
    private Long idEnderecoAluno;

    public Long getId_empresas() {
        return id_empresas;
    }

    public void setId_empresas(Long id_empresas) {
        this.id_empresas = id_empresas;
    }

    public long getId_alunos() {
        return id_alunos;
    }

    public void setId_alunos(long id_alunos) {
        this.id_alunos = id_alunos;
    }

    public Long getId_vagas() {
        return id_vagas;
    }

    public void setId_vagas(Long id_vagas) {
        this.id_vagas = id_vagas;
    }

    public Long getId_candidaturas() {
        return id_candidaturas;
    }

    public void setId_candidaturas(Long id_candidaturas) {
        this.id_candidaturas = id_candidaturas;
    }

    public Long getId_contratos() {
        return id_contratos;
    }

    public void setId_contratos(Long id_contratos) {
        this.id_contratos = id_contratos;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getIdEnderecoAluno() {
        return idEnderecoAluno;
    }

    public void setIdEnderecoAluno(Long idEnderecoAluno) {
        this.idEnderecoAluno = idEnderecoAluno;
    }
}

