package cs211.project.services;

import cs211.project.models.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ChatDatasource implements Datasource<Chat>{
    private String directoryName;
    private String fileName;
    public ChatDatasource(String directoryName, String fileName){
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
    public Chat readData(){
        Chat texts = new Chat();
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
                String chatId = data[0];
                String date = data[1];
                String time = data[2];
                String name = data[3];
                String text = data[4];

                texts.addNewText(chatId,date,time,name,text);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return texts;
    }

    @Override
    public void writeData(Chat data) {
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
            for (ChatText text : data.getChat()) {
                String line = text.getChatId() + "," + text.getDate() + "," + text.getTime() + "," + text.getUsername() + "," + text.getText();
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

