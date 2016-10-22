package elastic.explorer.framework.elasticsearch;

import elastic.explorer.framework.utils.Constants;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.node.Node;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Operations {

    private Node node;
    private Client client;
    private BulkProcessor bulkProcessor;
    private SearchResponse response;

    private static final Logger LOG = LoggerFactory.getLogger(Operations.class);

    public void createIndex(String indexName, String home) {
        LOG.trace("Create Index () - start");
        startNodeAndClient(home);
        client.admin().indices().prepareCreate(Constants.INDEX_NAME).get();
        LOG.trace("Create Index () - end");
    }

    public void indexTester() {
        startNodeAndClient(Constants.ELASTIC_HOME);
        try {
            IndexResponse response = null;
            for (int i = 1; i < 1000; i++) {
                response = client.prepareIndex(Constants.INDEX_NAME, Constants.MAPPING_NAME)
                        .setSource(jsonBuilder()
                                .startObject()
                                .field("user", "user" + i)
                                .field("today_date", new Date())
                                .field("test_speed", "Teste of things")
                                .endObject()
                        )
                        .get();
                System.out.println("Indexed document: " + i);
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            client.close();
        }
    }

    /*OK!!!*/
    public void testBulkProcessor() {
        startNodeAndClient(Constants.ELASTIC_HOME);
        try {
            for (int i = 1; i < 50; i++) {
                IndexRequest request = client.prepareIndex(Constants.INDEX_NAME, Constants.MAPPING_NAME)
                        .setSource(jsonBuilder()
                                .startObject()
                                .field("user", "user" + i)
                                .field("today_date", new Date())
                                .field("test_speed", "Teste of things")
                                .endObject()
                        ).request();

                bulkProcessor.add(request);
                System.out.println("Saved document: " + i);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            bulkProcessor.flush();
            bulkProcessor.close();
            client.close();
        }

    }

    private void startNodeAndClient(String home) {
        if (node == null) {
            node = NodeBuilder.nodeBuilder().clusterName("andre").client(true)
                    .settings(Settings.builder().put("path.home", home)).node();
        }
        if (client == null) {
            client = node.client();
        }
        if (bulkProcessor == null) {
            bulkProcessor = BulkProcessor.builder(
                    client,
                    new BulkProcessor.Listener() {
                        @Override
                        public void beforeBulk(long executionId,
                                BulkRequest request) {
                            System.out.println("Bulk started");
                        }

                        @Override
                        public void afterBulk(long executionId,
                                BulkRequest request,
                                BulkResponse response) {
                            System.out.println("Bulk finished");
                        }

                        @Override
                        public void afterBulk(long executionId,
                                BulkRequest request,
                                Throwable failure) {
                            System.out.println("Bulk finished");
                        }
                    })
                    .setBulkActions(10000)
                    .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
                    .setFlushInterval(TimeValue.timeValueSeconds(5))
                    .setConcurrentRequests(1)
                    .build();

            response = null;
        }
    }

    public void flushBulkProcessor() {
        bulkProcessor.close();
    }
}
