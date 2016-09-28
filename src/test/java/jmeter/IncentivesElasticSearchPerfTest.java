package jmeter;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tushar Chokshi @ 12/2/15.
 */
public class IncentivesElasticSearchPerfTest extends AbstractJavaSamplerClient {
    private static final String WEB_IDS = "webIds";

    private String siteUrl;

    /*
    @Override
    public Arguments getDefaultParameters() {
        Arguments defaults = new Arguments();
        defaults.addArgument(WEB_IDS, "test-merch-search");
        return defaults;
    }
    */

    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        //siteUrl = "http://nitra.test-dealer.com.websites.sea-chokst-m3.sea.ds.adp.com/merch-search";
    }

    @Override
    public SampleResult runTest(final JavaSamplerContext javaSamplerContext) {
        //System.out.println("hi....");
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
        try {

            List<String> sitesUrls = parseUrls(javaSamplerContext.getParameter("sites"));
            for (String siteUrl : sitesUrls) {
                siteUrl = decorateSiteUrl(siteUrl + "/fetchAndDeActivateIncentivesAsync.do", new HashMap<String, String>() {{
                    put("numberofoffersactivationtobetoggled", javaSamplerContext.getParameter("numberofoffers"));
                }});
                System.out.println("siteUrl: "+siteUrl);
                executePostReturningResponse(siteUrl);
            }

            Thread.sleep(10000);
            //System.out.println("hi, i m fine");
            result.setSuccessful(true);
            result.setResponseMessage("success");
            result.setResponseData("success", "UTF-8");
            result.setResponseCodeOK(); // 200 code
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setResponseMessage("Error:.."  + e);
            result.setResponseCode("500");
        }
        result.sampleEnd(); // stop stopwatch

        return result;
    }
    public static HttpResponse executePostReturningResponse(String url) throws Exception {
        HttpResponse httpResponse = new AutoRetryHttpClient(AutoRetryHttpClient.HttpMethod.POST).execute(url);
        return httpResponse;

    }
    protected String decorateSiteUrl(String siteUrl, Map<String, String> queryParams) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(siteUrl).append("?");
        if (queryParams != null && queryParams.size() > 0) {
            for (String queryParamKey : queryParams.keySet()) {
                stringBuilder.append("&").append(queryParamKey).append("=").append(encode(queryParams.get(queryParamKey)));
            }
        }
        return stringBuilder.toString();
    }
    protected static String encode(String value) {
        if (value != null) {
            try {
                return URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
    private List<String> parseUrls(String ids) throws Exception {
        List<String> webIds = new ArrayList<>();
        if (ids != null && ids.trim().length() > 0) {
            throw new Exception("Empty urls");
        } else {
            for (String webId : ids.split(",")) {
                webIds.add(webId);
            }
        }
        return webIds;
    }
    protected static class AutoRetryHttpClient {
        private HttpClient httpClient = getDefaultHttpClient();
        private int maxNumberOfRetries = 3;
        private long intervalInMilliSeconds = 3000;
        private HttpMethod httpMethod = HttpMethod.POST;

        public enum HttpMethod {
            GET, POST, DELETE;
        }

        public AutoRetryHttpClient() {

        }

        public AutoRetryHttpClient(HttpMethod httpMethod) {
            this();
            this.httpMethod = httpMethod;
        }

        public AutoRetryHttpClient(HttpMethod httpMethod, int maxNumberOfRetries, long intervalInMilliSeconds) {
            this(httpMethod);

            this.maxNumberOfRetries = maxNumberOfRetries;
            this.intervalInMilliSeconds = intervalInMilliSeconds;
        }

        public HttpClient getDefaultHttpClient() {
            RequestConfig config = getRequestConfig();
            HttpClientBuilder httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(config);
            return httpClientBuilder.build();
        }

        private RequestConfig getRequestConfig() {
            RequestConfig.Builder configBuilder = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(30000);
            RequestConfig config = configBuilder.build();
            return config;
        }

        public HttpResponse execute(String url) throws Exception {
            HttpUriRequest httpRequest = new HttpPost(url);
            if (httpMethod == HttpMethod.GET) {
                httpRequest = new HttpGet(url);
            } else if (httpMethod == HttpMethod.DELETE) {
                httpRequest = new HttpDelete(url);
            }
            return executeHttpClient(httpRequest, 0);
        }

        private HttpResponse executeHttpClient(HttpUriRequest httpRequest, int retryNumber) throws HttpStatusCodeException {
            try {
                if (retryNumber >= maxNumberOfRetries) {
                    throw new MaxRetryAttemptsExhaustedException(maxNumberOfRetries);
                }
                if (retryNumber > 0) {
                    System.out.println("Retry Number - " + retryNumber + " for " + httpRequest.getURI().toString());
                    Thread.sleep(intervalInMilliSeconds);
                }
                HttpResponse httpResponse = httpClient.execute(httpRequest);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (HttpStatus.SC_OK == statusCode) {
                    return httpResponse;
                } else {
                    throw new HttpStatusCodeException(statusCode);
                }
            } catch (InterruptedException | IOException e) {
                executeHttpClient(httpRequest, ++retryNumber);
            }

            return null;
        }

        class HttpStatusCodeException extends RuntimeException {
            protected HttpStatusCodeException(int httpStatus) {
                super("HttpStatus Code:" + httpStatus);
            }
        }

        class MaxRetryAttemptsExhaustedException extends RuntimeException {
            protected MaxRetryAttemptsExhaustedException(int maxRetryCount) {
                super("Max retry attempts " + maxRetryCount + " exhausted.");
            }
        }
    }

}
