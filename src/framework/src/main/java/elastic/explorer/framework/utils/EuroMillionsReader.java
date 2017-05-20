package elastic.explorer.framework.utils;

import elastic.explorer.framework.model.euromilhoes.EuroMilllionsResult;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author andre
 */
public class EuroMillionsReader {

    private final Consumer<String> consumer;
    private static final List<EuroMilllionsResult> res = new ArrayList<>();

    public EuroMillionsReader() {
        consumer = EuroMillionsReader::addObjectRes;
    }

    public List<EuroMilllionsResult> getEuroMillionsResults() {
        try (Stream<String> stream = Files.lines(Paths.get("resultados.txt"))) {
            stream.forEach(consumer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    private static void addObjectRes(String line) {
        try{
        EuroMilllionsResult chave = new EuroMilllionsResult();
        String[] entries = line.split("-");
        chave.setId(Long.valueOf(entries[0].trim()));
        String dateString = entries[1];
        DateFormat format = new SimpleDateFormat("dd MMM yyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (Exception e) {
            date = new Date();
        }
        chave.setDate(date == null ? new Date() : date);
        String numbers = entries[2];
        String numArr [] = numbers.split("\\s+");
        List<Integer> list = new ArrayList();
        for(String s : numArr){
            try{
                list.add(Integer.valueOf(s));
            }catch(Exception e){
                
            }
        }
        chave.setNumbers(list);
        String stars = entries[3];
        stars = stars.replaceAll("\\(", "");
        stars = stars.replaceAll("\\)", "");
        String starsArr [] = stars.split("\\s+");
        List<Integer> starsList = new ArrayList();
         for(String s : starsArr){
            try{
                starsList.add(Integer.valueOf(s));
            }catch(Exception e){
                
            }
        }
        chave.setStars(starsList);
        chave.setJackpot(entries[4]);
        chave.setWinners(Integer.valueOf(entries[5].trim()));
        System.out.println(line);
        res.add(chave);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on line " + line);
        } 
    }
}
