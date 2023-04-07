package digi.ecomm.elasticpathsdk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.exception.ApiBusinessException;
import digi.ecomm.elasticpathsdk.exception.ApiCommunicationException;
import digi.ecomm.elasticpathsdk.model.base.ErrorModel;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected AbstractService() {
        this.client = HttpClientBuilder.create().build();
    }

    protected AbstractService(final int connectTimeout, final int socketTimeout) {
        final RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout).build();
        this.client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    protected AbstractService(final RequestConfig requestConfig) {
        this.client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * Get request.
     *
     * @param endpoint
     * @param responseClass
     * @param context
     * @param <T>
     * @return
     */
    protected <T extends Object> T executeGetRequest(final String endpoint, final Class<T> responseClass, final ApiContext context) {
        try {
            final HttpUriRequest request =
                    RequestBuilder.get(context.getApiUrl() + endpoint).addHeader(authHeader(context.getAccessToken())).build();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {}", request.getMethod(), request.getURI()); //NOSONAR
            }

            final HttpResponse response = client.execute(request);
            final String responseBody = EntityUtils.toString(response.getEntity());

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {}", response.getStatusLine().getStatusCode(), responseBody); //NOSONAR
            }

            return objectMapper.readValue(responseBody, responseClass);
        } catch (IOException e) {
            throw new ApiCommunicationException(e.getMessage(), e);
        }
    }

    /**
     * Get request.
     *
     * @param endpoint
     * @param query
     * @param responseClass
     * @param context
     * @param <T>
     * @return
     */
    protected <T extends Object> T executeGetRequest(final String endpoint, final Query query, final Class<T> responseClass,
                                                     final ApiContext context) {
        try {
            final StringBuilder url = new StringBuilder(context.getApiUrl()).append(endpoint);
            final String queryString = query.toQueryString();
            if (queryString.length() > 0) {
                url.append("?").append(queryString);
            }
            final HttpUriRequest request =
                    RequestBuilder.get(url.toString()).addHeader(authHeader(context.getAccessToken())).build();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {}", request.getMethod(), request.getURI());
            }

            final HttpResponse response = client.execute(request);
            final String responseBody = EntityUtils.toString(response.getEntity());

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {}", response.getStatusLine().getStatusCode(), responseBody);
            }

            return objectMapper.readValue(responseBody, responseClass);
        } catch (IOException e) {
            throw new ApiCommunicationException(e.getMessage(), e);
        }
    }

    /**
     * Delete request.
     *
     * @param endpoint
     * @param context
     * @return
     */
    protected void executeDeleteRequest(final String endpoint, final ApiContext context) {
        try {
            final HttpUriRequest request = RequestBuilder.delete(context.getApiUrl() + endpoint)
                    .addHeader(authHeader(context.getAccessToken())).build();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {}", request.getMethod(), request.getURI());
            }

            final HttpResponse response = client.execute(request);
            final int statusCode = response.getStatusLine().getStatusCode();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}", statusCode);
            }

            if (statusCode != HttpStatus.SC_NO_CONTENT && statusCode != HttpStatus.SC_OK) {
                throw new ApiBusinessException(objectMapper.readValue(EntityUtils.toString(response.getEntity()), ErrorModel.class));
            }
        } catch (IOException e) {
            throw new ApiCommunicationException(e.getMessage(), e);
        }
    }

    /**
     * Delete request with payload.
     *
     * @param endpoint
     * @param payload
     * @param responseClass
     * @param context
     * @param <T>
     * @return
     */
    protected <T extends Object> T executeDeleteWithPayloadRequest(final String endpoint, final Object payload,
                                                                   final Class<T> responseClass, final ApiContext context) {
        try {
            final ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            final String requestBody = objectWriter.writeValueAsString(payload);
            final HttpUriRequest request = RequestBuilder.delete(context.getApiUrl() + endpoint)
                    .addHeader(authHeader(context.getAccessToken())).addHeader(acceptHeader()).addHeader(contentTypeHeader())
                    .setEntity(new StringEntity(requestBody)).build();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {} {}", request.getMethod(), request.getURI(), requestBody); //NOSONAR
            }

            final HttpResponse response = client.execute(request);
            final String responseBody = EntityUtils.toString(response.getEntity());

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {}", response.getStatusLine().getStatusCode(), responseBody); //NOSONAR
            }

            return objectMapper.readValue(responseBody, responseClass);
        } catch (IOException e) {
            throw new ApiCommunicationException(e.getMessage(), e);
        }
    }

    /**
     * Post request.
     *
     * @param endpoint
     * @param payload
     * @param responseClass
     * @param context
     * @param <T>
     * @return
     */
    protected <T extends Object> T executePostRequest(final String endpoint, final Object payload,
                                                      final Class<T> responseClass, final ApiContext context) {
        try {
            final ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            final String requestBody = objectWriter.writeValueAsString(payload);
            final HttpUriRequest request = RequestBuilder.post(context.getApiUrl() + endpoint)
                    .addHeader(authHeader(context.getAccessToken())).addHeader(acceptHeader()).addHeader(contentTypeHeader())
                    .setEntity(new StringEntity(requestBody)).build();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {} {}", request.getMethod(), request.getURI(), requestBody);
            }

            final HttpResponse response = client.execute(request);
            final String responseBody = EntityUtils.toString(response.getEntity());

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {}", response.getStatusLine().getStatusCode(), responseBody);
            }

            return objectMapper.readValue(responseBody, responseClass);
        } catch (IOException e) {
            throw new ApiCommunicationException(e.getMessage(), e);
        }
    }

    /**
     * Put request.
     *
     * @param endpoint
     * @param payload
     * @param responseClass
     * @param context
     * @param <T>
     * @return
     */
    protected <T extends Object> T executePutRequest(final String endpoint, final Object payload,
                                                     final Class<T> responseClass, final ApiContext context) {
        try {
            final ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            final String requestBody = objectWriter.writeValueAsString(payload);
            final HttpUriRequest request = RequestBuilder.put(context.getApiUrl() + endpoint)
                    .addHeader(authHeader(context.getAccessToken())).addHeader(acceptHeader()).addHeader(contentTypeHeader())
                    .setEntity(new StringEntity(requestBody)).build();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {} {}", request.getMethod(), request.getURI(), requestBody);
            }

            final HttpResponse response = client.execute(request);
            final String responseBody = EntityUtils.toString(response.getEntity());

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {}", response.getStatusLine().getStatusCode(), responseBody);
            }
            return objectMapper.readValue(responseBody, responseClass);
        } catch (IOException e) {
            throw new ApiCommunicationException(e.getMessage(), e);
        }
    }

    /**
     * Put without return request.
     *
     * @param endpoint
     * @param payload
     * @param context
     * @return
     */
    protected void executePutWithoutReturnRequest(final String endpoint, final Object payload, final ApiContext context) {
        try {
            final ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            final String requestBody = objectWriter.writeValueAsString(payload);
            final HttpUriRequest request = RequestBuilder.put(context.getApiUrl() + endpoint)
                    .addHeader(authHeader(context.getAccessToken())).addHeader(acceptHeader()).addHeader(contentTypeHeader())
                    .setEntity(new StringEntity(requestBody)).build();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} {} {}", request.getMethod(), request.getURI(), requestBody);
            }

            final HttpResponse response = client.execute(request);
            final int statusCode = response.getStatusLine().getStatusCode();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}", statusCode);
            }

            if (statusCode != HttpStatus.SC_NO_CONTENT && statusCode != HttpStatus.SC_OK) {
                throw new ApiBusinessException(objectMapper.readValue(EntityUtils.toString(response.getEntity()), ErrorModel.class));
            }
        } catch (IOException e) {
            throw new ApiCommunicationException(e.getMessage(), e);
        }
    }

    private Header contentTypeHeader() {
        return new BasicHeader("Content-type", "application/json");
    }

    private Header acceptHeader() {
        return new BasicHeader("Accept", "application/json");
    }

    private Header authHeader(final String accessToken) {
        return new BasicHeader("Authorization", accessToken);
    }
}
