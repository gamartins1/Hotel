package ValueObjects;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Pessoa {

    private String nome;
    private Set<Veiculo> veiculo = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Veiculo> getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Set<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }
        
        

}
