import java.util.NoSuchElementException;
import java.util.Scanner;

public class PostfixToInfix {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the postfix expression..: ");
        String post = sc.nextLine();

        char c = 0;
        boolean isPostfix = true;
        int operands = 0;
        int operators = 0;

        for (int i = 0; i < post.length(); i++) {
            c = post.charAt(i);
            if ((c != '+') && (c != '-') && (c != '/') && (c != '*') && !(c >= '0' && c <= '9')) {
                isPostfix = false;
                break;
            }
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operators++;
            } else {
                operands++;
            }
        }

        if (isPostfix) {
            //
            if ((c != '+') && (c != '-') && (c != '/') && (c != '*')) {
                isPostfix = false;
            }
            if (!(operators == operands - 1)) {
                isPostfix = false;
            }
        }

        if (isPostfix == false) {
            System.out.println("Sorry, invalid postfix expression");
        } else {
            StringDoubleEndedQueueImpl<String> s = new StringDoubleEndedQueueImpl<String>();
            String new_operand="";

            try {
                for (int i = 0; i < post.length(); i++) {
                    c = post.charAt(i);
                    if (c >= '0' && c <= '9') {
                        s.addFirst(String.valueOf(c));
                    } else {
                        String last = s.removeFirst();
                        String first = s.removeFirst();
                        new_operand = '(' + first + c + last + ')';
                        s.addFirst(new_operand);
                    }
                }
				
				System.out.print("Infix expression: ");
                System.out.print(new_operand);

            } catch (NoSuchElementException e) {
                System.err.println("Sorry, invalid postfix expression");
            }
        }
    }
}
