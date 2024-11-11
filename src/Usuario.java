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

    public static Usuario logarUsuario (String email, String senha) throws UsuarioInvalido{
        Usuario u = Dados.obterUsuarioPorEmail(email);
        if(u == null) throw new UsuarioInvalido("Usuario ou senha invalidos");
        if(!(u.getEmail().equals(email)) || !(u.senha.equals(senha))) throw new UsuarioInvalido("Usuario ou senha invalidos");
        return u;
    }


}