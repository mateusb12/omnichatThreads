package mocks;

import classes.ConversationStage;

public class ConversationFlow {
    public static ConversationStage[] conversationFlow1 = new ConversationStage[]{
            new ConversationStage("_hello", "Oii", "Parece que você não está cadastrado no nosso sistema, e vou precisar fazer o seu cadastro. Por favor, me informe o seu nome."),
            new ConversationStage("_name", "Clark Kent", "Qual o seu endereço?"),
            new ConversationStage("_address", "Avenida do Solitario 2845", "Qual o seu CPF?"),
            new ConversationStage("_cpf", "12345678910", "Usuário cadastrado com sucesso!, digite 'ok' para continuar"),
            new ConversationStage("_welcome", "ok", "Olá! Bem-vindo à Pizza do Bill! Funcionamos das 17h às 22h.\n Cardápio de pizzas:\n- Calabresa - R$17.50\n- Frango - R$18.90\n- Portuguesa - R$13.99\n- Margherita - R$15.50\n- Quatro Queijos - R$16.90\n- Pepperoni - R$19.99\n. \nQual pizza você vai querer?"),
            new ConversationStage("_pizzaChoice", "Vou querer uma pizza meia calabresa meia margherita e uma pizza de frango", "Maravilha! Meia calabresa margherita, uma inteira frango então. Você vai querer alguma bebida?"),
            new ConversationStage("_drinkConfirmation", "Sim", "Cardápio de bebidas: - Coca-cola - R$5.99 - Guaraná - R$4.99 - Suco de laranja - R$6.50"),
            new ConversationStage("_drinkChoice", "Vou querer um guaraná e dois sucos de laranja", "Vai ser: - 1 x Pizza meio calabresa meio margherita (R$16.50) - 1 x Pizza de frango (R$18.90) - 1 x Guaraná (R$4.99) - 2 x Suco de laranja (R$13.00) - Total → [R$53.39] Qual vai ser a forma de pagamento? (pix/cartão/dinheiro)"),
            new ConversationStage("_paymentChoice", "Pix", "Tudo certo então! O pedido estará indo para a sua casa em breve!")
    };
    public static ConversationStage[] conversationFlow2 = new ConversationStage[]{
            new ConversationStage("_hello", "Oii", "Parece que você não está cadastrado no nosso sistema, e vou precisar fazer o seu cadastro. Por favor, me informe o seu nome."),
            new ConversationStage("_name", "Bruce Wayne", "Qual o seu endereço?"),
            new ConversationStage("_address", "Alameda dos órfãos 1283", "Qual o seu CPF?"),
            new ConversationStage("_cpf", "04551455392", "Usuário cadastrado com sucesso!, digite 'ok' para continuar"),
            new ConversationStage("_welcome", "ok", "Olá! Bem-vindo à Pizza do Bill! Funcionamos das 17h às 22h.\n Cardápio de pizzas:\n- Calabresa - R$17.50\n- Frango - R$18.90\n- Portuguesa - R$13.99\n- Margherita - R$15.50\n- Quatro Queijos - R$16.90\n- Pepperoni - R$19.99\n. \nQual pizza você vai querer?"),
            new ConversationStage("_pizzaChoice", "Vou querer uma pizza meia calabresa meia margherita e uma pizza de frango", "Maravilha! Meia calabresa margherita, uma inteira frango então. Você vai querer alguma bebida?"),
            new ConversationStage("_drinkConfirmation", "Sim", "Cardápio de bebidas: - Coca-cola - R$5.99 - Guaraná - R$4.99 - Suco de laranja - R$6.50"),
            new ConversationStage("_drinkChoice", "Vou querer um guaraná e dois sucos de laranja", "Vai ser: - 1 x Pizza meio calabresa meio margherita (R$16.50) - 1 x Pizza de frango (R$18.90) - 1 x Guaraná (R$4.99) - 2 x Suco de laranja (R$13.00) - Total → [R$53.39] Qual vai ser a forma de pagamento? (pix/cartão/dinheiro)"),
            new ConversationStage("_paymentChoice", "Pix", "Tudo certo então! O pedido estará indo para a sua casa em breve!")
    };
    public static ConversationStage[] conversationFlow3 = new ConversationStage[]{
            new ConversationStage("_hello", "Oii", "Parece que você não está cadastrado no nosso sistema, e vou precisar fazer o seu cadastro. Por favor, me informe o seu nome."),
            new ConversationStage("_name", "Barry Allen", "Qual o seu endereço?"),
            new ConversationStage("_address", "Rua dos Precoce 2323", "Qual o seu CPF?"),
            new ConversationStage("_cpf", "06451342387", "Usuário cadastrado com sucesso!, digite 'ok' para continuar"),
            new ConversationStage("_welcome", "ok", "Olá! Bem-vindo à Pizza do Bill! Funcionamos das 17h às 22h.\n Cardápio de pizzas:\n- Calabresa - R$17.50\n- Frango - R$18.90\n- Portuguesa - R$13.99\n- Margherita - R$15.50\n- Quatro Queijos - R$16.90\n- Pepperoni - R$19.99\n. \nQual pizza você vai querer?"),
            new ConversationStage("_pizzaChoice", "Vou querer uma pizza meia calabresa meia margherita e uma pizza de frango", "Maravilha! Meia calabresa margherita, uma inteira frango então. Você vai querer alguma bebida?"),
            new ConversationStage("_drinkConfirmation", "Sim", "Cardápio de bebidas: - Coca-cola - R$5.99 - Guaraná - R$4.99 - Suco de laranja - R$6.50"),
            new ConversationStage("_drinkChoice", "Vou querer um guaraná e dois sucos de laranja", "Vai ser: - 1 x Pizza meio calabresa meio margherita (R$16.50) - 1 x Pizza de frango (R$18.90) - 1 x Guaraná (R$4.99) - 2 x Suco de laranja (R$13.00) - Total → [R$53.39] Qual vai ser a forma de pagamento? (pix/cartão/dinheiro)"),
            new ConversationStage("_paymentChoice", "Pix", "Tudo certo então! O pedido estará indo para a sua casa em breve!")
    };
}
