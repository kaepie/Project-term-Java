package cs211.project.services;

import cs211.project.models.Account;
import cs211.project.models.AccountList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AccountDatasource implements Datasource<AccountList> {
    private String directoryName;
    private String fileName;

    public AccountDatasource(String directoryName, String fileName){
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
    public AccountList readData() {
        AccountList accountList = new AccountList();
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
                String roleAccount = data[0].trim();
                String Id = data[1].trim();
                String username = data[2].trim();
                String name = data[3].trim();
                String password = data[4].trim();
                String image = data[5].trim();
                String timeLogin = data[6].trim();
                String theme = data[7].trim();
                // เพิ่มข้อมูลลงใน list
                if(roleAccount.equals("User")){
                    accountList.addNewUserFromFile(username, name,Id, password, roleAccount, image, timeLogin, theme);
                }
                else if(roleAccount.equals("Admin")){
                    accountList.addNewAdminFromFile(username, name,Id, password, roleAccount, image, timeLogin, theme);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    @Override
    public void writeData(AccountList data) {
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
            for (Account user : data.getAccounts()) {
                String line = user.getRoleAccount() + "," + user.getAccountId() + "," +  user.getUsername() + "," + user.getName() + "," + user.getPassword() + "," + user.getImagePath() + "," + user.getTimeLogin() + "," + user.getAccountTheme();
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
