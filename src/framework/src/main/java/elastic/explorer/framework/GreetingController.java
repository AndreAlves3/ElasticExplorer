package elastic.explorer.framework;


import elastic.explorer.framework.elasticsearch.Operations;
import elastic.explorer.framework.utils.Constants;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.ws.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private Operations elasticOperations;
     
    public GreetingController(){
        elasticOperations = new Operations();
    }
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/createIndex")
    public Response startWorker(@RequestParam(value="index", defaultValue="elasticExplorer") String indexName){        
        elasticOperations.createIndex(indexName,Constants.ELASTIC_HOME);
        return null;
    }
    
    @RequestMapping("/indexTester")
    public void indexTester(){
        elasticOperations.indexTester();
    }
}