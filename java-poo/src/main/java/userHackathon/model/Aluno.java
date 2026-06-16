package userHackathon.model;

public class Aluno {
    private Long id;
    private String cargo;
    private String cpf;
    private String email;
    private String telefone;
    private String curso;
    private Integer periodo;
    private String dataNascimento;
    private Long idEnderecoAluno;
    private EnderecoAluno endereco;
    private Integer ra;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getRa() {
        return ra;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public EnderecoAluno getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoAluno endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
