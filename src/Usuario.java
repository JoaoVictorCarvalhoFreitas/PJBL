import java.io.Serializable;

abstract class Usuario implements Serializable  {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;

    public Usuario(int idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome(){return nome;}
    public int getId(){return idUsuario;}
    public String getEmail(){return email;}
    public String getSenha(){return senha;}
}