
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadConversationTest {
    private static final String SELECTED_URL = "http://localhost:8080/twilioSandbox";


    public static void main(String[] args) {

        HashMap<String, String> headers1 = new HashMap<>();
        headers1.put("CustomIp", utils.generateRandomIp());

        HashMap<String, String> headers2 = new HashMap<>();
        headers1.put("CustomIp", utils.generateRandomIp());

        HashMap<String, String> headers3 = new HashMap<>();
        headers1.put("CustomIp", utils.generateRandomIp());
            // Definindo três diferentes fluxos de conversa
            ConversationStage[] conversationFlow1 = {
                    // ... estágios de conversação para o fluxo 1 ...
            };
            ConversationStage[] conversationFlow2 = {
                    // ... estágios de conversação para o fluxo 2 ...
            };
            ConversationStage[] conversationFlow3 = {
                    // ... estágios de conversação para o fluxo 3 ...
            };

            // Criar um ExecutorService com 3 threads
            ExecutorService executor = Executors.newFixedThreadPool(3);

            // Executar cada fluxo de conversa em uma thread separada
            executor.submit(() -> runConversationTest(conversationFlow1, headers1));
            executor.submit(() -> runConversationTest(conversationFlow2, headers2));
            executor.submit(() -> runConversationTest(conversationFlow3, headers3));

            // Fechar o ExecutorService
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }

    private static void runConversationTest(ConversationStage[] stages, HashMap<String, String> headers) {
        for (ConversationStage stage : stages) {
            try {
                String output = BackendHttpRequest.getBotResponseFromFlask(stage.getInput(), SELECTED_URL, headers);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    }

