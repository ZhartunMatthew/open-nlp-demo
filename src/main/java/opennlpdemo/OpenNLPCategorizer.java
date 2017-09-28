package opennlpdemo;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class OpenNLPCategorizer {

    private DoccatModel model;

    void trainModel() {
        InputStream dataIn = null;
        try {
            dataIn = new FileInputStream("data/train.txt");
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);
            // Specifies the minimum number of times a feature must be seen
            int cutoff = 2;
            int trainingIterations = 30;
            model = DocumentCategorizerME.train("en", sampleStream, cutoff,
                    trainingIterations);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataIn != null) {
                try {
                    dataIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void classify(String input) {
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);
        double[] outcomes = myCategorizer.categorize(input);
        String category = myCategorizer.getBestCategory(outcomes);

        if (category.equalsIgnoreCase("0")) {
            System.out.println("HI");
        } else if (category.equalsIgnoreCase("1")){
            System.out.println("BYE");
        } else {
            System.out.println("ROOM");
        }
    }
}