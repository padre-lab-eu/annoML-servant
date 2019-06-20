package org.annoml.servant.SpringAnnoMLServant.init;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Answer;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Discussion;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.VegaVisualization;
import org.annoml.servant.SpringAnnoMLServant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.management.Query;
import java.util.LinkedList;
import java.util.List;

/**
 * Initializes the system with several questions, answers and users in case the corresponding property is set.
 */
@Component
@PropertySource("classpath:config.properties")
public class DBInitializer implements ApplicationRunner {
    public static final int INITIAL_USERS = 5;
    private static final int INITIAL_QUESTIONS = 9;
    private static final int INITIAL_ANSWERS = 2;
    private static final int INITAL_VISUALIZATIONS = 2;

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
        final String[] questions = new String[]{"What's the meaning of life?", "What am I doing here?",
                "Do emails get delivered on Sundays?"};
        final String[] answers = new String[]{"The answer ist: 42", "IIRC 25",
                "Lorem ipsum dolor sit amet, consectetur, adipisci velit, …", "That's a good question!"};
        final String[] visualizations = new String[]{"{\"type\":\"vega-lite\",\"spec\":{\"$schema\":\"https://vega.github.io/schema/vega-lite/v3.json\",\"data\":{\"name\":\"data\",\"values\":[{\"date\":\"2017-01-01T00:00:00.000Z\",\"value\":997.6888},{\"date\":\"2017-01-02T00:00:00.000Z\",\"value\":1018.05},{\"date\":\"2017-01-03T00:00:00.000Z\",\"value\":1030.8175},{\"date\":\"2017-01-04T00:00:00.000Z\",\"value\":1129.87},{\"date\":\"2017-02-09T00:00:00.000Z\",\"value\":988.9475},{\"date\":\"2017-02-14T00:00:00.000Z\",\"value\":1009.2513},{\"date\":\"2017-02-15T00:00:00.000Z\",\"value\":1009.1163},{\"date\":\"2017-02-16T00:00:00.000Z\",\"value\":1034.0825},{\"date\":\"2017-02-17T00:00:00.000Z\",\"value\":1053.1225},{\"date\":\"2017-02-21T00:00:00.000Z\",\"value\":1123.6563},{\"date\":\"2017-02-22T00:00:00.000Z\",\"value\":1122.195},{\"date\":\"2017-02-23T00:00:00.000Z\",\"value\":1178.384},{\"date\":\"2017-02-24T00:00:00.000Z\",\"value\":1180.918},{\"date\":\"2017-02-25T00:00:00.000Z\",\"value\":1151.581},{\"date\":\"2017-02-26T00:00:00.000Z\",\"value\":1179.968},{\"date\":\"2017-02-27T00:00:00.000Z\",\"value\":1194.278},{\"date\":\"2017-02-28T00:00:00.000Z\",\"value\":1190.888},{\"date\":\"2017-03-01T00:00:00.000Z\",\"value\":1230.016},{\"date\":\"2017-03-05T00:00:00.000Z\",\"value\":1277.685},{\"date\":\"2017-03-16T00:00:00.000Z\",\"value\":1172.909},{\"date\":\"2017-03-17T00:00:00.000Z\",\"value\":1070.128},{\"date\":\"2017-03-18T00:00:00.000Z\",\"value\":970.598},{\"date\":\"2017-03-19T00:00:00.000Z\",\"value\":1017.8},{\"date\":\"2017-03-20T00:00:00.000Z\",\"value\":1041.343},{\"date\":\"2017-03-21T00:00:00.000Z\",\"value\":1115.039},{\"date\":\"2017-03-22T00:00:00.000Z\",\"value\":1037.44},{\"date\":\"2017-03-27T00:00:00.000Z\",\"value\":1040.491}]},\"width\":1200,\"height\":800,\"layer\":[{\"mark\":{\"type\":\"line\",\"point\":true},\"encoding\":{\"x\":{\"field\":\"date\",\"type\":\"temporal\"},\"y\":{\"field\":\"value\",\"type\":\"quantitative\"},\"color\":{\"value\":\"#3395ff\"}}}]}}"};
        final String[] annotations = new String[]{"{\"note\":{\"title\":\"Annotation\",\"label\":\"Date: Thu Feb 09 2017 Value: 988.9475\"},\"data\":{\"date\":\"2017-02-09T00:00:00.000Z\",\"value\":988.9475,\"radius\":5},\"color\":\"green\",\"subject\":{\"x\":\"right\"}}"};
        String autofill = environment.getProperty("autofillAtStartup");
        if (autofill != null && autofill.equals("true")) {

            for (int i = 0; i < INITIAL_USERS; i++) {
                Author account = new Author("user" + i, "pw");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode vegaSchema = mapper.readTree(visualizations[0]);
                JsonNode annotationProperties = mapper.readTree(annotations[0]);
                VegaVisualization visualization = new VegaVisualization(account, "Example Vega-Lite Visualization #" + i+1, vegaSchema);
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
                questionList.add(question);
                this.accountRepository.saveAndFlush(account);
                this.visualizationRepository.saveAndFlush(visualization);
                Discussion discussion = new Discussion(account, "Test Discussion", questionList, visualization);



                this.questionRepository.saveAndFlush(question);

                this.discussionRepository.saveAndFlush(discussion);


            }
/*
            for (int i = 0; i < INITIAL_QUESTIONS; i++) {
                Question question = new Question("questiontitle " + i, questions[i % questions.length]);
                if (i % 3 == 0) {
                    for (int j = 0; j < INITIAL_ANSWERS; j++) {
                        Answer answer = new Answer(answers[j % answers.length]);
                        question.addAnswer(answer);
                        this.answerRepository.saveAndFlush(answer);
                        if (i % 2 == 0 && j == (INITIAL_ANSWERS - 1)) {
                            question.setFavorite(answer);
                        }
                    }
                }
                this.questionRepository.saveAndFlush(question);
            }
 */
        }
    }
}
