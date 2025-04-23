import java.util.HashMap;

public class Checker {

    public ReportMonthly reportMonthly;
    public ReportYear reportYear;

    public Checker(ReportMonthly reportMonthly, ReportYear reportYear) {
        this.reportMonthly = reportMonthly;
        this.reportYear = reportYear;
    }

    public boolean cheсkYandM() {
        boolean chek = true;

        HashMap<Integer, Integer> expensesByYear = new HashMap<>();
        HashMap<Integer, Integer> incomeByYear = new HashMap<>();
        HashMap<Integer, Integer> expensesByMonth = new HashMap<>();
        HashMap<Integer, Integer> incomeByMonth = new HashMap<>();

        for (YearData yearData : reportYear.yearInfo) {
            if (!yearData.isExpense) {
                incomeByYear.put(yearData.month, incomeByYear.getOrDefault(yearData.month, 0) + yearData.amount);
            } else {
                expensesByYear.put(yearData.month, expensesByYear.getOrDefault(yearData.month,0) + yearData.amount);
            }
        }

        for (MonthData monthData : reportMonthly.monthInfo) {
            int total = monthData.quantity * monthData.unit_price;
            if (!monthData.is_expense) {
                incomeByMonth.put(monthData.pickMonth, incomeByMonth.getOrDefault(monthData.pickMonth,0) + total);
            } else {
                expensesByMonth.put(monthData.pickMonth, expensesByMonth.getOrDefault(monthData.pickMonth,0) + total);
            }
        }

        for (Integer month : expensesByYear.keySet()) {
            if (!expensesByYear.get(month).equals(expensesByMonth.get(month))) {
              //  System.out.println("Данные по расходам за " + month + " месяц  совпадают с годовым отчетом ."); // если потребуется вводить инфу по месяцам
            // } else {
                System.out.println("Данные по расходам за " + month + " месяц не совпадают с годовым отчетом!!!");
                return chek = false;
            }
        }

        for (Integer month : incomeByYear.keySet()) {
            if (!incomeByYear.get(month).equals(incomeByMonth.get(month))) {
              //  System.out.println("Данные по доходам за " + month + " месяц  совпадают с годовым отчетом ."); // если потребуется вводить инфу по месяцам
            //} else {
                System.out.println("Данные по доходам за " + month + " месяц не совпадают с годовым отчетом!!!");
                return chek = false;
            }
        }

        return chek;
    }
}
