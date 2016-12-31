package carna.google.codejam.y2k10.round.qualification.africa

import spock.lang.Specification
import spock.lang.Unroll

import static org.hamcrest.core.IsEqual.equalTo
import static spock.util.matcher.HamcrestSupport.expect

/**
 * Created by arthur on 25/12/2016.
 */
class StoreCreditSpecification extends Specification {

    @Unroll
    "load store credit file - #filename"() {
        given:
        URI inUri = this.class.getResource("${filename}.in").toURI()
        URI outUri = this.class.getResource("${filename}.out").toURI()


        when:
        File outfile = new StoreCredit().run(new File(inUri.path))


        then:
        expect outfile.name, equalTo("${filename}.out" as String)
        expect outfile.text, equalTo(new File(outUri.path).text)

        where:
        filename << ['store-credit-sample', 'A-small-practice', 'A-large-practice']

    }

}
