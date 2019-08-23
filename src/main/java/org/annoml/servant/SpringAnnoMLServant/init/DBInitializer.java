package org.annoml.servant.SpringAnnoMLServant.init;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Discussion;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.VegaVisualization;
import org.annoml.servant.SpringAnnoMLServant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Initializes the system with several visualizations and user accounts.
 */
@Component
@PropertySource("classpath:config.properties")
public class DBInitializer implements ApplicationRunner {


    private final AuthorRepository accountRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final DiscussionRepository discussionRepository;
    private final VisualizationRepository visualizationRepository;
    private final AnnotationRepository annotationRepository;
    private final Environment environment;

    @Autowired
    public DBInitializer(AuthorRepository accountRepository, QuestionRepository questionRepository, AnswerRepository
            answerRepository, DiscussionRepository discussionRepository, VisualizationRepository visualizationRepository, AnnotationRepository annotationRepository, Environment environment) {
        this.accountRepository = accountRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.discussionRepository = discussionRepository;
        this.visualizationRepository = visualizationRepository;
        this.annotationRepository = annotationRepository;
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        final String[] visualizations = new String[]{"{\"type\":\"vega-lite\",\"spec\":{\"$schema\":\"https://vega.github.io/schema/vega-lite/v2.6.0.json\",\"config\":{\"view\":{\"height\":300,\"width\":400}},\"data\":{\"name\":\"data-41aae2d2bdeb6ac9753016b8c0e1eb79\"},\"datasets\":{\"data-41aae2d2bdeb6ac9753016b8c0e1eb79\":[{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.2,\"sepallength\":5.1,\"sepalwidth\":3.5},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.2,\"sepallength\":4.9,\"sepalwidth\":3},{\"class\":0,\"petallength\":1.3,\"petalwidth\":0.2,\"sepallength\":4.7,\"sepalwidth\":3.2},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.2,\"sepallength\":4.6,\"sepalwidth\":3.1},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.2,\"sepallength\":5,\"sepalwidth\":3.6},{\"class\":0,\"petallength\":1.7,\"petalwidth\":0.4,\"sepallength\":5.4,\"sepalwidth\":3.9},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.3,\"sepallength\":4.6,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.2,\"sepallength\":5,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.2,\"sepallength\":4.4,\"sepalwidth\":2.9},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.1,\"sepallength\":4.9,\"sepalwidth\":3.1},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.2,\"sepallength\":5.4,\"sepalwidth\":3.7},{\"class\":0,\"petallength\":1.6,\"petalwidth\":0.2,\"sepallength\":4.8,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.1,\"sepallength\":4.8,\"sepalwidth\":3},{\"class\":0,\"petallength\":1.1,\"petalwidth\":0.1,\"sepallength\":4.3,\"sepalwidth\":3},{\"class\":0,\"petallength\":1.2,\"petalwidth\":0.2,\"sepallength\":5.8,\"sepalwidth\":4},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.4,\"sepallength\":5.7,\"sepalwidth\":4.4},{\"class\":0,\"petallength\":1.3,\"petalwidth\":0.4,\"sepallength\":5.4,\"sepalwidth\":3.9},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.3,\"sepallength\":5.1,\"sepalwidth\":3.5},{\"class\":0,\"petallength\":1.7,\"petalwidth\":0.3,\"sepallength\":5.7,\"sepalwidth\":3.8},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.3,\"sepallength\":5.1,\"sepalwidth\":3.8},{\"class\":0,\"petallength\":1.7,\"petalwidth\":0.2,\"sepallength\":5.4,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.4,\"sepallength\":5.1,\"sepalwidth\":3.7},{\"class\":0,\"petallength\":1,\"petalwidth\":0.2,\"sepallength\":4.6,\"sepalwidth\":3.6},{\"class\":0,\"petallength\":1.7,\"petalwidth\":0.5,\"sepallength\":5.1,\"sepalwidth\":3.3},{\"class\":0,\"petallength\":1.9,\"petalwidth\":0.2,\"sepallength\":4.8,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.6,\"petalwidth\":0.2,\"sepallength\":5,\"sepalwidth\":3},{\"class\":0,\"petallength\":1.6,\"petalwidth\":0.4,\"sepallength\":5,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.2,\"sepallength\":5.2,\"sepalwidth\":3.5},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.2,\"sepallength\":5.2,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.6,\"petalwidth\":0.2,\"sepallength\":4.7,\"sepalwidth\":3.2},{\"class\":0,\"petallength\":1.6,\"petalwidth\":0.2,\"sepallength\":4.8,\"sepalwidth\":3.1},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.4,\"sepallength\":5.4,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.1,\"sepallength\":5.2,\"sepalwidth\":4.1},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.2,\"sepallength\":5.5,\"sepalwidth\":4.2},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.1,\"sepallength\":4.9,\"sepalwidth\":3.1},{\"class\":0,\"petallength\":1.2,\"petalwidth\":0.2,\"sepallength\":5,\"sepalwidth\":3.2},{\"class\":0,\"petallength\":1.3,\"petalwidth\":0.2,\"sepallength\":5.5,\"sepalwidth\":3.5},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.1,\"sepallength\":4.9,\"sepalwidth\":3.1},{\"class\":0,\"petallength\":1.3,\"petalwidth\":0.2,\"sepallength\":4.4,\"sepalwidth\":3},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.2,\"sepallength\":5.1,\"sepalwidth\":3.4},{\"class\":0,\"petallength\":1.3,\"petalwidth\":0.3,\"sepallength\":5,\"sepalwidth\":3.5},{\"class\":0,\"petallength\":1.3,\"petalwidth\":0.3,\"sepallength\":4.5,\"sepalwidth\":2.3},{\"class\":0,\"petallength\":1.3,\"petalwidth\":0.2,\"sepallength\":4.4,\"sepalwidth\":3.2},{\"class\":0,\"petallength\":1.6,\"petalwidth\":0.6,\"sepallength\":5,\"sepalwidth\":3.5},{\"class\":0,\"petallength\":1.9,\"petalwidth\":0.4,\"sepallength\":5.1,\"sepalwidth\":3.8},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.3,\"sepallength\":4.8,\"sepalwidth\":3},{\"class\":0,\"petallength\":1.6,\"petalwidth\":0.2,\"sepallength\":5.1,\"sepalwidth\":3.8},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.2,\"sepallength\":4.6,\"sepalwidth\":3.2},{\"class\":0,\"petallength\":1.5,\"petalwidth\":0.2,\"sepallength\":5.3,\"sepalwidth\":3.7},{\"class\":0,\"petallength\":1.4,\"petalwidth\":0.2,\"sepallength\":5,\"sepalwidth\":3.3},{\"class\":1,\"petallength\":4.7,\"petalwidth\":1.4,\"sepallength\":7,\"sepalwidth\":3.2},{\"class\":1,\"petallength\":4.5,\"petalwidth\":1.5,\"sepallength\":6.4,\"sepalwidth\":3.2},{\"class\":1,\"petallength\":4.9,\"petalwidth\":1.5,\"sepallength\":6.9,\"sepalwidth\":3.1},{\"class\":1,\"petallength\":4,\"petalwidth\":1.3,\"sepallength\":5.5,\"sepalwidth\":2.3},{\"class\":1,\"petallength\":4.6,\"petalwidth\":1.5,\"sepallength\":6.5,\"sepalwidth\":2.8},{\"class\":1,\"petallength\":4.5,\"petalwidth\":1.3,\"sepallength\":5.7,\"sepalwidth\":2.8},{\"class\":1,\"petallength\":4.7,\"petalwidth\":1.6,\"sepallength\":6.3,\"sepalwidth\":3.3},{\"class\":1,\"petallength\":3.3,\"petalwidth\":1,\"sepallength\":4.9,\"sepalwidth\":2.4},{\"class\":1,\"petallength\":4.6,\"petalwidth\":1.3,\"sepallength\":6.6,\"sepalwidth\":2.9},{\"class\":1,\"petallength\":3.9,\"petalwidth\":1.4,\"sepallength\":5.2,\"sepalwidth\":2.7},{\"class\":1,\"petallength\":3.5,\"petalwidth\":1,\"sepallength\":5,\"sepalwidth\":2},{\"class\":1,\"petallength\":4.2,\"petalwidth\":1.5,\"sepallength\":5.9,\"sepalwidth\":3},{\"class\":1,\"petallength\":4,\"petalwidth\":1,\"sepallength\":6,\"sepalwidth\":2.2},{\"class\":1,\"petallength\":4.7,\"petalwidth\":1.4,\"sepallength\":6.1,\"sepalwidth\":2.9},{\"class\":1,\"petallength\":3.6,\"petalwidth\":1.3,\"sepallength\":5.6,\"sepalwidth\":2.9},{\"class\":1,\"petallength\":4.4,\"petalwidth\":1.4,\"sepallength\":6.7,\"sepalwidth\":3.1},{\"class\":1,\"petallength\":4.5,\"petalwidth\":1.5,\"sepallength\":5.6,\"sepalwidth\":3},{\"class\":1,\"petallength\":4.1,\"petalwidth\":1,\"sepallength\":5.8,\"sepalwidth\":2.7},{\"class\":1,\"petallength\":4.5,\"petalwidth\":1.5,\"sepallength\":6.2,\"sepalwidth\":2.2},{\"class\":1,\"petallength\":3.9,\"petalwidth\":1.1,\"sepallength\":5.6,\"sepalwidth\":2.5},{\"class\":1,\"petallength\":4.8,\"petalwidth\":1.8,\"sepallength\":5.9,\"sepalwidth\":3.2},{\"class\":1,\"petallength\":4,\"petalwidth\":1.3,\"sepallength\":6.1,\"sepalwidth\":2.8},{\"class\":1,\"petallength\":4.9,\"petalwidth\":1.5,\"sepallength\":6.3,\"sepalwidth\":2.5},{\"class\":1,\"petallength\":4.7,\"petalwidth\":1.2,\"sepallength\":6.1,\"sepalwidth\":2.8},{\"class\":1,\"petallength\":4.3,\"petalwidth\":1.3,\"sepallength\":6.4,\"sepalwidth\":2.9},{\"class\":1,\"petallength\":4.4,\"petalwidth\":1.4,\"sepallength\":6.6,\"sepalwidth\":3},{\"class\":1,\"petallength\":4.8,\"petalwidth\":1.4,\"sepallength\":6.8,\"sepalwidth\":2.8},{\"class\":1,\"petallength\":5,\"petalwidth\":1.7,\"sepallength\":6.7,\"sepalwidth\":3},{\"class\":1,\"petallength\":4.5,\"petalwidth\":1.5,\"sepallength\":6,\"sepalwidth\":2.9},{\"class\":1,\"petallength\":3.5,\"petalwidth\":1,\"sepallength\":5.7,\"sepalwidth\":2.6},{\"class\":1,\"petallength\":3.8,\"petalwidth\":1.1,\"sepallength\":5.5,\"sepalwidth\":2.4},{\"class\":1,\"petallength\":3.7,\"petalwidth\":1,\"sepallength\":5.5,\"sepalwidth\":2.4},{\"class\":1,\"petallength\":3.9,\"petalwidth\":1.2,\"sepallength\":5.8,\"sepalwidth\":2.7},{\"class\":1,\"petallength\":5.1,\"petalwidth\":1.6,\"sepallength\":6,\"sepalwidth\":2.7},{\"class\":1,\"petallength\":4.5,\"petalwidth\":1.5,\"sepallength\":5.4,\"sepalwidth\":3},{\"class\":1,\"petallength\":4.5,\"petalwidth\":1.6,\"sepallength\":6,\"sepalwidth\":3.4},{\"class\":1,\"petallength\":4.7,\"petalwidth\":1.5,\"sepallength\":6.7,\"sepalwidth\":3.1},{\"class\":1,\"petallength\":4.4,\"petalwidth\":1.3,\"sepallength\":6.3,\"sepalwidth\":2.3},{\"class\":1,\"petallength\":4.1,\"petalwidth\":1.3,\"sepallength\":5.6,\"sepalwidth\":3},{\"class\":1,\"petallength\":4,\"petalwidth\":1.3,\"sepallength\":5.5,\"sepalwidth\":2.5},{\"class\":1,\"petallength\":4.4,\"petalwidth\":1.2,\"sepallength\":5.5,\"sepalwidth\":2.6},{\"class\":1,\"petallength\":4.6,\"petalwidth\":1.4,\"sepallength\":6.1,\"sepalwidth\":3},{\"class\":1,\"petallength\":4,\"petalwidth\":1.2,\"sepallength\":5.8,\"sepalwidth\":2.6},{\"class\":1,\"petallength\":3.3,\"petalwidth\":1,\"sepallength\":5,\"sepalwidth\":2.3},{\"class\":1,\"petallength\":4.2,\"petalwidth\":1.3,\"sepallength\":5.6,\"sepalwidth\":2.7},{\"class\":1,\"petallength\":4.2,\"petalwidth\":1.2,\"sepallength\":5.7,\"sepalwidth\":3},{\"class\":1,\"petallength\":4.2,\"petalwidth\":1.3,\"sepallength\":5.7,\"sepalwidth\":2.9},{\"class\":1,\"petallength\":4.3,\"petalwidth\":1.3,\"sepallength\":6.2,\"sepalwidth\":2.9},{\"class\":1,\"petallength\":3,\"petalwidth\":1.1,\"sepallength\":5.1,\"sepalwidth\":2.5},{\"class\":1,\"petallength\":4.1,\"petalwidth\":1.3,\"sepallength\":5.7,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":6,\"petalwidth\":2.5,\"sepallength\":6.3,\"sepalwidth\":3.3},{\"class\":2,\"petallength\":5.1,\"petalwidth\":1.9,\"sepallength\":5.8,\"sepalwidth\":2.7},{\"class\":2,\"petallength\":5.9,\"petalwidth\":2.1,\"sepallength\":7.1,\"sepalwidth\":3},{\"class\":2,\"petallength\":5.6,\"petalwidth\":1.8,\"sepallength\":6.3,\"sepalwidth\":2.9},{\"class\":2,\"petallength\":5.8,\"petalwidth\":2.2,\"sepallength\":6.5,\"sepalwidth\":3},{\"class\":2,\"petallength\":6.6,\"petalwidth\":2.1,\"sepallength\":7.6,\"sepalwidth\":3},{\"class\":2,\"petallength\":4.5,\"petalwidth\":1.7,\"sepallength\":4.9,\"sepalwidth\":2.5},{\"class\":2,\"petallength\":6.3,\"petalwidth\":1.8,\"sepallength\":7.3,\"sepalwidth\":2.9},{\"class\":2,\"petallength\":5.8,\"petalwidth\":1.8,\"sepallength\":6.7,\"sepalwidth\":2.5},{\"class\":2,\"petallength\":6.1,\"petalwidth\":2.5,\"sepallength\":7.2,\"sepalwidth\":3.6},{\"class\":2,\"petallength\":5.1,\"petalwidth\":2,\"sepallength\":6.5,\"sepalwidth\":3.2},{\"class\":2,\"petallength\":5.3,\"petalwidth\":1.9,\"sepallength\":6.4,\"sepalwidth\":2.7},{\"class\":2,\"petallength\":5.5,\"petalwidth\":2.1,\"sepallength\":6.8,\"sepalwidth\":3},{\"class\":2,\"petallength\":5,\"petalwidth\":2,\"sepallength\":5.7,\"sepalwidth\":2.5},{\"class\":2,\"petallength\":5.1,\"petalwidth\":2.4,\"sepallength\":5.8,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":5.3,\"petalwidth\":2.3,\"sepallength\":6.4,\"sepalwidth\":3.2},{\"class\":2,\"petallength\":5.5,\"petalwidth\":1.8,\"sepallength\":6.5,\"sepalwidth\":3},{\"class\":2,\"petallength\":6.7,\"petalwidth\":2.2,\"sepallength\":7.7,\"sepalwidth\":3.8},{\"class\":2,\"petallength\":6.9,\"petalwidth\":2.3,\"sepallength\":7.7,\"sepalwidth\":2.6},{\"class\":2,\"petallength\":5,\"petalwidth\":1.5,\"sepallength\":6,\"sepalwidth\":2.2},{\"class\":2,\"petallength\":5.7,\"petalwidth\":2.3,\"sepallength\":6.9,\"sepalwidth\":3.2},{\"class\":2,\"petallength\":4.9,\"petalwidth\":2,\"sepallength\":5.6,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":6.7,\"petalwidth\":2,\"sepallength\":7.7,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":4.9,\"petalwidth\":1.8,\"sepallength\":6.3,\"sepalwidth\":2.7},{\"class\":2,\"petallength\":5.7,\"petalwidth\":2.1,\"sepallength\":6.7,\"sepalwidth\":3.3},{\"class\":2,\"petallength\":6,\"petalwidth\":1.8,\"sepallength\":7.2,\"sepalwidth\":3.2},{\"class\":2,\"petallength\":4.8,\"petalwidth\":1.8,\"sepallength\":6.2,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":4.9,\"petalwidth\":1.8,\"sepallength\":6.1,\"sepalwidth\":3},{\"class\":2,\"petallength\":5.6,\"petalwidth\":2.1,\"sepallength\":6.4,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":5.8,\"petalwidth\":1.6,\"sepallength\":7.2,\"sepalwidth\":3},{\"class\":2,\"petallength\":6.1,\"petalwidth\":1.9,\"sepallength\":7.4,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":6.4,\"petalwidth\":2,\"sepallength\":7.9,\"sepalwidth\":3.8},{\"class\":2,\"petallength\":5.6,\"petalwidth\":2.2,\"sepallength\":6.4,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":5.1,\"petalwidth\":1.5,\"sepallength\":6.3,\"sepalwidth\":2.8},{\"class\":2,\"petallength\":5.6,\"petalwidth\":1.4,\"sepallength\":6.1,\"sepalwidth\":2.6},{\"class\":2,\"petallength\":6.1,\"petalwidth\":2.3,\"sepallength\":7.7,\"sepalwidth\":3},{\"class\":2,\"petallength\":5.6,\"petalwidth\":2.4,\"sepallength\":6.3,\"sepalwidth\":3.4},{\"class\":2,\"petallength\":5.5,\"petalwidth\":1.8,\"sepallength\":6.4,\"sepalwidth\":3.1},{\"class\":2,\"petallength\":4.8,\"petalwidth\":1.8,\"sepallength\":6,\"sepalwidth\":3},{\"class\":2,\"petallength\":5.4,\"petalwidth\":2.1,\"sepallength\":6.9,\"sepalwidth\":3.1},{\"class\":2,\"petallength\":5.6,\"petalwidth\":2.4,\"sepallength\":6.7,\"sepalwidth\":3.1},{\"class\":2,\"petallength\":5.1,\"petalwidth\":2.3,\"sepallength\":6.9,\"sepalwidth\":3.1},{\"class\":2,\"petallength\":5.1,\"petalwidth\":1.9,\"sepallength\":5.8,\"sepalwidth\":2.7},{\"class\":2,\"petallength\":5.9,\"petalwidth\":2.3,\"sepallength\":6.8,\"sepalwidth\":3.2},{\"class\":2,\"petallength\":5.7,\"petalwidth\":2.5,\"sepallength\":6.7,\"sepalwidth\":3.3},{\"class\":2,\"petallength\":5.2,\"petalwidth\":2.3,\"sepallength\":6.7,\"sepalwidth\":3},{\"class\":2,\"petallength\":5,\"petalwidth\":1.9,\"sepallength\":6.3,\"sepalwidth\":2.5},{\"class\":2,\"petallength\":5.2,\"petalwidth\":2,\"sepallength\":6.5,\"sepalwidth\":3},{\"class\":2,\"petallength\":5.4,\"petalwidth\":2.3,\"sepallength\":6.2,\"sepalwidth\":3.4},{\"class\":2,\"petallength\":5.1,\"petalwidth\":1.8,\"sepallength\":5.9,\"sepalwidth\":3}]},\"encoding\":{\"color\":{\"field\":\"class\",\"type\":\"nominal\"},\"x\":{\"field\":\"sepallength\",\"title\":\"Sepal Length\",\"type\":\"quantitative\"},\"y\":{\"field\":\"sepalwidth\",\"title\":\"Sepal Width\",\"type\":\"quantitative\"}},\"mark\":\"point\",\"selection\":{\"selector001\":{\"bind\":\"scales\",\"encodings\":[\"x\",\"y\"],\"type\":\"interval\"}},\"title\":\"iris dataset visualization for sepallength and sepalwidth\"}}"};
        final String[] annotations = new String[]{"{\"note\":{\"title\":\"Annotation\",\"label\":\"Date: Thu Feb 09 2017 Value: 988.9475\"},\"data\":{\"date\":\"2017-02-09T00:00:00.000Z\",\"value\":988.9475,\"radius\":5},\"color\":\"green\",\"subject\":{\"x\":\"right\"}}"};
        String autofill = environment.getProperty("autofillAtStartup");
        if (autofill != null && autofill.equals("true")) {

            Author account = new Author("111");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode vegaSchema = mapper.readTree(visualizations[0]);
            JsonNode annotationProperties = mapper.readTree(annotations[0]);
            VegaVisualization visualization = new VegaVisualization( vegaSchema);
            visualization.setDescription("Example Vega-Lite Visualization #1");
            VegaVisualization externalVegaVisualization = new VegaVisualization( "1", "http://localhost:3000/visualiaztions/1");
            externalVegaVisualization.setDescription("External Vega Visualization");
            externalVegaVisualization.setHash("dafdaa588d418dd2cd81271472c55832f2e29306105e00682a3a88cfb2071ffa");
            List<VegaPointAnnotation> annotationsList = new LinkedList<>();
            List<Question> questionList = new LinkedList<>();
            VegaPointAnnotation annotation = new VegaPointAnnotation("c0382b", annotationProperties.get("note"), annotationProperties.get("data"), annotationProperties.get("subject"));
            annotationsList.add(annotation);
            Question question = new Question(mapper.readTree("{\n" +
                    "  \"type\": \"doc\",\n" +
                    "  \"content\": [\n" +
                    "    {\n" +
                    "      \"type\": \"paragraph\",\n" +
                    "      \"content\": [\n" +
                    "        {\n" +
                    "          \"type\": \"text\",\n" +
                    "          \"text\": \"This is some inserted text. \uD83D\uDC4B\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}"), account, annotationsList, new LinkedList<>(), "Test title", new LinkedList<>(), null, annotation.getColor());
            this.accountRepository.saveAndFlush(account);
            this.visualizationRepository.saveAndFlush(visualization);
            this.visualizationRepository.save(externalVegaVisualization);
            Discussion discussion = new Discussion(account, "Test Discussion", questionList, visualization);

            Discussion discussionExternal = new Discussion(account, "External Discussion", questionList, externalVegaVisualization);



            this.questionRepository.saveAndFlush(question);

            this.discussionRepository.saveAndFlush(discussion);
            this.discussionRepository.saveAndFlush(discussionExternal);

        }
    }
}
