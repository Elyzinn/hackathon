package userHackathon.model;

import java.util.Date;

public class Vagas {
    private Long id;
    private String vaga_preenchida;
    private Date data_abertura;
    private Date data_fechamento;
    private String requisitos;
    private Long empresas_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVaga_preenchida() {
        return vaga_preenchida;
    }

    public void setVaga_preenchida(String vaga_preenchida) {
        this.vaga_preenchida = vaga_preenchida;
    }

    public Date getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(Date data_abertura) {
        this.data_abertura = data_abertura;
    }

    public Date getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(Date data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public Long getEmpresas_id() {
        return empresas_id;
    }

    public void setEmpresas_id(Long empresas_id) {
        this.empresas_id = empresas_id;
    }
}
