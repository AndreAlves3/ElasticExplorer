package elastic.explorer.framework.model.euromilhoes;

import java.util.Date;
import java.util.List;

public class EuroMilllionsResult {
    private Long id;
    private Date date;
    private List<Integer> numbers;
    private List<Integer> stars;
    private String jackpot;
    private Integer winners;
    
    public EuroMilllionsResult(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getStars() {
        return stars;
    }

    public void setStars(List<Integer> stars) {
        this.stars = stars;
    }

    public String getJackpot() {
        return jackpot;
    }

    public void setJackpot(String jackpot) {
        this.jackpot = jackpot;
    }

    public Integer getWinners() {
        return winners;
    }

    public void setWinners(Integer winners) {
        this.winners = winners;
    } 
    
}
