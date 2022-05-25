import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AssignmentTwo {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Teams teams = new Teams();

        String[] splitData = input.replace("{", "")
                .replace("}", "").split(",\\s*\"");
        String[] splitN = splitData[0].split(":\\s*");
        teams.setN(Integer.parseInt(splitN[1]));

        String[] splitPNT = splitData[1].split(":\\s*");
        String[] splitPNTArr = splitPNT[1].replace("[", "")
                .replace("]", "").split(",\\s*");
        teams.setPnt(Arrays.stream(splitPNTArr).mapToInt(e -> Integer.parseInt(e))
                .boxed().collect(Collectors.toList()));

        String[] splitPWE = splitData[2].split(":\\s*");
        String[] splitPWEArr = splitPWE[1].replace("[", "")
                .replace("]", "").split(",\\s*");
        teams.setPwe(Arrays.stream(splitPWEArr).mapToInt(e -> Integer.parseInt(e))
                .boxed().collect(Collectors.toList()));


        if (teams.getN() < 2) {
            ErrorsAssignmentTwo error = new ErrorsAssignmentTwo();
            error.setError("Count of N is less than 2");
            gson.toJson(error, System.out);
            return;
        } else if (teams.getPnt().size() < teams.getN()) {
            ErrorsAssignmentTwo error = new ErrorsAssignmentTwo();
            error.setError(String.format("Length of PNT Array is less than %d", teams.getN()));
            gson.toJson(error, System.out);
            return;
        } else if (teams.getPwe().size() < teams.getN()) {
            ErrorsAssignmentTwo error = new ErrorsAssignmentTwo();
            error.setError(String.format("Length of PWE Array is less than %d", teams.getN()));
            gson.toJson(error, System.out);
            return;
        }


        List<Integer> teamsPnt = teams.getPnt();
        List<Integer> teamsPwe = teams.getPwe();
        List<Integer> allSums = new ArrayList<>();
        int sum = 0;

        int i = 0;
        int j = 0;
        while (i + j <= teamsPwe.size() - teams.getN()) {

            while (i < teams.getN()) {
                sum += Math.abs(teamsPnt.get(i) - teamsPwe.get(i + j));
                i++;
            }
            allSums.add(sum);
            sum = 0;
            i = 0;

            j++;
        }
        i++;
        j = 0;
        int count = 0;
        while (i + j - count <= teamsPwe.size() - teams.getN()) {
            count++;
            while (i < teamsPnt.size()) {
                sum += Math.abs(teamsPnt.get(i) - teamsPwe.get(i + j - count));
                i++;
            }
            count--;
            allSums.add(sum);
            sum = 0;
            i = 1;

            j++;
        }
//        for (int i = 0; i < teams.getN(); i++) {
//            sum += Math.abs(teamsPnt.get(i) - teamsPwe.get(i + 0));
//        }
//        allSums.add(sum);
//        sum = 0;
//        for (int i = 0; i < teams.getN(); i++) {
//            sum += Math.abs(teamsPnt.get(i) - teamsPwe.get(i + 1));
//        }
//        allSums.add(sum);
//        sum = 0;

        System.out.println(allSums);
    }
}
