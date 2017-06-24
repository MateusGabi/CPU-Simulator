import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;


public class MyCPU implements Runnable {

    private ArrayList<MyThread> filaDeProntos;
    private MyThread threadExecutando;
    private ArrayList<MyThread> listaMyThreadTerminados;

    private boolean isRunning;
    private long tempoMaximoDeExecucaoDeUmaThread;

    public MyCPU(long quantum) {
        super();
        this.filaDeProntos = new ArrayList<MyThread>();
        this.listaMyThreadTerminados = new ArrayList<MyThread>();

        this.tempoMaximoDeExecucaoDeUmaThread = quantum;

        this.isRunning = false;

        this.run();
    }

    /**
     *
     * Método que recebe uma thread para ser executada pela CPU
     *
     * @param thread
     */
    public void alocar(MyThread thread) {


        try {

            Thread.sleep((long) (thread.getId() * thread.getInter()));

        } catch (InterruptedException e) {
        }

        filaDeProntos.add(thread);

        thread.setEstado(MyThread.Estado.PRONTO);

        System.out.println(thread);

        System.out.println("Há " + filaDeProntos.size() + " threads PRONTAS.");

    }

    /**
     * Método responsável por "executar" uma thread e gerenciar as filas
     */
    @Override
    public void run() {

        if (isRunning) {
             return;
        }


        this.isRunning = true;

        while (filaDeProntos.size() > 0) {

			/* pegar o de maior prioridade */
            MyThread thread = this.getNext();

            if (thread == null) {
                break;
            }


            thread.setEstado(MyThread.Estado.EXECUTANDO);


            this.filaDeProntos.remove(thread);

            this.threadExecutando = thread;

            System.out.println(thread);

            long quantoTempoFalta = thread.getResta() - tempoMaximoDeExecucaoDeUmaThread;

			/* quando realmente falta, e precisa ser executado de novo */
            if (quantoTempoFalta > 0) {

                try {

                    thread.sleep(tempoMaximoDeExecucaoDeUmaThread);

                } catch (InterruptedException e) {

                }

                thread.setPrioridade(thread.getPrioridade() - 1);
                thread.setResta(quantoTempoFalta);

                this.filaDeProntos.add(thread);
                thread.setEstado(MyThread.Estado.PRONTO);

                System.out.println("Tempo esgotado! Thread #" + thread.getId() + " volta para fila de prontos.");
            } else {

                try {

                    thread.sleep(quantoTempoFalta * -1);

                } catch (InterruptedException e) {
                }

                thread.setResta(0);
                thread.setEstado(MyThread.Estado.TERMINADO);

                System.out.println(thread);

                this.listaMyThreadTerminados.add(thread);

            }

        }


        this.isRunning = false;

        System.out.println("Não há threads para serem executadas e CPU foi parada.");
    }

    private MyThread getNext() {

        ArrayList<MyThread> copiaFilaProntos = (ArrayList<MyThread>) filaDeProntos.clone();

        Collections.sort(copiaFilaProntos);

        System.out.println("Thread #" + copiaFilaProntos.get(0).getId() + " indo para Execução.");

        return copiaFilaProntos.get(0);

    }

}
