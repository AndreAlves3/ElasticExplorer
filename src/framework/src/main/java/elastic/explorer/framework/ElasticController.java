package elastic.explorer.framework;


import elastic.explorer.framework.elasticsearch.Operations;
import elastic.explorer.framework.utils.Constants;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.ws.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private Operations elasticOperations;
     
    public ElasticController(){
        elasticOperations = new Operations();
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
    
    @RequestMapping("/testBulkProcessor")
    public void testBulk(){
        elasticOperations.testBulkProcessor();
    }
    
}