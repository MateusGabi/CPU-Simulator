public class MyThread extends Thread implements Comparable<MyThread> {

	/**
	 * Classe que representa os possíveis estados de uma classe
	 */
	static enum Estado {
		PRONTO,  EXECUTANDO, TERMINADO;
	}


	private int id;
	private int prioridade;	
	private int inter;
	private int unidade;
	private int tempoExecucao;
	
	private long resta;

	private MyThread.Estado estado;
	
	public MyThread(int id, int prio, int inter, int unidade) {
		super();
		this.id = id;
		this.prioridade = prio;
		this.inter = inter;
		this.unidade = unidade;
		
		tempoExecucao = 2 * (id + 1) *  unidade;
		resta = tempoExecucao;
	}

	@Override
	public int compareTo(MyThread o) {
		if (this.prioridade > o.getPrioridade()) {
			return -1;
		}
		else if (this.prioridade < o.getPrioridade()) {
			return 1;
		}
		else if (this.prioridade == o.getPrioridade()) {
			

			if (this.id > o.getId()) {
				return -1;
			}
			else if (this.id < o.getId()) {
				return 1;
			}
			
		}
		
		return 0;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		
		if(prioridade > 100) prioridade = 100;
		
		this.prioridade = prioridade;
	}

	public int getInter() {
		return inter;
	}

	public void setInter(int inter) {
		this.inter = inter;
	}

	public int getUnidade() {
		return unidade;
	}

	public void setUnidade(int unidade) {
		this.unidade = unidade;
	}
	
	public int getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(int tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public long getResta() {
		return resta;
	}

	public void setResta(long resta) {
		this.resta = resta;
	}

	public MyThread.Estado getEstado() { return this.estado; }

	public void setEstado(MyThread.Estado estado) { this.estado = estado; }


    @Override
    public String toString() {
        return
				"\n----------------------------------\n" +
				"Thread #" + id + "\n" +
                "Estado: " + estado + "\n" +
                "TempoExecucao Total: " + tempoExecucao + "\n" +
                "Resta: " + resta + "\n" +
                "Prioridade: " + prioridade + "\n" +
                "----------------------------------\n";
    }
}
