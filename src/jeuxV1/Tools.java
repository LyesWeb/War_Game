package jeuxV1;

public class Tools {
	public static double getRandom(double min, double max){
	    double x = (Math.random()*((max-min)+1))+min;
	    return x;
	}
}
