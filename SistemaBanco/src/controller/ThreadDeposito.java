package controller;
import java.util.concurrent.Semaphore;

public class ThreadDeposito extends Thread {
	private int idConta;
	private double saldoConta,valorTransacao;
	private Semaphore limiteDeposito;
	
	public ThreadDeposito (int idConta, double saldoConta, double valorTransacao, Semaphore limiteDeposito) {
		this.idConta = idConta; 
		this.saldoConta = saldoConta;
		this.valorTransacao = valorTransacao;
		this.limiteDeposito = limiteDeposito;
	}
	
	public void run () {
		try { //Tentativa e Erro
			limiteDeposito.acquire(); //Adquire o Sem�foro limiteDeposito
			creditoConta(); //Roda o m�todo creditoConta
			sleep(1000); //Permanece parado por 1000 ms
		} catch (InterruptedException e) { //Tratamento de exce��o
			e.printStackTrace();
		} finally {
			limiteDeposito.release(); //Libera o sem�foro para a pr�xima opera��o
		}
	}
	
	
	private void creditoConta() {
		System.out.format("Conta %d - Saldo Anterior: R$ %.2f Deposito no valor de: R$ %.2f Novo saldo: R$ %.2f%n",
				idConta, saldoConta, valorTransacao, saldoConta + valorTransacao);
		this.saldoConta += valorTransacao;
	}
}
