import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class AssignmentTwo1 {
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

        BestSprints bestSprints = new BestSprints();

        List<Integer> teamsPnt = teams.getPnt();
        List<Integer> teamsPwe = teams.getPwe();
        int sum = 0;

        int i = 0;
        int j = 0;
        int count = 0;
        while (count <= teamsPnt.size() - teams.getN()) {

            while (j <= teamsPwe.size() - teams.getN()) {

                while (i <= teams.getN() - 1 && i + j < teamsPwe.size()) {
                    sum += Math.abs(teamsPnt.get(i + count) - teamsPwe.get(i + j));
                    i++;
                }
                if (count == 0 && j == 0) {
                    bestSprints.setMinDiff(sum);
                }
                if (sum < bestSprints.getMinDiff()) {
                    bestSprints.setMinDiff(sum);
                    bestSprints.setStartIndexPnt(count);
                    bestSprints.setStartIndexPwe(j);
                }
                sum = 0;
                i = 0;

                j++;
            }

            count++;
            j = 0;
        }


        gson.toJson(bestSprints, System.out);
    }
}
