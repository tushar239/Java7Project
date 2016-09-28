package jmeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import temp.Offer;

/**
 * @author Tushar Chokshi @ 12/2/15.
 */
public class Test  extends AbstractJavaSamplerClient {


    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
    }

    @Override
    public SampleResult runTest(final JavaSamplerContext javaSamplerContext) {

        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
        try {

            Offer offer = new Offer();
            offer.setId(1);

            System.out.println("hi....");

            Thread.sleep(10000);

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

}
