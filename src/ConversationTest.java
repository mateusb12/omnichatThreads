import java.util.Collections;

public class ConversationTest {
    public static void main(String[] args) {
        final String SELECTED_URL = "http://localhost:3000/twilioSandbox";
        final int COLUMN_WIDTH = 70;

        ConversationStage[] conversationStages = new ConversationStage[] {
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

        System.out.printf("%-15s %-" + COLUMN_WIDTH + "s %-" + COLUMN_WIDTH + "s %-" + COLUMN_WIDTH + "s %-10s%n",
                "STAGE", "INPUT", "EXPECTED OUTPUT", "ACTUAL OUTPUT", "TEST RESULT");
        System.out.println(String.join("", Collections.nCopies(175, "-")));

        for (ConversationStage stage : conversationStages) {
            try {
                String output = BackendHttpRequest.getBotResponseFromFlask(stage.getInput(), SELECTED_URL);
                String resultEmoji = output.equals(stage.getExpectedOutput()) ? "✅" : "❌";
                String truncatedInput = truncateString(stage.getInput(), COLUMN_WIDTH);
                String truncatedExpected = truncateString(stage.getExpectedOutput(), COLUMN_WIDTH);
                String truncatedOutput = truncateString(output, COLUMN_WIDTH);
                System.out.printf("%-15s %-" + COLUMN_WIDTH + "s %-" + COLUMN_WIDTH + "s %-" + COLUMN_WIDTH + "s %-10s%n",
                        stage.getStage(), truncatedInput, truncatedExpected, truncatedOutput, resultEmoji);
            } catch (Exception e) {
                System.err.println("Error at stage " + stage.getStage());
                e.printStackTrace();
            }
        }
    }

    private static String truncateString(String str, int width) {
        if (str.length() <= width) {
            return str;
        } else {
            return str.substring(0, width - 3) + "..."; // Truncate and add ellipsis
        }
    }
}
