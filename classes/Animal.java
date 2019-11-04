import java.util.List;


public abstract class Animal {
    
    private boolean vivo;
    private Campo campo;
    private Localizacao localizacao;


    public Animal(Campo campo, Localizacao localizacao) {
        vivo = true;
        this.campo = campo;
        setLocalizacao(localizacao);
    }

   
    abstract public void atividade(List<Animal> novoAnimal);

   
    public boolean estaVivo() {
        return vivo;
    }

    
    public void setMorte() {
        vivo = false;
        if (localizacao != null) {
            campo.limpa(localizacao);
            localizacao = null;
            campo = null;
        }
    }

  
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao newLocalizacao) {
        if (localizacao != null) {
            campo.limpa(localizacao);
        }
        localizacao = newLocalizacao;
        campo.lugar(this, newLocalizacao);
    }

    public Campo getCampo() {
        return campo;
    }

}
 