package ValueObjects;

public abstract class Veiculo {

    private int qtdLugares;
    private String cor;
    private Pessoa pessoa;
    private VagaEstacionamento vagaEstacionamento;

    public Veiculo(VagaEstacionamento vagaEstacionamento) {
        setVagaEstacionamento(vagaEstacionamento);
    }

    public Veiculo(int qtdLugares, String cor, VagaEstacionamento vagaEstacionamento) {
        setQtdLugares(qtdLugares);
        setCor(cor);
        setVagaEstacionamento(vagaEstacionamento);
    }

    public int getQtdLugares() {
        return qtdLugares;
    }

    public void setQtdLugares(int qtdLugares) {
        this.qtdLugares = qtdLugares;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public VagaEstacionamento getVagaEstacionamento() {
        return vagaEstacionamento;
    }

    public void setVagaEstacionamento(VagaEstacionamento vagaEstacionamento) {
        this.vagaEstacionamento = vagaEstacionamento;
    }

}
