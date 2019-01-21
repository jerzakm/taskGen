package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static List<WorkTask> taskList = new ArrayList<WorkTask>();

    private static final  int M_ROUND = 5;
    private static final  int MIN_DURATION = 360;
    private static final int MAX_DURATION = 450;


    public static void main(String[] args) {
        addTasks();
        TreeMap<String, Integer> tList = rollTasks();
        makeCSV((tList));
    }

    private static void addTasks() {
        taskList.add(new WorkTask(1, 60,180, "Maile, przyjmowanie i obsługa zamówień"));
        taskList.add(new WorkTask(0.8, 30,100, "Kontakt z klientami - hurt"));
        taskList.add(new WorkTask(0.05, 15,60, "Instrukcja, sprawdzanie poprawności/przygotowywanie nowych"));
        taskList.add(new WorkTask(0.5, 20,90, "Kontrola jakości aukcji i zgodności opisu z przedmiotem"));
        taskList.add(new WorkTask(0.25, 40,120, "Wystawianie/zmiana aukcji"));
        taskList.add(new WorkTask(0.2, 10,30, "Serwis"));
        taskList.add(new WorkTask(0.1, 10,20, "Magazyn - konsultacja sposobu wysyłki zamówień hurtowych"));
        taskList.add(new WorkTask(0.1, 10,20, "Magazyn - sprawdzanie specyfikacji produktów"));
        taskList.add(new WorkTask(1, 20,90, "Odbieranie telefonów i załatwanie z nimi związanymi spraw"));
        taskList.add(new WorkTask(0.1, 20,90, "Alibaba, WeChat itp. - poszukiwanie nowych produktów"));
        taskList.add(new WorkTask(0.3, 10,60, "Analiza sprzedaży kategorii i porównanie z planem"));
        taskList.add(new WorkTask(0.15, 10,30, "Zmiana cen wg. trendu sprzedaży i planu dostaw"));
        taskList.add(new WorkTask(0.3, 10,60, "Analiza sprzedaży kategorii i porównanie z planem"));
        taskList.add(new WorkTask(0.2, 10,30, "Pomoc z zapytaniami i problemami działu sprzedaży"));
        taskList.add(new WorkTask(0.4, 20,40, "Tradewatch - analiza sprzedaży, śledzenie konkurencji i poszukiwanie nowych produktów"));
    }

    private static TreeMap<String, Integer> rollTasks() {
        AtomicInteger currentDuration = new AtomicInteger();

        TreeMap<String, Integer> dayTasks = new TreeMap<>();

        while(currentDuration.get() <MIN_DURATION) {
            //iterate all tasks
            taskList.forEach(task -> {
                //check if unique
                if(!dayTasks.keySet().contains(task.getName())) {
                    //roll chance
                    double roll = (ThreadLocalRandom.current().nextDouble(0, 1));
                    if(task.getChance()>roll) {
                        //roll duration
                        int duration = ThreadLocalRandom.current().nextInt(task.getMinLength(), task.getMaxLength());
                        duration = duration +(5-(duration%5));
                        currentDuration.addAndGet(duration);
                        dayTasks.put(task.getName(),duration);
                    }
                }
            });
        }

        System.out.println(gson.toJson(dayTasks));
        System.out.println("TOTAL:" + currentDuration);

        return dayTasks;
    }

    private static void makeCSV(TreeMap<String,Integer> taskList) {
        String csv = "";

        for (String k : taskList.keySet()) {
            csv+= k+";"+taskList.get(k)+";\n";
        }

        try {
            FileUtils.writeStringToFile(new File("t.csv"), csv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}