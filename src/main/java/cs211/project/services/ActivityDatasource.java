package cs211.project.services;

import cs211.project.models.Activity;
import cs211.project.models.ActivityList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ActivityDatasource implements  Datasource<ActivityList>{
    private String directoryName;
    private String fileName;

    public ActivityDatasource(String directoryName, String fileName){
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
    public ActivityList readData() {
        ActivityList activitys = new ActivityList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader buffer = new BufferedReader(inputStreamReader);
        String line = "";
        try {
            while ( (line = buffer.readLine()) != null ){
                if (line.equals("")) continue;

                String[] data = line.split(",");
                String id = data[0].trim();
                String name = data[1].trim();
                String detail = data[2].trim();
                String dateStart = data[3].trim();
                String dateEnd = data[4].trim();
                String timeStart = data[5].trim();
                String timeEnd = data[6].trim();
                String  status = data[7].trim();

                activitys.addNewActivityFromFile(name,detail,id,dateStart,dateEnd,timeStart,timeEnd,status);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return activitys;
    }

    @Override
    public void writeData(ActivityList data)
    {
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
            for (Activity activity : data.getActivity()) {
                String line = activity.getId() + "," + activity.getName() + "," +activity.getDetail() + "," + activity.getDateStart() + "," +activity.getDateEnd()+ "," +activity.getTimeStart()+ "," +activity.getTimeEnd() + ","+ activity.getStatus();
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