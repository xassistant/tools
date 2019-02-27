package com.stack;

/**
 * @Date 2019/2/25 19:52
 */
public class CalculateExpression {
    /**
     * 中缀转后缀
     *
     * @param expstr 中缀表达式字符串
     * @return
     */
    public static String srcToPostFix(String expstr) {
        //创建栈,用于存储运算符
        SeqStack<String> stack = new SeqStack<>(expstr.length());

        String postfix = "";//存储后缀表达式的字符串
        int i = 0;
        while (i < expstr.length()) {
            char ch = expstr.charAt(i);
            switch (ch) {
                case '+':
                case '-':
                    //当栈不为空或者栈顶元素不是左括号时,直接出栈,因此此时只有可能是*/+-四种运算符(根据规则4),否则入栈
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        postfix += stack.pop();
                    }
                    //入栈
                    stack.push(ch + "");
                    i++;
                    break;
                case '*':
                case '/':
                    //遇到运算符*/
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                        postfix += stack.pop();
                    }
                    stack.push(ch + "");
                    i++;
                    break;
                case '(':
                    //左括号直接入栈
                    stack.push(ch + "");
                    i++;
                    break;
                case ')':
                    //遇到右括号(规则3)
                    String out = stack.pop();
                    while (out != null && !out.equals("(")) {
                        postfix += out;
                        out = stack.pop();
                    }
                    i++;
                    break;
                default:
                    //操作数直接入栈
                    while (ch >= '0' && ch <= '9') {
                        postfix += ch;
                        i++;
                        if (i < expstr.length()) ch = expstr.charAt(i);
                        else ch = '=';
                    }
                    //分隔符
                    postfix += " ";
                    break;
            }
        }
        //最后把所有运算符出栈(规则5)
        while (!stack.isEmpty()) postfix += stack.pop();
        return postfix;
    }

    public static String toPostfix(String expstr) {
        int i = 0;
        int length = expstr.length();
        StringBuilder exp = new StringBuilder();
        SeqStack<Character> seqStack = new SeqStack<>(length);
        String bound = "(";
        while (i < length) {
            char c = expstr.charAt(i++);
            switch (c) {
                case '+':
                case '-':
                    seqStack.push(c);
                    break;
                case '*':
                case '/':
                    seqStack.push(c);
                    break;
                case '(':
                    seqStack.push(c);
                    break;
                case ')': {
                    String pop = seqStack.pop().toString();
                    while (!pop.equals(bound)) {
                        exp.append(pop);
                        pop = seqStack.pop().toString();
                    }
                    break;
                }
                default:
                    if (c >= '0' && c <= '9') {
                        exp.append(c);
                    }
                    break;
            }
        }
        return exp.toString();
    }

    public static void main(String[] args) {
        String expstr = "4+7-1+3*(9-2)+90";
        String postfix = srcToPostFix(expstr);
        System.out.println("中缀表达式->expstr=  " + expstr);
        System.out.println("后缀表达式->postfix= " + postfix);
    }
}
