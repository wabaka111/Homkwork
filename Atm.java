import java.util.Scanner;
public class Atm {
    public static void main(String[] args) {
        double balance = 1000.0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== ATM 菜单 =====");
            System.out.println("1. 查询余额");
            System.out.println("2. 取款");
            System.out.println("3. 存款");
            System.out.println("0. 退出");
            System.out.print("请选择操作：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("当前余额：" + balance);
                    break;
                case 2:
                    System.out.print("请输入取款金额：");
                    double withdraw = scanner.nextDouble();
                   if (withdraw <= 0) {
                        System.out.println("错误：金额必须大于 0！");
                    } else if (withdraw % 100 != 0) {
                        System.out.println("错误：金额必须是 100 的倍数！");
                    } else if (withdraw > balance) {
                        System.out.println("错误：余额不足，当前可用：" + balance);
                    } else {
                        balance -= withdraw;
                        System.out.println("取款成功！新余额：" + balance);
                    }
                    break;
                case 3:
                    System.out.print("请输入存款金额：");
                    double deposit = scanner.nextDouble();
                 if (deposit <= 0) {
                        System.out.println("错误：金额必须大于 0！");
                    } else if (deposit % 100 != 0) {
                        System.out.println("错误：金额必须是 100 的倍数！");
                    } else {
                        balance += deposit;
                        System.out.println("存款成功！新余额：" + balance);
                    }
                    break;
                case 0:
                    System.out.println("感谢使用ATM，再见！");
                    scanner.close();
                    return;
                default:
                    System.out.println("错误：请输入 0-3 之间的数字！");
            }
        }
    }
}