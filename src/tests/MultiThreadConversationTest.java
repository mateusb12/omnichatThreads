package tests;

import common.utils;
import mocks.ConversationFlow;

import mocks.ReqPropertiesMocks;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadConversationTest {

    public static void main(String[] args) {
        String csvFileName = "conversation_log";
        HashMap<String, String> headers1 = new HashMap<>();
        headers1.put("CustomIp", utils.generateRandomIp());

        HashMap<String, String> headers2 = new HashMap<>();
        headers2.put("CustomIp", utils.generateRandomIp()); // Corrigido para headers2

        HashMap<String, String> headers3 = new HashMap<>();
        headers3.put("CustomIp", utils.generateRandomIp()); // Corrigido para headers3

        // Definindo três diferentes fluxos de conversa


            // Criar um ExecutorService com 3 threads
            ExecutorService executor = Executors.newFixedThreadPool(3);

            // Executar cada fluxo de conversa em uma thread separada
            executor.submit(() -> ConversationTest.RunConversationFlow(ConversationFlow.conversationFlow1, headers1, csvFileName + "1", ReqPropertiesMocks.reqProperties1));
            executor.submit(() -> ConversationTest.RunConversationFlow(ConversationFlow.conversationFlow2, headers2, csvFileName + "2", ReqPropertiesMocks.reqProperties2));
            executor.submit(() -> ConversationTest.RunConversationFlow(ConversationFlow.conversationFlow3, headers3, csvFileName + "3", ReqPropertiesMocks.reqProperties3));

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




    }

