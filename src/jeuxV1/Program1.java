package jeuxV1;

class A extends Thread {
	void manger() {
		System.out.println("Ham Ham ");
		try{Thread.sleep(1000);}catch(Exception exp){}
	}
	@Override
	public void run() {
			manger();
	}
}

class B extends Thread {
	void marcher() {
		System.out.println("TikTak ");	
	}
	@Override
	public void run() { 
		marcher();
	}
}

public class Program1 {
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		a.manger();
		b.marcher();
	}
}