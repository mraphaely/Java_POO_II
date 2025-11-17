package model;

import java.time.LocalDateTime;

public class Usuario {

    private int idUsuario;
    private String nome;
    private String email;
    private String senhaHash;
    private String tipoUsuario;
    private LocalDateTime dataCriacao;
    private boolean status;

    public Usuario() {}

    public Usuario(int idUsuario, String nome, String email, String senhaHash,
                   String tipoUsuario, LocalDateTime dataCriacao, boolean status) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
