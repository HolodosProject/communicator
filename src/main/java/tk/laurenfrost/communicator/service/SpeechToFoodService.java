package tk.laurenfrost.communicator.service;

import com.google.cloud.language.v1.*;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpeechToFoodService {
    public final SpeechClient speechClient;
    public final LanguageServiceClient nlpClient;

    Logger logger = LoggerFactory.getLogger(SpeechToFoodService.class);

    public List<String> speechToFoodNames(byte[] speech) {
        logger.debug("Received a speech recording to interpret");
        String text = this.speechToText(speech);
        if (!text.equals(""))
            return this.textToFoodNames(text);
        else
            return new ArrayList<>();

    }

    private List<String> textToFoodNames(String text) {
        Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
        AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16)
                .build();

        AnalyzeEntitiesResponse response = nlpClient.analyzeEntities(request);


        List<String> foodNames = response.getEntitiesList().stream()
                .filter(entity -> entity.getType().equals(Entity.Type.CONSUMER_GOOD))
                .map(entity -> {
                    if (entity.getSalience() < 0.1) {
                        logger.debug("Omitted non-salient consumer goods: " + entity.getName());
                        return null;
                    } else {
                        return entity;
                    }
                })
                .filter(Objects::nonNull)
                .map(Entity::getName)
                .distinct()
                .collect(Collectors.toList());

        if (!foodNames.isEmpty())
            logger.debug("Found consumer goods in the recording: " + foodNames);
        else
            logger.debug("The recording contained no consumer goods mentions");
        return foodNames;
    }

    private String speechToText(byte[] speech) {
        ByteString audioBytes = ByteString.copyFrom(speech);

        RecognitionConfig config =
                RecognitionConfig.newBuilder()
                        .setEncoding(RecognitionConfig.AudioEncoding.FLAC)
                        .setLanguageCode("ru-RU")
                        .setSampleRateHertz(48000)
                        .build();
        RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();

        RecognizeResponse response = speechClient.recognize(config, audio);
        List<SpeechRecognitionResult> results = response.getResultsList();

        for (SpeechRecognitionResult result : results) {
            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
            logger.debug("The recording can be interpreted as: " + alternative.getTranscript());
        }
        if (!results.isEmpty()) {
            String text = results.get(0).getAlternativesList().get(0).getTranscript();
            logger.debug("Decided to interpret as: " + text);
            return text;
        } else {
            logger.debug("Didn't find any text in the recording");
            return "";
        }
    }
}
