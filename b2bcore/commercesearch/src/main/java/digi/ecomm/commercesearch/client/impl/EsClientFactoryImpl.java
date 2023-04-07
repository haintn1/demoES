package digi.ecomm.commercesearch.client.impl;

import digi.ecomm.commercesearch.client.EsClientFactory;
import digi.ecomm.commercesearch.repository.EsServerRepository;
import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsServer;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.List;

public class EsClientFactoryImpl implements EsClientFactory {

    private final EsServerRepository serverRepository;

    public EsClientFactoryImpl(final EsServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public RestHighLevelClient get(final EsFacetSearchConfig facetSearchConfig) {
        ServicesUtils.validateParameterNotNullStandardMessage("facetSearchConfig", facetSearchConfig);

        final List<EsServer> servers = serverRepository.findByServerConfig(facetSearchConfig.getServerConfig());

        final HttpHost[] httpHosts = servers.stream().map(server ->
                new HttpHost(server.getHostName(), server.getPort(), server.getScheme())).toArray(size -> new HttpHost[size]);
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}
