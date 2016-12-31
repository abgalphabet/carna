import spock.lang.Specification

import static org.hamcrest.core.IsEqual.equalTo
import static spock.util.matcher.HamcrestSupport.expect

/**
 * Created by arthur on 25/12/2016.
 */
class Year_2010_Africa_Qualification_Round extends Specification {

    def "load sample store credit file"() {
        given:
        String filename = "store_credit_small"
        URI inUri = this.class.getResource("${filename}.in").toURI()
        URI outUri = this.class.getResource("${filename}.out").toURI()


        when:
        File outfile = new Year2010AfricaQualificationRoundStoreCredit().run(new File(inUri.path))


        then:
        expect outfile.name, equalTo("${filename}.out" as String)
        expect outfile.text, equalTo(new File(outUri.path).text)

    }

}
