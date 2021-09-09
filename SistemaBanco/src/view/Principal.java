package view; 
import controller.ThreadDeposito;
import controller.ThreadSaque;
import java.util.concurrent.Semaphore;


public class Principal {

	public static void main(String[] args) {
			Semaphore limiteSaque = new Semaphore(1);
			Semaphore limiteDeposito = new Semaphore(1);
			//O sistema envia até 20 transações simultâneas aleatoriamente
				for (int i = 0; i < 20; i++) {
					int limite = (int) (Math.random() * 2); //Não recebe 2 depósitos ou 2 saques simultâneamente
					int idConta = (int) (Math.random() * 1000); //Número da conta
					double saldoConta = Math.round((Math.random() * 10000) * 100d) / 100d; //Saldo aleatório
					double valorTransacao = Math.round((Math.random() * 10000) * 100d) / 100d; //Valor que deve ser debitado ou creditado
						if (limite == 0) { //Devido a limitação, só podemos trabalhar com 0 e 1 no operador condicional
							ThreadDeposito deposito = new ThreadDeposito(idConta, saldoConta, valorTransacao, limiteSaque);
							deposito.start(); //Inicio da Thread Deposito
								} else if (limite == 1) {
									ThreadSaque saque = new ThreadSaque(idConta, saldoConta, valorTransacao, limiteDeposito);
									saque.start(); //Inicio da Thread Saque
								}
							}
						}
					}
