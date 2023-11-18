import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class ConversationTest {
    final static int COLUMN_WIDTH = 70;

    public static void main(String[] args) {
        final String SELECTED_URL = "http://localhost:8080/twilioSandbox";

        String csvFile = "conversation_log.csv";
        String lineSeparator = System.getProperty("line.separator");

        ConversationStage[] conversationStages = new ConversationStage[]{
                new ConversationStage("_hello", "Oii", "Parece que você não está cadastrado no nosso sistema, e vou precisar fazer o seu cadastro. Por favor, me informe o seu nome."),
                new ConversationStage("_name", "Clark Kent", "Qual o seu endereço?"),
                new ConversationStage("_address", "Avenida da Paz 2845", "Qual o seu CPF?"),
                new ConversationStage("_cpf", "12345678910", "Usuário cadastrado com sucesso!, digite 'ok' para continuar"),
                new ConversationStage("_welcome", "ok", "Olá! Bem-vindo à Pizza do Bill! Funcionamos das 17h às 22h.\n Cardápio de pizzas:\n- Calabresa - R$17.50\n- Frango - R$18.90\n- Portuguesa - R$13.99\n- Margherita - R$15.50\n- Quatro Queijos - R$16.90\n- Pepperoni - R$19.99\n. \nQual pizza você vai querer?"),
                new ConversationStage("_pizzaChoice", "Vou querer uma pizza meia calabresa meia margherita e uma pizza de frango", "Maravilha! Meia calabresa margherita, uma inteira frango então. Você vai querer alguma bebida?"),
                new ConversationStage("_drinkConfirmation", "Sim", "Cardápio de bebidas: - Coca-cola - R$5.99 - Guaraná - R$4.99 - Suco de laranja - R$6.50"),
                new ConversationStage("_drinkChoice", "Vou querer um guaraná e dois sucos de laranja", "Vai ser: - 1 x Pizza meio calabresa meio margherita (R$16.50) - 1 x Pizza de frango (R$18.90) - 1 x Guaraná (R$4.99) - 2 x Suco de laranja (R$13.00) - Total → [R$53.39] Qual vai ser a forma de pagamento? (pix/cartão/dinheiro)"),
                new ConversationStage("_paymentChoice", "Pix", "Tudo certo então! O pedido estará indo para a sua casa em breve!")
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            // Cabeçalhos do CSV
            bw.write(String.join(",", "STAGE", "INPUT", "EXPECTED OUTPUT", "ACTUAL OUTPUT", "TEST RESULT"));
            bw.write(lineSeparator);

            for (ConversationStage stage : conversationStages) {
                try {
                    String output = BackendHttpRequest.getBotResponseFromFlask(stage.getInput(), SELECTED_URL);
                    String resultEmoji = output.equals(stage.getExpectedOutput()) ? "✅" : "❌";
                    String truncatedInput = truncateString(stage.getInput());
                    String truncatedExpected = truncateString(stage.getExpectedOutput());
                    String truncatedOutput = truncateString(output);

                    // Escrevendo no CSV
                    bw.write(String.join(",",
                            stage.getStage(),
                            "\"" + truncatedInput + "\"",
                            "\"" + truncatedExpected + "\"",
                            "\"" + truncatedOutput + "\"",
                            resultEmoji
                    ));
                    bw.write(lineSeparator);

                } catch (Exception e) {
                    System.err.println("Error at stage " + stage.getStage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String truncateString(String str) {
        if (str.length() <= COLUMN_WIDTH) {
            return str;
        } else {
            // Encontrar o último espaço antes do limite para evitar quebrar palavras
            int lastSpaceIndex = str.lastIndexOf(' ', COLUMN_WIDTH - 1);
            if (lastSpaceIndex == -1) {
                lastSpaceIndex = COLUMN_WIDTH - 1; // Se não houver espaço, quebre no limite
            }
            return str.substring(0, lastSpaceIndex) + "\n" + str.substring(lastSpaceIndex).trim();
        }
    }
}
