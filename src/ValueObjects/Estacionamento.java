package ValueObjects;

import java.util.ArrayList;
import java.util.Collection;

public class Estacionamento {

    private Collection<VagaEstacionamento> vagaEstacionamento = new ArrayList<>();

    public void listarVeiculos() {
        for(VagaEstacionamento ve : vagaEstacionamento) {
            for(Veiculo v : ve.getVeiculo()) {
                if(v instanceof Carro) {
                    System.out.println("Carro - Modelo: " + 
                            ((Carro)v).getModelo());
                }
                else if(v instanceof Bicicleta) {
                    System.out.println("Bicicleta - Tem Marcha?: " + 
                            ((Bicicleta)v).isMarcha());
                } else {
                    System.out.println("Motocicleta - Cilindrada: " + 
                            ((Motocicleta)v).getCilindrada());
                }
            }
        }
    }

	public void setVagaEstacionamento(ArrayList<VagaEstacionamento> vagas) {
		// serve para setar quantas vagas tem no estacionamento
		vagaEstacionamento = vagas;
	}


	public void setaCarro(Carro carro) {
		// metodo para definir a vaga do carro, verificar se tem vaga disponivel, e colcoa o carro na vaga vaga
		
	}

	public void setaBike(Bicicleta bike) {
		// TODO Auto-generated method stub
		
	}


}
