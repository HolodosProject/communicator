package tk.laurenfrost.communicator.alexeyapi;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.util.List;

public class AlexeyApiClass {

    public static void processSpeech(String boardName, byte[] speechBytes) {
        System.out.println("Message from "+boardName);
        try (SpeechClient client = SpeechClient.create()) {
            ByteString audioBytes = ByteString.copyFrom(speechBytes);
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setLanguageCode("ru-RU")
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();
            RecognizeResponse response = client.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();
            for (SpeechRecognitionResult result : results) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
            error.printStackTrace();
        }

    }

}
