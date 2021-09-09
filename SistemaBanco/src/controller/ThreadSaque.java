package controller;

import java.util.concurrent.Semaphore;

public class ThreadSaque extends Thread {
	private int idConta;
	private double saldoConta,valorTransacao;
	private Semaphore limiteSaque;
	
	//Construtor
	public ThreadSaque (int idConta, double saldoConta, double valorTransacao, Semaphore limiteSaque) {
		this.idConta = idConta;
		this.saldoConta = saldoConta;
		this.valorTransacao = valorTransacao;
		this.limiteSaque = limiteSaque;
		
	}
	
	public void run() {
		try { //Tentativa e erro
			limiteSaque.acquire(); //Adquire o semáforo limiteSaque
			debitoConta(); //Roda o método debitoConta()
			sleep(1000); //Permanece parado por 1000ms
		} catch (InterruptedException e) { //Tratamento de exceção
			e.printStackTrace();
		} finally {
			limiteSaque.release(); //Libera o semáforo para a próxima operação
		}
	}
	
	private void debitoConta() {
		System.out.printf("Conta %d - Saldo Anterior: R$ %.2f Saque no valor de: R$ %.2f Novo saldo: R$ %.2f%n",
				idConta, saldoConta, valorTransacao, saldoConta + valorTransacao);
		this.saldoConta += valorTransacao; //Utiliza o valor da Transação como forma de débito, colocando-o no registro saldoConta
	}
	
}
