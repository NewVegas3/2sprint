import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ReportYear {

    ArrayList<YearData> yearInfo = new ArrayList<>();

    public ReportYear(String path) {
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] lineContents = line.split(",");

            int month = Integer.parseInt(lineContents[0]);
            int amount = Integer.parseInt(lineContents[1]);
            boolean isExpense = Boolean.parseBoolean(lineContents[2]);

            yearInfo.add(new YearData(month, amount, isExpense));
        }
    }

    public void printStatisticalForMonths() {
        HashMap<Integer, Integer> income = new HashMap<>();
        HashMap<Integer, Integer> expenses = new HashMap<>();

        for (YearData yearData : yearInfo) {
            if (!yearData.isExpense) {
                income.put(yearData.month, income.getOrDefault(yearData.month, 0) + yearData.amount);
            } else {
                expenses.put(yearData.month, expenses.getOrDefault(yearData.month,0) + yearData.amount);
            }
        }

        int sumIncome = 0;
        for (Integer value : income.values()) {
            sumIncome += value;
        }

        int sumExpenses = 0;
        for (Integer value : expenses.values()) {
            sumExpenses += value;
        }

        for (int i = 1; i < 4; i++) {
            System.out.println("Прибыль по каждому месяцу: " + i + ". месяц  сумма: " + (income.get(i) - expenses.get(i)));
        }
        System.out.println("средний расход за все имеющиеся операции в году: " + sumExpenses / expenses.size());
        System.out.println("средний доход за все имеющиеся операции в году: " + sumIncome / income.size());

    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
