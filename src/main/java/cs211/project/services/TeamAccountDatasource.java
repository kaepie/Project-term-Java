package cs211.project.services;

import cs211.project.pivot.TeamAccount;
import cs211.project.pivot.TeamAccountList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TeamAccountDatasource implements Datasource<TeamAccountList>{

    private String directoryName;
    private String fileName;

    public TeamAccountDatasource(String directoryName, String fileName){
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
    public TeamAccountList readData() {
        TeamAccountList teamAccountList = new TeamAccountList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการอ่านไฟล์
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
            // ใช้ while loop เพื่ออ่านข้อมูลรอบละบรรทัด
            while ( (line = buffer.readLine()) != null ){
                // ถ้าเป็นบรรทัดว่าง ให้ข้าม
                if (line.equals("")) continue;

                // แยกสตริงด้วย ,
                String[] data = line.split(",");

                // อ่านข้อมูลตาม index แล้วจัดการประเภทของข้อมูลให้เหมาะสม
                int team_id = Integer.parseInt(data[0]);
                int acc_id = Integer.parseInt(data[1]);
                String status = data[2].trim();
                String role = data[3].trim();
                teamAccountList.addNew(acc_id, team_id, status, role);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return teamAccountList;
    }

    @Override
    public void writeData(TeamAccountList data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการเขียนไฟล์
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
            // สร้าง csv ของ user และเขียนลงในไฟล์ทีละบรรทัด
            for (TeamAccount teamAccount : data.getList()) {
                String line = teamAccount.getTeamId() + "," + teamAccount.getAccountId() + "," + teamAccount.getStatus() + "," + teamAccount.getRole();
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
