import java.util.Scanner;

/**
 * Executa o sistema
 */
public class Main {

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);


		int num = scanner.nextInt();
	    int inter = scanner.nextInt();
		int unidade = scanner.nextInt();
		int quantum = scanner.nextInt();
			
		
		
		System.out.println("Inicio da simulação");	
		

		MyCPU cpu = new MyCPU(quantum);


		MyThread myThread = new MyThread(scanner.nextInt(), scanner.nextInt(), inter, unidade);


		myThread.start();

		cpu.alocar(myThread);

		for (int i = 0; i < num; i++) {

			myThread = new MyThread(scanner.nextInt(), scanner.nextInt(), inter, unidade);
			myThread.start();
			cpu.alocar(myThread);

        }


		cpu.run();


		try {
			myThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Término da observação");
	}

}
