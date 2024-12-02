package cs211.project.services;

import cs211.project.pivot.EventActivity;
import cs211.project.pivot.EventActivityList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class EventActivityDatasource implements Datasource<EventActivityList>{
    private String directoryName;
    private String fileName;

    public EventActivityDatasource(String directoryName, String fileName) {
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
    public EventActivityList readData() {
        EventActivityList eventActivityList = new EventActivityList();
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
                int ev_id = Integer.parseInt(data[0]);
                int ac_id = Integer.parseInt(data[1]);

                eventActivityList.addNew(ev_id,ac_id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return eventActivityList;
    }


    @Override
    public void writeData(EventActivityList data)
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
            for (EventActivity eventActivity : data.getList()) {
                String line =eventActivity.getEventId()+ "," +eventActivity.getActivityId() ;
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
