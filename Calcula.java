import java.util.*;

public class Calcula {

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite a expressão: ");
    String expressao = scanner.nextLine();
    System.out.println("Digite a notação (i para infixa, p para pós-fixa, pr para pré-fixa): ");
    String notacao = scanner.nextLine();

    String posfixa = "";
    String preFixa = "";

    switch (notacao) {
        case "i":
            posfixa = converterParaPosFixa(expressao);
            preFixa = converterParaPreFixa(expressao);
            break;
        case "p":
            posfixa = expressao;
            break;
        case "pr":
            posfixa = converterParaPosFixa(expressao);
            preFixa = expressao;
            break;
        default:
            System.out.println("Notação inválida");
            return;
    }

    double resultadoPosFixa = avaliarPosFixa(posfixa);
    double resultadoPreFixa = avaliarPreFixa(preFixa);

    exibirExpressao(expressao, notacao);
    exibirExpressao(posfixa, "p");
    exibirExpressao(preFixa, "pr");
    System.out.println("Resultado em notação pós-fixa: " + resultadoPosFixa);
    System.out.println("Resultado em notação pré-fixa: " + resultadoPreFixa);
}

public static String converterParaPosFixa(String expressao) {
    //O método converterParaPosFixa implementa a conversão de uma expressão aritmética na notação infixa para posfixa, utilizando uma pilha (Stack) para armazenar operadores temporariamente.
    Stack<Character> operadores = new Stack<>();
    StringBuilder posfixa = new StringBuilder();
    for (int i = 0; i < expressao.length(); i++) {
        char c = expressao.charAt(i);
        if (Character.isDigit(c)) {
            posfixa.append(c);
        } else if (Character.isLetter(c)) {
            posfixa.append(c);
            // Você pode adicionar mais casos para outros tipos de operandos aqui,
            // como variáveis ou funções
        } else if (c == '(') {
            operadores.push(c);
        } else if (c == ')') {
            while (!operadores.isEmpty() && operadores.peek() != '(') {
                posfixa.append(operadores.pop());
            }
            if (!operadores.isEmpty() && operadores.peek() == '(') {
                operadores.pop();
            } else {
                System.out.println("Expressão inválida");
                return "";
            }
        } else if (isOperator(c)) {
            while (!operadores.isEmpty() && operadores.peek() != '(' && precedence(c) <= precedence(operadores.peek())) {
                posfixa.append(operadores.pop());
            }
            operadores.push(c);
        } else {
            System.out.println("Caractere inválido: " + c);
            return "";
        }
    }
    while (!operadores.isEmpty()) {
        if (operadores.peek() == '(') {
            System.out.println("Expressão inválida");
            return "";
        }
        posfixa.append(operadores.pop());
    }
    return posfixa.toString();
}

public static String converterParaPreFixa(String expressao) {
    // Implemente a conversão para pré-fixa aqui
    //O método converterParaPreFixa está vazio e precisa ser implementado para realizar a conversão de uma expressão infixa para prefixa.
    return "";
}

public static double avaliarPosFixa(String expressao) {
    //O método avaliarPosFixa avalia uma expressão aritmética na notação posfixa, utilizando uma pilha (Stack) para armazenar operandos temporariamente.
    Stack<Double> operandos = new Stack<>();
    for (int i = 0; i < expressao.length(); i++) {
        char c = expressao.charAt(i);
        if (Character.isDigit(c)) {
            operandos.push(Double.parseDouble(String.valueOf(c)));
        }
        else if (isOperator(c)) {
            double operand2 = operandos.pop();
            double operand1 = operandos.pop();
            double resultado = aplicarOperacao(operand1, operand2, c);
            operandos.push(resultado);
        } else {
            System.out.println("Operador inválido: " + c);
            return 0;
        }
    }
    return operandos.pop();
}

        public static double avaliarPreFixa(String expressao) {
            //O método avaliarPreFixa está vazio e precisa ser implementado para avaliar uma expressão aritmética na notação prefixa.
        // Implemente a avaliação de pré-fixa aqui
        return 0;
    }   

        public static void exibirExpressao(String expressao, String notacao) {
            //O método exibirExpressao exibe uma expressão e sua notação (posfixa ou prefixa) no console.
        System.out.print("Expressão em notação " + notacao + ": ");
        System.out.println(expressao);
    }

    public static boolean isOperator(char c) {
        //O método isOperator verifica se um caractere é um operador aritmético (+, -, * ou /).
        return c == '+' || c == '-' || c == '*' || c == '/';    
    }

        public static int precedence(char c) {
            //O método precedence retorna a precedência de um operador aritmético (1 para + e -, 2 para * e /, e 0 para outros caracteres).
        switch (c) {
                case '+':
                    case '-':
                        return 1;
                            case '*':
                                case '/':
                                    return 2;
                                        default:
                                                return 0;
                                            }
        }

        public static double aplicarOperacao(double operand1, double operand2, char operator) {
            //O método aplicarOperacao aplica uma operação aritmética (+, -, * ou /) a dois operandos e retorna o resultado.
            switch (operator) {
                case '+':
                    return operand1 + operand2;
                            case '-':
                            return operand1 - operand2;
                                    case '*':
                                        return operand1 * operand2;
                                                case '/':
                                                return operand1 / operand2;
                                                    default:
                                                        return 0;
                }
        }
}    
