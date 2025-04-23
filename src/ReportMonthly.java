import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ReportMonthly {
    ArrayList<MonthData> monthInfo = new ArrayList<>();

    public void loadFile(int pickMonth ,String path) {
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] lineContents = line.split(",");

            String name = lineContents[0];
            boolean isExpense = Boolean.parseBoolean(lineContents[1]);
            int quantity = Integer.parseInt(lineContents[2]);
            int unit_price = Integer.parseInt(lineContents[3]);

            monthInfo.add(new MonthData(name, isExpense, quantity, unit_price, pickMonth));
        }
    }

    public void printStatisticMonths(int pickMonth) { //самый прибыльный товар, название товара и сумму и самую большую трату, название товара и сумму.
        String bestProduct = null;
        int bestProfit = 0;
        String biggestExpenseItem =  null;
        int biggestExpense = 0;


        for (MonthData monthData : monthInfo) {
            if (monthData.pickMonth != pickMonth) {
                continue;
            }
            int total = monthData.quantity * monthData.unit_price;

            if (!monthData.is_expense) {
                if (total > bestProfit) {
                    bestProfit = total;
                    bestProduct = monthData.item_name;
                }
            } else {
                if (total > biggestExpense) {
                    biggestExpense = total;
                    biggestExpenseItem = monthData.item_name;
                }
            }
        }

        System.out.println("m.20210" + pickMonth + ".csv");
        System.out.println("Cамый прибыльный товар: " + bestProduct + " сумма " + bestProfit);
        System.out.println("Самая большая трата: " + biggestExpenseItem + " сумма " + biggestExpense);
    }

    List<String> readFileContents(String path) { // Считывание файлов
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}