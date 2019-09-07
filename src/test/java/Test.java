import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Tushar Chokshi @ 7/8/15.
 */
public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        List<TermsObject> termsList = new ArrayList<>(2);
        {
            TermsObject terms = new TermsObject();
            terms.addFieldNameValues("webId", "gmps-abel", "gmps-86street");
            termsList.add(terms);

        }
        {
            TermsObject terms = new TermsObject();
            terms.addFieldNameValues("locale", "en_US");
            termsList.add(terms);

        }
        AndElement andElement = new AndElement();
        andElement.setAnd(termsList.toArray(new TermsObject[termsList.size()]));
        ObjectMapper objectMapper = new ObjectMapper();
        //filter={"and":[{"terms":{"webId":["gmps-86th-street"]}},{"terms":{"locale":["en_US"]}}]}
        System.out.println("filter=" + objectMapper.writeValueAsString(andElement));
        System.out.println(StringEscapeUtils.escapeJava("filter=" + objectMapper.writeValueAsString(andElement)));

        {
            String hexString = Hex.encodeHexString("gmps-abel:locale:12345".getBytes());
            System.out.println(hexString);
        }
        {
            byte[] bytes = "g".getBytes();
            for (byte aByte : bytes) {
                System.out.println(aByte);
            }
            String hexString = Hex.encodeHexString("gmps-abel:locale:12346".getBytes());
            System.out.println(hexString);
        }
        {
            String hexString = Hex.encodeHexString("10995".getBytes());
            System.out.println('A');
            System.out.println(hexString);
        }

        LinkedList<Integer> linkedList = new LinkedList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(5, 5);
        }};
        System.out.println(linkedList.get(2));

        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");

    }

    private static Date getDate(Date date) {
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }

    static class AndElement implements Cloneable {
        private Object[] and;

        @JsonProperty("and")
        public Object[] getAnd() {
            return and;
        }

        public void setAnd(Object[] and) {
            this.and = and;
        }

    }

    static class TermsObject {
        private Map<String, String[]> terms = new HashMap<>();

        @JsonProperty("terms")
        public Map<String, String[]> getTerms() {
            return terms;
        }

        public void setTerms(Map<String, String[]> terms) {
            this.terms = terms;
        }

        public void addFieldNameValues(String fieldName, String... fieldValues) {
            terms.put(fieldName, fieldValues);
        }

    }
}
