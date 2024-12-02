# CS211-661-Project
> Project ภาคต้น 2566

## Project detail
https://saacsos.notion.site/Project-CS211-2566-1c8e195c0ebd4071aea8f733692e3989?pvs=4

---
> วิธีการติดตั้งหรือรันโปรแกรม
1.  โหลดไฟล์ NPBP.zip จาก Tag Jarfile 
2.  เเตกไฟล์ NPBP.zip 
3.  หลังจากเเตกไฟล์เรียบร้อยเเล้ว เวลาที่ต้องการเปิดโปรเเกรมให้ คลิกไปที่ NPBP หรือ รันผ่าน command line โดยใช้ java -jar *.jar file

>  ตัวอย่างข้อมูลผู้ใช้ระบบ
* Admin:
    * Username:Admin
    * Password:Admin123
* User:
    * Username:Kaepie
    * Password:Kaepie123
>  การวางโครงสร้างไฟล์
1. data เก็บข้อมูล csv ทั้งหมดภายในโปรแกรม
   1. account.csv เก็บข้อมูลของ account ทั้งหมดภายในโปรแกรม
   2. event.csv เก็บข้อมูลของอิเว้นท์ทั้งหมดภายในโปรแกรม
   3. teams.csv เก็บข้อมูลของทีมทั้งหมดภายในโปรแกรม
   4. activity.csv เก็บกิจกรรมทั้งหมดภายในโปรแกรม
   5. chat.csv เก็บแชททั้งหมดภายในโปรแกรม
   6. account_join_events.csv เก็บข้อมูล accountId ที่เข้าร่วม event ผูกกับ eventId 
   7. account_owner_events.csv เก็บข้อมูล accountId ที่สร้าง event ผูกกับ eventId
   8. event_activity.csv เก็บข้อมูลกิจกรรมภายในอีเว้นท์นั้น โดยผูก eventId กับ activityId
   9. event_team.csv เก็บข้อมูลของทีมภายในอีเว้นท์นั้น โดยผูก eventId กับ teamId
   10. team_account.csv เก็บข้อมูลของผู้ใช้ที่อยู่ในทีมนั้น โดยผูก teamId กับ accountId
   11. team_activity.csv เก็บข้อมูลกิจกรรมภายในทีมนั้น โดยผูก teamId กับ activityId
   12. team_chat.csv เก็บข้อมูลของแชทภายในทีม โดยผูก teamId กับ chatId
2. images
   1. Admin เก็บไฟล์รูปของ Admin
   2. Creator เก็บไฟล์รูปของผู้สร้างโปรแกรม
   3. event เก็บไฟล์รูปของอีเว้นท์
   4. User เก็บไฟล์รูปของ User
3. controllers จะเก็บคลาสทั้งหมดที่คอยจัดการแต่ละที่ผู้ใช้เห็น
4. cs211661 จะเก็บคลาสไว้รันโปรแกรม
5. models เป็นตัวจัดการการคำนวณต่างๆในโปรแกรม
6. pivot ผูกข้อมูลระหว่างสองข้อมูลเข้าด้วยกันเพื่อที่จะสามารถนำไปใช้ได้ง่ายยิ่งขึ้น
7. repository เป็นตัวขั้นกลางระหว่าง controller กับ datasource ทำให้ controller สะดวกในการอ่านข้อมูลจากไฟล์มากขึ้น 
8. services เก็บตัวอ่านข้อมูลจาก csv และเป็นตัวที่ controller สามารถเรียกใช้อะไรที่จำเป็นได้
9. CSS เก็บ css file เพื่อตกแต่งตัวโปรแกรมด้านในและเก็บรูปบางรูปที่ css ต้องดึงมาใช้
10. views เก็บ fxml ที่ controller จะดึงไปแสดงให้ผู้ใช้โปรแกรมเห็น
> ความคืบหน้าครั้งที่ 1 ของแต่ละคน

* 6510450143:
    * สร้างหน้า  fxml sidebar admin  setting
    * เชื่อมหน้าแต่ละหน้าเข้าด้วยกัน
* 6510450291:
    * สร้างหน้า fxml เกี่ยวกับ Event
    * ทำในส่วนของหน้า Create Event
    * ทำในส่วนของหน้า Join Event
* 6510450437:
    * สร้างหน้า fxml ที่ใช้ในการแก้ไขข้อมูลอีเว้นท์ และ การสร้างทีมสำหรับผู้ร่วมจัดอีเว้นท์
* 6510450445:
    * สร้างหน้า fxml เกี่ยวกับ login,signup,edit user, home-page
    * เชื่อมหน้าแต่ละหน้าเข้าด้วยกัน
> ความคืบหน้าครั้งที่ 2 ของแต่ละคน

* 6510450143:
    * สร้าง models Activity  ActivityList
* 6510450291:
    * สร้าง models ของ Event กับ EventList
    * สร้าง attribute กับ methods ของ models Event กับ EventList
    * สร้าง Controller เกี่ยวกับหน้าโชว์ Event ที่จะเข้าร่วมกับ การเข้าร่วม Event
* 6510450437:
    * สร้าง models ของ Staff, StaffList, Team, TeamList, ChatText, Chat
    * สร้าง Controller ของหน้า StaffList แสดงสมาชิกทั้งหมดในทีม
    * สร้าง Controller ของหน้า Chat แสดงหน้าต่างสนทนาของทีม ซึ่งสามารถส่งความได้แล้ว
    * สร้าง TeamDatasource, StaffDatasource, ChatDatasource
* 6510450445:
    * สร้าง models ของ Account, AccountList, User, Admin
    * สร้าง attribute ของ Account, AccountList, User, Admin
    * ทำ accountDataSource 
    * ทำ Controller หน้า login
> ความคืบหน้าครั้งที่ 3 ของแต่ละคน

* 6510450143:
    *  สร้าง Controller ของหน้าSetting (เปลี่ยนรหัส เปลี่ยนรูป)
    *  สร้าง ActivityDatasource,TeamActivityDatasource,EventActivityDatasource
    *  สร้าง pivot EventActivity,EventActivityList,TeamActivity,TeamActivityList
    *  สร้าง repository  ActivityTeamEventRepository
* 6510450291:
    * ทำช่อง Search ชื่อ Event
    * ทำระบบเกี่ยวกับการเข้าร่วมอีเว้นท์มีการเช็คเวลาของอีเว้นท์ เช็คจำนวนคน และเช็คว่าผู้ใช้โดนแบนหรือไม่
    * สร้างหน้าโชว์อีเว้นท์ที่เข้าร่วม สามารถดูรายละเอียดของอีเว้นท์นั้นได้
    * สร้างหน้า โชว์อีเว้นที่ ตัวเองเป็นผู้จัดอีเว้นท์ สามารถเข้าไปหน้าจัดการอีเว้นท์ได้
    * สร้างหน้าที่สามารถแก้ไขข้อมูลภายในอีเว้นท์ได้ สำรหรับผู้ใช้ที่เป็นผู้จัดอีเว้นท์
    * ทำ Animation ในหน้าโชว์ Event
* 6510450437:
    * แก้ไข models ของ Team จากเดิมที่มีการเก็บ ArrayList ของ Staff ไว้ใน Team เปลี่ยนเป็นการดึงข้อมูลของสมาชิกในทีมผ่านเลขไอดีของสมาชิกแทน
    * สร้าง pivot ของ EventTeam และ EventTeamList สำหรับเชื่อมข้อมูล และ จดบันทึกข้อมูลระหว่างเลขไอดีของ event และ เลขไอดีของ team
    * สร้าง repository ของ TeamRepository
    * สร้างหน้า CreateTeam สำหรับใช้ในการสร้างทีมของผู้ร่วมจัดอีเว้นท์
    * สร้างหน้า TeamList แสดงทีมทั้งหมดในอีเว้นท์นั้นๆ
* 6510450445:
    * ดึง dependency ของ MaterialFx มาใช้ในหน้า login, signup, history
    * ทำ controller home เซ็ตรูปภาพ sidebar
    * ทำ controller ที่ผู้จัดอีเว้นท์จัดการผู้เข้าร่วมอีเว้นท์
    * ทำ controller หน้า history สร้างตารางเพื่อโชว์อีเว้นท์ที่เข้าร่วม
    * ทำ css ใส่ตาราง history
    * ทำคลาส animation กับ keypress
> ความคืบหน้าครั้งที่ 4 ของแต่ละคน

* 6510450143:
    * นำActivity ไปเชื่อมกับ Team เเละ Event
* 6510450291:
    * ส่วนระบบของอีเว้นท์เสร็จสมบูรณ์ ได้มีการแก้ไข debug ต่างๆ ทำให้มีการทำงานที่ถูกต้อง แก้ไขเรื่องเวลา การเปิดรับรับสมัครคนเข้าร่วมอีเว้นท์
    * ทำส่วนที่สามารถเข้าร่วม team ได้หลาย team เมื่อเข้าร่วม team แล้วจะไม่สามารถเข้าร่วมอีเว้นท์แบบผู้ใช้ทั่วไปได้ แต่สามารถเข้าร่วมทีมอื่นได้
* 6510450437:
    * สร้าง pivot ของ TeamAccount และ TeamAccountList สำหรับเชื่อมข้อมูล และ จดบันทึกข้อมูลระหว่างเลขไอดีของ team และ เลขไอดีของ account
    * สร้าง pivot ของ TeamChat และ TeamChatList สำหรับเชื่อมข้อมูล และ จดบันทึกข้อมูลระหว่างเลขไอดีของ team และ เลขไอดีของ chat
    * สร้าง repository ของ EventTeamRepository, TeamAccountRepository, TeamChatRepository
    * แก้ไขหน้า TeamList ที่แสดงทีมทั้งหมดในอีเว้นท์นั้นๆ ให้แสดงผลอย่างถูกต้อง โดยไม่แสดงทีมของอีเว้นท์อื่นๆ
    * ทำส่วนการเข้าร่วม team ให้ไม่แสดง team ที่หมดเวลารับสมัครแล้ว และ ผู้ใช้ไม่สามารถเข้าร่วม team ที่สมาชิกเต็มแล้วได้
    * สร้างหน้าแสดงรายละเอียดของ team ซึ่งสามารถไปยังหน้าต่างๆของระบบ team ได้ เช่น หน้าแสดงสมาชิก, หน้าการสื่อสารภายในทีม(chat), หน้าตารางกิจกรรมของทีม
    * แก้ไขหน้าแสดงสมาชิกของ team ให้แสดงผลอย่างถูกต้อง โดยไม่แสดงสมาชิกที่อยู่ใน team อื่น
    * ทำระบบการระงับสิทธิ์ของผู้ร่วมทีม โดยผู้จัดอีเว้นท์สามารถระงับสิทธิ์ของผู้เข้าร่วมทีมได้ และ ผู้ที่ถูกระงับสิทธิ์จะไม่สามารถมองเห็นตารางกิจกรรมของทีมที่ตนถูกระงับสิทธิ์ได้แล้ว
    * แก้ไขระบบสร้าง team ให้ไม่สามารถมีชื่อทีมซ้ำกันในอีเว้นท์เดียวกันได้
* 6510450445:
    * ทำส่วนของเปลี่ยนธีม
    * ทำส่วนของ admin ให้เสร็จสมบูรณ์
    * ทำ polymorphism ใน admin เรียงลำดับผู้ใช้ที่ login เข้ามาล่าสุด
    * ทำ introduction, creator ในหน้า login ให้สามารถกดเข้าไปดูได้