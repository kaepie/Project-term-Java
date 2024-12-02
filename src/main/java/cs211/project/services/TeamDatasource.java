package cs211.project.services;
import cs211.project.models.Team;
import cs211.project.models.TeamList;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class TeamDatasource implements Datasource<TeamList>{
    private String directoryName;
    private String fileName;
    public TeamDatasource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public TeamList readData(){
        TeamList teams = new TeamList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InputStreamReader inputStreamReader = new InputStreamReader(
                fileInputStream,
                StandardCharsets.UTF_8
        );
        BufferedReader buffer = new BufferedReader(inputStreamReader);

        String line = "";
        try {
            while ( (line = buffer.readLine()) != null ){
                if (line.equals("")) continue;

                String[] data = line.split(",");
                String teamId = data[0];
                String name = data[1];
                String countMember = data[2];
                String maxMember = data[3];
                String openDate = data[4];
                String openTime = data[5];
                String closeDate = data[6];
                String closeTime = data[7];

                teams.addNewTeam(teamId, name, maxMember, openDate, openTime, closeDate, closeTime, countMember);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teams;
    }

    @Override
    public void writeData(TeamList data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream,
                StandardCharsets.UTF_8
        );
        BufferedWriter buffer = new BufferedWriter(outputStreamWriter);

        try {
            for (Team team : data.getTeams()) {
                String line = team.getTeamId() + "," + team.getTeamName() + "," + team.getCountMember() + "," + team.getMaxMember() + "," + team.getOpenDate() + "," + team.getOpenTime() + "," + team.getCloseDate() + "," + team.getCloseTime();
                buffer.append(line);
                buffer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.flush();
                buffer.close();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
