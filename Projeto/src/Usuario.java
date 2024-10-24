

public class Usuario {

    String nome;
    Party party = new Party();
    Pc pcUsuario = new Pc();

    public Usuario(String nome){
        this.nome = nome;
    }

    public void transferirParaPc(Pokemon pokemon){
        this.pcUsuario.getPC().add(pokemon);
    }



    public void InterfacePC(){
        for(Pokemon pokemon: pcUsuario.getPC()){
            System.out.println(pokemon);
        }
    }

    public Party getParty(){
        return this.party;
    }

    public Pc getPcUsuario(){
        return this.pcUsuario;
    }

}