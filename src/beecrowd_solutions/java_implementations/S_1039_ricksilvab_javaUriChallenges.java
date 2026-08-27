package computionalGeometry;

import java.util.Arrays;
import java.util.Scanner;
//https://www.urionlinejudge.com.br/judge/en/problems/view/1039
public class FireFlowers1039 {

	public static void main (String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String line;
		int positions [];
		while(sc.hasNextLine()){
			 line = sc.nextLine();
			 positions = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
			 System.out.println(circleAreas(positions));
		}
	}
	
	public static double pointsDist(int[] hunterPos, int[] flowerPos ){
		int xDiff = hunterPos[0] - flowerPos[0];
		int yDiff = hunterPos[1] - flowerPos[1];
		
		double sumDiffs = Math.pow(xDiff, 2) + Math.pow(yDiff,2);
		
		return Math.sqrt(sumDiffs);
	}
	
	public static String circleAreas(int[] pos){
		 int [] hunterPos = Arrays.copyOfRange(pos, 1, 3);
		 int [] flowerPos = Arrays.copyOfRange(pos, 4, 6);
		
		 double hunterRadius = pos[0];
		 double flowerRadius = pos[3];
		 
		 double dist = pointsDist(hunterPos,flowerPos) + flowerRadius;
		
		 if(hunterRadius >= dist) return "RICO";
		 else return "MORTO";
		
	}
	
}
