/* 영화관련 정보를 다룸 */
package Project;

public class Movie {
	String id, theater, moviename, movietime, movieseat;
	int movieprice, movieperson, cardnumber;
	
	public String getid() {
		return id;
	}
	public String gettheater() {
		return theater;
	}
	public String getmoviename() {
		return moviename;
	}
	public String getmovietime() {
		return movietime;
	}
	public String getmovieseat() {
		return movieseat;
	}
	public int getmovieprice() {
		return movieprice;
	}
	public int getmovieperson() {
		return movieperson;
	}
	public int getcardnumber() {
		return cardnumber;
	}
	
	public void setid(String id) {
		this.id = id;
	}
	public void settheater(String theater) {
		this.theater = theater;
	}
	public void setmoviename(String moviename) {
		this.moviename = moviename;
	}
	public void setmovietime(String movietime) {
		this.movietime = movietime;
	}
	public void setmovieseat(String movieseat) {
		this.movieseat = movieseat;
	}
	public void setmovieprice(int movieprice) {
		this.movieprice = movieprice;
	}
	public void setmovieperson(int movieperson) {
		this.movieperson = movieperson;
	}
	public void setcardnumber(int cardnumber) {
		this.cardnumber = cardnumber;
	}
}
