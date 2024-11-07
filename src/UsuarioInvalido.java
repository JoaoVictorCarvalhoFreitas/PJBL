public class UsuarioInvalido extends Exception {
    public UsuarioInvalido(){
        super("Usuario invalido");
    }

    public UsuarioInvalido(String msg){
        super(msg);
    }

    public UsuarioInvalido(String msg, Throwable e){
        super(msg, e);
    }

    public UsuarioInvalido(Throwable e){
        super(e);
    }

}
