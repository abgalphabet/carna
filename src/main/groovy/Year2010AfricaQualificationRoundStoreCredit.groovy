/**
 * Created by arthur on 25/12/2016.
 */
class Year2010AfricaQualificationRoundStoreCredit {
    static void main(String[] args) {
        new Year2010AfricaQualificationRoundStoreCredit(args[0])
    }

    File run(File infile) {
        List<Map> specs = parseSpec(infile)

        List<List> solutions = specs.collect { Map test ->
            List<List> combinations = chooseK(test.noOfItems as int , 2)
            List solution = combinations.find { c -> test.credit == test.priceOfItems[c[0]] + test.priceOfItems[c[1]] }

            solution[0] += 1
            solution[1] += 1

            solution
        }

        List content = []
        solutions.eachWithIndex { List solution, int i ->
            content << "Case #${i + 1}: ${solution.join(' ')}"
        }

        File outfile = new File("${infile.name.split('\\.')[0]}.out")
        outfile.withWriter('utf-8') { it.write content.join('\n') }

        outfile
    }

    private List<Map> parseSpec(File infile) {
        List<String> ins = infile.readLines()
        int noOfTests = ins[0] as int

        List specs = []
        ins[1..-1].eachWithIndex { String entry, int i ->
            int integral = (i / 3) as int
            int remainder = i % 3

            switch (remainder) {
                case 0: specs << [credit: entry as Integer]; break;
                case 1: specs[integral] << [noOfItems: entry as Integer]; break;
                default: specs[integral] << [priceOfItems: entry.split(' ').collect { it as int }]; break;
            }
        }

        assert specs.size() == noOfTests
        specs
    }

    private List<List> chooseK(int n, int k) {
        return chooseK((0..n-1) as int[], 0, n, k)
    }

    private List<List> chooseK(int[] nums, int start, int n, int k) {
        if (n == 0 || k == 0) return [[]]
        if (n == k) return [nums[start..-1]]

        chooseK(nums, start + 1, n - 1, k - 1).collect { [nums[start]] + it } + chooseK(nums, start + 1, n - 1, k)
    }
}
