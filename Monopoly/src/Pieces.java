import java.util.ArrayList;

public class Pieces extends Game{

	private ArrayList<String> list = new ArrayList<String>();
//	private String piece1;
//	private String piece2;
//	private String piece3;
//	private String piece4;
//	private String piece5;
//	private String piece6;
//	private String piece7;
//	private String piece8;
	
	public Pieces(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8) {
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		
	}
	
	public void removePiece(int n) {
		list.remove(n);
	}
	
	public int getSize() {
		return list.size();
	}
	
	public String getPiece(int n) {
		return list.get(n);
	}

//	public String getPiece1() {
//		return piece1;
//	}
//
//	public void setPiece1(String piece1) {
//		this.piece1 = piece1;
//	}
//
//	public String getPiece2() {
//		return piece2;
//	}
//
//	public void setPiece2(String piece2) {
//		this.piece2 = piece2;
//	}
//
//	public String getPiece3() {
//		return piece3;
//	}
//
//	public void setPiece3(String piece3) {
//		this.piece3 = piece3;
//	}
//
//	public String getPiece4() {
//		return piece4;
//	}
//
//	public void setPiece4(String piece4) {
//		this.piece4 = piece4;
//	}
//
//	public String getPiece5() {
//		return piece5;
//	}
//
//	public void setPiece5(String piece5) {
//		this.piece5 = piece5;
//	}
//
//	public String getPiece6() {
//		return piece6;
//	}
//
//	public void setPiece6(String piece6) {
//		this.piece6 = piece6;
//	}
//
//	public String getPiece7() {
//		return piece7;
//	}
//
//	public void setPiece7(String piece7) {
//		this.piece7 = piece7;
//	}
//
//	public String getPiece8() {
//		return piece8;
//	}
//
//	public void setPiece8(String piece8) {
//		this.piece8 = piece8;
//	}
}
