package sorteo;

import java.util.ArrayList;
import java.util.Collections;

public class Bombo<T> {

	private ArrayList<T> bolas;

	public Bombo() {
		super();
		this.bolas = new ArrayList<T>();
	}
	

	public Bombo(ArrayList<T> bolas) {
		super();
		this.bolas = bolas;
	}


	public void moverBombo() {
		Collections.shuffle(bolas);
	}

	public void introducirBola(T bola) {
		bolas.add(bola);
	}

	public T sacarBola() {
		if (bolas.isEmpty())
			return null;
		else
			return bolas.remove(0);
	}
	
	public ArrayList<T> getBolas(){
		return bolas;
	}
	
	public Bombo<T> getCopia(){
		return new Bombo<T>(new ArrayList<T>(bolas));
	}


	@Override
	public String toString() {
		return "Bombo [bolas=" + bolas + "]";
	}
	
	

}
