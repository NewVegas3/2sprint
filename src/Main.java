import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ReportMonthly reportMonthly = null;
        ReportYear reportYear = null;
        Checker checker;

        while (true) {
            System.out.println("Пожалуйста выберете что Вы хотите сделать: ");
            printMenu();
            int command = scanner.nextInt();

            switch (command) {
                case 1:
                    reportMonthly = new ReportMonthly();
                    for (int i = 1; i < 4; i++) {
                        String path = "resources/m.20210" + i + ".csv";
                        reportMonthly.loadFile(i, path);
                    }
                    break;
                case 2:
                    reportYear = new ReportYear("resources/y.2021.csv");
                    break;
                case 3:
                    if (reportMonthly == null || reportYear == null) {
                        System.out.println("Вы еще не считали месячный отчет или годовой отчет");
                    } else {
                        checker = new Checker(reportMonthly, reportYear);
                        System.out.println("Резултат сверки данных " + checker.cheсkYandM());
                    }
                    break;
                case 4:
                    if (reportMonthly == null) {
                        System.out.println("Вы еще не считали месячный отчет");
                    } else {
                        for (int i = 1; i < 4; i++) {
                            reportMonthly.printStatisticMonths(i);
                        }
                    }
                    break;
                case 5:
                    if (reportYear == null) {
                        System.out.println("Вы еще не считали годовой отчет");
                    } else {
                        reportYear.printStatisticalForMonths();
                    }
                    break;
                case 0:
                    return;

                default:
                    System.out.println("Такой комманды нет");
                    break;
            }

        }
    }

    static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Показать информацию обо всех месячных отчётах");
        System.out.println("5 - информацию о годовом отчёте ");
        System.out.println("Если Вы хотите выйти введите: 0");
    }
}

